package backend;

import java.sql.Timestamp;

public class Quiz {
	private String quiz_name;
	private String quiz_description;
	private String quiz_author;
	private int likes;
	private String difficulty;
	private int times_taken;
	
	public String getQuizName(){
		return quiz_name;
	}
	
	public String getQuizDescription(){
		return quiz_description;
	}
	
	public String getQuizAuthor(){
		return quiz_author;
	}
	
	public int getLikes(){
		return likes;
	}
	
	public String getDifficulty(){
		return difficulty;
	}
	
	public int getTimesTaken(){
		return times_taken;
	}
}
