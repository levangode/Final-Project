package quizInfoes;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;

public class QuizFullSummary extends QuizDetailedInfo implements DrawableInfo {
	private String quiz_difficulty;
	private Boolean immediate_correction;
	private int quiz_score;
	private int time_limit;

	public QuizFullSummary(String quiz_name, int times_taken, String quiz_author, int quiz_score, Timestamp quiz_date, int quiz_id,
			String quiz_category, String quiz_description, int quiz_likes, String quiz_difficulty,
			boolean immediate_correction, int time_limit) {
		super(quiz_name, times_taken, quiz_author, quiz_date, quiz_id, quiz_category, quiz_description, quiz_likes);
		this.setQuiz_difficulty(quiz_difficulty);
		this.setImmediate_correction(immediate_correction);
		this.setQuiz_score(quiz_score);
		this.time_limit=time_limit;
	}
	@Override
	public void showOnCard(JspWriter out) {
		String onOff = "";
		if (getImmediate_correction()) {
			onOff = "ON";
		} else {
			onOff = "OFF";
		}
		try {
			out.write("<p>Name: " + getQuiz_name() + "</p>");
			out.write("<p>Description: " + getQuiz_description() + "</p>");
			out.write("<p>Quiz Category: " + getQuiz_category() + "</p>");
			out.write("<p>");
			out.write("	Author: <a href=\"UserPage.jsp?" + getQuiz_author() + "\">" + getQuiz_author() + "</a>");
			out.write("</p>");
			out.write("<p>Quiz Difficulty: " + getQuiz_difficulty() + "</p>");
			out.write("<p>Create Date: " + getQuiz_date() + "</p>");
			out.write("<p>Immediate Correction: " + onOff + "</p>");
			out.write("<p>Quiz Likes: " + getQuiz_likes() + "</p>");
			out.write("<p>Times Taken: " + getTimes_taken() + "</p>");
			out.write("<p>Maximum Score in This Quiz: " + getQuiz_score() + "</p>");
			out.write("<p>Time Limit: "+time_limit + " minutes</p>");
		} catch (Exception e) {
			// TODO: handle exception
		}
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
	public int getQuiz_score() {
		return quiz_score;
	}
	public void setQuiz_score(int quiz_score) {
		this.quiz_score = quiz_score;
	}

}
