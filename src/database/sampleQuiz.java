package database;

import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import backend.*;

public class sampleQuiz {

	
	public ArrayList<Question> buildQuestions(){
		Question a = new Question("Ra kaci xar?", QuestionTypes.MultipleChoice, "kacoba-test",
				-1);
		
		Answer aa = new Answer(1, 1, "chmiddshvc", "tyn",
				true,"kacuri", a.getQuestionid());
		Answer ab = new Answer(2, 1, "shniddshvc", 
				"tyn2", false, "mamruli",a.getQuestionid());
		ArrayList<Answer> answers = new ArrayList<>();
		answers.add(aa);
		answers.add(ab);
		a.setAnswers(answers);
		
		
		Question b = new Question("kai bichia davalombardet da fuli gvchirdeba-dagvexmarebi?",
				QuestionTypes.MultipleChoice,"tyn3", -1);
		Answer ba = new Answer(1, 1, "ki to", "tynas", true, "racxa", b.getQuestionid());
		Answer bb = new Answer(2, 1, "vera to vivusidan damdeven",
				"bla", false, "badb", b.getQuestionid());
		
		ArrayList<Answer> answers2 = new ArrayList<>();
		answers2.add(ba);
		answers2.add(bb);
		b.setAnswers(answers2);
		
		ArrayList<Question> questions = new ArrayList<>();
		questions.add(a);
		questions.add(b);
		return questions;
		
	}
	

	
	public Quiz getSampleQuiz() {
		Quiz quiz = new Quiz("kai bichi test", "chmddshvc", "bla",
				"HARD", 0, 0);
		quiz.setQuestions(buildQuestions());
		return quiz;
	}
	
	public void addQuiz(){
		
	}

}
