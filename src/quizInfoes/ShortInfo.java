package quizInfoes;

import java.sql.Timestamp;

public class ShortInfo {
	private int quiz_id;
	private String quiz_name;
	private Timestamp date;
	private String quiz_description;
	private int quiz_likes;

	public ShortInfo(int quiz_id, String quiz_name, Timestamp date, String quiz_description, int quiz_likes) {
		this.quiz_id = quiz_id;
		this.quiz_name = quiz_name;
		this.date = date;
		this.quiz_description = quiz_description;
		this.quiz_likes = quiz_likes;
	}

	public int getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}

	public String getQuiz_name() {
		return quiz_name;
	}

	public void setQuiz_name(String quiz_name) {
		this.quiz_name = quiz_name;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getQuiz_description() {
		return quiz_description;
	}

	public void setQuiz_description(String quiz_description) {
		this.quiz_description = quiz_description;
	}

	public int getQuiz_likes() {
		return quiz_likes;
	}

	public void setQuiz_likes(int quiz_likes) {
		this.quiz_likes = quiz_likes;
	}

}
