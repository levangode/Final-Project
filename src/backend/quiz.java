package backend;

import java.sql.ResultSet;
import java.util.HashSet;

public class quiz {
	private HashSet<question> questions;
	private String description;
	private int score;

	public quiz(HashSet<question> questions, String description) {
		this.questions = questions;
		this.description = description;
		score = 0;
	}

	
	
	public void incrementScore(int num) {
		score += num;
	}

	public void decrementScore(int num) {
		score -= num;
	}

	public HashSet<question> getQuestions() {
		return questions;
	}

	public int getScore() {
		return score;
	}

	public String getDescription() {
		return description;
	}

}
