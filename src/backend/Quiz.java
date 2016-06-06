package backend;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Quiz {
	private int quiz_id;
	private String quiz_name;
	private String quiz_description;
	private String quiz_author;
	private int quiz_likes;
	private Timestamp date_created_timestamp;
	private int category_id;
	private String quiz_difficulty;
	private int times_taken;
	private ArrayList<Question> questions;

	public Quiz(int quiz_id, String quiz_name, int category_id, String quiz_description, String quiz_author,
			Timestamp date_created_timestamp, int quiz_likes, String quiz_difficulty, int times_taken,
			ArrayList<Question> questions) {
		this.quiz_id = quiz_id;
		this.quiz_name = quiz_name;
		this.quiz_description = quiz_description;
		this.quiz_author = quiz_author;
		this.quiz_likes = quiz_likes;
		this.questions = questions;
		this.date_created_timestamp = date_created_timestamp;
		this.category_id = category_id;
		this.quiz_difficulty = quiz_difficulty;
		this.times_taken = times_taken;

	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public int getQuizId() {
		return quiz_id;
	}

	public Timestamp getDateCreatedTimestamp() {
		return date_created_timestamp;
	}

	public int getCategoryId() {
		return category_id;
	}

	public String getQuizName() {
		return quiz_name;
	}

	public String getQuizDescription() {
		return quiz_description;
	}

	public String getQuizAuthor() {
		return quiz_author;
	}

	public int getLikes() {
		return quiz_likes;
	}

	public String getDifficulty() {
		return quiz_difficulty;
	}

	public int getTimesTaken() {
		return times_taken;
	}
}
