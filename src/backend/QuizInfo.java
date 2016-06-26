package backend;

import java.sql.Timestamp;
import java.util.ArrayList;

public class QuizInfo {
	private String quiz_name;
	private int times_taken;
	private String quiz_author;
	private Timestamp quiz_date;
	private int quiz_id;
	
	public QuizInfo(String quiz_name, int times_taken, String quiz_author, Timestamp quiz_date, int quiz_id){
		this.setQuiz_name(quiz_name);
		this.quiz_author=quiz_author;
		this.times_taken=times_taken;
		this.quiz_date=quiz_date;
		this.quiz_id=quiz_id;
	}

	public String getQuiz_name() {
		return quiz_name;
	}

	public void setQuiz_name(String quiz_name) {
		this.quiz_name = quiz_name;
	}
	
	
}
