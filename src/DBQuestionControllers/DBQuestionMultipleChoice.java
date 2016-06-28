package DBQuestionControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBAnswerControllers.DBMultipleChoiceAnswers;
import DBAnswerControllers.DBResponseAnswer;
import answers.Answer;
import database.DBconnector;
import questions.MultipleChoiceQuestion;
import questions.Question;
import questions.QuestionResponse;
import questions.QuestionTypes;
import questions.QuestionWithMultipleAnswers;

public class DBQuestionMultipleChoice {
	
	private Connection connection;

	
	public DBQuestionMultipleChoice() {
		this.connection = new DBconnector().getConnection();
	}

	public DBQuestionMultipleChoice(Connection con) {
		this.connection = con;
	}
	
	public List<MultipleChoiceQuestion> retrieveQuestions(int quiz_id){
		List<MultipleChoiceQuestion> questions = new ArrayList<MultipleChoiceQuestion>();
		
		String query = " select question_text, question_data, question_time_limit, score, num_answers_display, num_answers_correct, question_id from Questions_MultipleChoice where quiz_id = "
				+ quiz_id
				+ "; ";
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				MultipleChoiceQuestion newQuestion;
				
				ArrayList<Answer> answers = getAnswers(rs.getInt(7));
				
				newQuestion = new MultipleChoiceQuestion(
								rs.getString(1),
								QuestionTypes.MultipleChoiceMultipleAnswer,
								rs.getString(2),
								rs.getLong(3),
								rs.getInt(4),
								rs.getInt(5),
								rs.getInt(6),
								answers,
								0
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
		
		DBMultipleChoiceAnswers db = new DBMultipleChoiceAnswers();
		
		res = new ArrayList<Answer>( db.retrieveAnswers(id) );
		
		return res;
	}
	
	public void addQuestion(MultipleChoiceQuestion question, int quiz_id) throws Exception{
		String query = 
				"insert into Questions_MultipleChoice(quiz_id, question_text, question_data, question_time_limit, score, num_answers_display, num_answers_correct) value ("
				+ quiz_id + ", "
				+ "'" + question.getQuestiontext() + "'" + ", "
				+ "'" + question.getQuestiondescription() + "'" + ", "
				+ question.getQuestiontimelimit() + ", "
				+ question.getQuestionscore() + ", "
				+ question.getNumanswersdisplay() + ", "
				+ question.getNumanswerscorrect()
				+ ");";
		
		PreparedStatement stm;
		
		try{
//			System.out.println(query);
			stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			stm.executeUpdate();
			
			ResultSet rs = stm.getGeneratedKeys();
			int question_id = -1;
			while(rs.next()){
				question_id = rs.getInt(1);
			}
	
			for(Answer cur: question.getAnswers()){
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
