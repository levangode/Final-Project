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
		this.quiz_name=quiz_name;
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

	public int getTimes_taken() {
		return times_taken;
	}

	public void setTimes_taken(int times_taken) {
		this.times_taken = times_taken;
	}

	public String getQuiz_author() {
		return quiz_author;
	}

	public void setQuiz_author(String quiz_author) {
		this.quiz_author = quiz_author;
	}

	public Timestamp getQuiz_date() {
		return quiz_date;
	}

	public void setQuiz_date(Timestamp quiz_date) {
		this.quiz_date = quiz_date;
	}

	public int getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	
}
