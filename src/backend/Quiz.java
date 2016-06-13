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
	private String quiz_category;
	private String quiz_difficulty;
	private int times_taken;
	private ArrayList<Question> questions;

	public Quiz(int quiz_id, String quiz_name, int category_id, String quiz_description, String quiz_author,
			Timestamp date_created_timestamp, int quiz_likes, String quiz_difficulty, int times_taken,
			ArrayList<Question> questions) {
		
		
		

	}
	public Quiz(String quiz_name, String quiz_description, String quiz_category, String quiz_difficulty, int times_taken,
			ArrayList<Question> questions){
		
		
		
		
	}
	public int getQuizID() {
		return quiz_id;
	}
	public void setQuizID(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	public String getQuizName() {
		return quiz_name;
	}
	public void setQuizName(String quiz_name) {
		this.quiz_name = quiz_name;
	}
	public String getQuizDescription() {
		return quiz_description;
	}
	public void setQuizDescription(String quiz_description) {
		this.quiz_description = quiz_description;
	}
	public int getQuizLikes() {
		return quiz_likes;
	}
	public void setQuizLikes(int quiz_likes) {
		this.quiz_likes = quiz_likes;
	}
	public Timestamp getDateCreatedTimestamp() {
		return date_created_timestamp;
	}
	public void setDateCreatedTimestamp(Timestamp date_created_timestamp) {
		this.date_created_timestamp = date_created_timestamp;
	}
	public int getCategoryID() {
		return category_id;
	}
	public void setCategoryID(int category_id) {
		this.category_id = category_id;
	}
	public String getQuizCategory() {
		return quiz_category;
	}
	public void setQuizCategory(String quiz_category) {
		this.quiz_category = quiz_category;
	}
	public int getTimesTaken() {
		return times_taken;
	}
	public void setTimesTaken(int times_taken) {
		this.times_taken = times_taken;
	}
	public String getQuizDifficulty() {
		return quiz_difficulty;
	}
	public void setQuizDifficulty(String quiz_difficulty) {
		this.quiz_difficulty = quiz_difficulty;
	}
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	public String getQuizAuthor() {
		return quiz_author;
	}
	public void setQuizAuthor(String quiz_author) {
		this.quiz_author = quiz_author;
	}
	


}
