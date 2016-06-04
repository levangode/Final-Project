package backend;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Quiz {
	private String quiz_name;
	private String quiz_description;
	private String quiz_author;
	private int likes;
	private String difficulty;
	private int times_taken;
	
	Quiz(	String quiz_name,
	String quiz_description,
	String quiz_author,
	int likes,
	String difficulty,
	int times_taken, ArrayList<Question> questions){
		this.quiz_name = quiz_name;
		this.quiz_description= quiz_description;
		this.quiz_author = quiz_author;
		this.likes = likes;
		this.difficulty = difficulty;
		this.times_taken = times_taken;
		
	}
	
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
