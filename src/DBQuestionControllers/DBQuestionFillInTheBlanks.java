package DBQuestionControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBAnswerControllers.DBBlankAnswer;
import answers.Answer;
import database.DBconnector;
import questions.FillTheBlankQuestion;
import questions.QuestionTypes;

public class DBQuestionFillInTheBlanks {
	private Connection connection;

	
	public DBQuestionFillInTheBlanks() {
		this.connection = new DBconnector().getConnection();
	}
	public DBQuestionFillInTheBlanks(Connection DBconnection) {
		this.connection = DBconnection;
	}
	
	public List<FillTheBlankQuestion> retrieveQuestions(int quiz_id){
		List<FillTheBlankQuestion> questions = new ArrayList<FillTheBlankQuestion>();
		
		String query = " select question_text, question_data, score, question_id, question_number from Questions_FillInTheBlanks where quiz_id = "
				+ quiz_id
				+ ";";
		
		PreparedStatement stm;
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				FillTheBlankQuestion newQuestion;
				
				ArrayList<Answer> answers = getAnswers(rs.getInt(4));
				
				newQuestion = new FillTheBlankQuestion(
								rs.getString("question_text"), 
								QuestionTypes.QuestionResponse,
								rs.getString("question_data"),
								rs.getInt("score"),
								answers,
								rs.getInt("question_number")
							);
				
				questions.add(newQuestion);
			}
			
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		}
		
		return questions;
	}
	
	private ArrayList<Answer> getAnswers(int id){
		ArrayList<Answer> res;
		
		DBBlankAnswer db = new DBBlankAnswer();
		
		res = new ArrayList<Answer>( db.retrieveAnswers(id) );
		
		return res;
	}
	
	public void addQuestion(FillTheBlankQuestion question, int quiz_id) throws Exception{
		String query = 
				"insert into Questions_FillInTheBlanks(quiz_id, question_text, question_data, score, question_number) value ("
				+ quiz_id + ", "
				+ "'" + question.getQuestiontext() + "'" + ", "
				+ "'" + question.getQuestiondescription() + "'" + ", "
				+ question.getQuestionscore() + ", "
				+ question.getQuestionnumber()
				+ ");";
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try{
			stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			stm.executeUpdate();
			
			ResultSet rs = stm.getGeneratedKeys();
			int question_id = -1;
			
			while(rs.next()){
				question_id = rs.getInt(1);
			}
			
			question.getAnswers();
			
			for(Answer cur: question.getAnswers() ){
				cur.addToDatabase(question_id);
			}
			
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			
			throw new Exception("Error durin insertion in DB");
			//e.printStackTrace();
		}
	}
}
