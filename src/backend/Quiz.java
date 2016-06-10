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
	public int getQuizid() {
		return quiz_id;
	}
	public void setQuizid(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	public String getQuizname() {
		return quiz_name;
	}
	public void setQuizname(String quiz_name) {
		this.quiz_name = quiz_name;
	}
	public String getQuizdescription() {
		return quiz_description;
	}
	public void setQuizdescription(String quiz_description) {
		this.quiz_description = quiz_description;
	}
	public int getQuizlikes() {
		return quiz_likes;
	}
	public void setQuizlikes(int quiz_likes) {
		this.quiz_likes = quiz_likes;
	}
	public Timestamp getDatecreatedtimestamp() {
		return date_created_timestamp;
	}
	public void setDatecreatedtimestamp(Timestamp date_created_timestamp) {
		this.date_created_timestamp = date_created_timestamp;
	}
	public int getCategoryid() {
		return category_id;
	}
	public void setCategoryid(int category_id) {
		this.category_id = category_id;
	}
	public String getQuizcategory() {
		return quiz_category;
	}
	public void setQuizcategory(String quiz_category) {
		this.quiz_category = quiz_category;
	}
	public int getTimestaken() {
		return times_taken;
	}
	public void setTimestaken(int times_taken) {
		this.times_taken = times_taken;
	}
	public String getQuizdifficulty() {
		return quiz_difficulty;
	}
	public void setQuizdifficulty(String quiz_difficulty) {
		this.quiz_difficulty = quiz_difficulty;
	}
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	public String getQuizauthor() {
		return quiz_author;
	}
	public void setQuizauthor(String quiz_author) {
		this.quiz_author = quiz_author;
	}
	


}
