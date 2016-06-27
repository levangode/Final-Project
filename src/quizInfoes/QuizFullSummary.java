package quizInfoes;

import java.sql.Timestamp;
import java.util.ArrayList;

public class QuizFullSummary extends QuizDetailedInfo implements DrawableInfo{
	private ArrayList<QuizInfo> usersPastPerformance;
	private ArrayList<String> highestPerformers;
	private ArrayList<String> performersOfTheDay;
	private ArrayList<String> statistics;
	private String quiz_difficulty;
	private Boolean immediate_correction;

	public QuizFullSummary(String quiz_name, int times_taken, String quiz_author, Timestamp quiz_date, int quiz_id,
			String quiz_category, String quiz_description, int quiz_likes, String quiz_difficulty, boolean immediate_correction) {
		super(quiz_name, times_taken, quiz_author, quiz_date, quiz_id, quiz_category, quiz_description, quiz_likes);
		this.setQuiz_difficulty(quiz_difficulty);
		this.setImmediate_correction(immediate_correction);
		//TODO is danarchenebi zemodan
	}
	
	public String showOnCard(){
		
		return null;
		
	}

	public ArrayList<QuizInfo> getUsersPastPerformance() {
		return usersPastPerformance;
	}

	public void setUsersPastPerformance(ArrayList<QuizInfo> usersPastPerformance) {
		this.usersPastPerformance = usersPastPerformance;
	}

	public ArrayList<String> getHighestPerformers() {
		return highestPerformers;
	}

	public void setHighestPerformers(ArrayList<String> highestPerformers) {
		this.highestPerformers = highestPerformers;
	}

	public ArrayList<String> getPerformersOfTheDay() {
		return performersOfTheDay;
	}

	public void setPerformersOfTheDay(ArrayList<String> performersOfTheDay) {
		this.performersOfTheDay = performersOfTheDay;
	}

	public ArrayList<String> getStatistics() {
		return statistics;
	}

	public void setStatistics(ArrayList<String> statistics) {
		this.statistics = statistics;
	}

	public String getQuiz_difficulty() {
		return quiz_difficulty;
	}

	public void setQuiz_difficulty(String quiz_difficulty) {
		this.quiz_difficulty = quiz_difficulty;
	}

	public Boolean getImmediate_correction() {
		return immediate_correction;
	}

	public void setImmediate_correction(Boolean immediate_correction) {
		this.immediate_correction = immediate_correction;
	}

}
