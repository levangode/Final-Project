package backend;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Quiz {
	private String quiz_name;
	private String quiz_description;
	private String quiz_author;
	private int quiz_likes;
	private Timestamp date_created_timestamp;
	private String quiz_category;
	private String quiz_difficulty;
	private int times_taken;
	private ArrayList<Question> questions;
	private boolean displayMultiplePages;
	private boolean immediateCorrection;
	private boolean randomQuestions;

	public Quiz(String quiz_name, String quiz_description, String quiz_author, int quiz_likes,
			Timestamp date_created_timestamp, String quiz_category, String quiz_difficulty, int times_taken,
			ArrayList<Question> questions, boolean displayMultiplePages, boolean immediateCorrection,
			boolean randomQuestions) {
		this.quiz_name = quiz_name;
		this.quiz_description = quiz_description;
		this.quiz_author = quiz_author;
		this.quiz_likes = quiz_likes;
		this.questions = questions;
		this.date_created_timestamp = date_created_timestamp;
		this.quiz_category = quiz_category;
		this.quiz_difficulty = quiz_difficulty;
		this.times_taken = times_taken;
		this.displayMultiplePages = displayMultiplePages;
		this.immediateCorrection = immediateCorrection;
		this.randomQuestions = randomQuestions;

	}

	public void addQuestion(Question question, int questionNum) {
		if (questionNum >= questions.size()) {
			questions.add(question);
		} else {
			questions.set(questionNum - 1, question);
		}
		System.out.println(questions.size());
	}

	public void addQuestionWithoutNum(Question question) {
		questions.add(question);
	}

	public String toString() {
		String result = "" + "name: " + quiz_name + "\n" + "description: " + quiz_description + "\n" + "author: "
				+ quiz_author + "\n" + "likes: " + quiz_likes + "\n" + "date: " + date_created_timestamp + "\n"
				+ "category: " + quiz_category + "\n" + "difficulty: " + quiz_difficulty + "\n" + "times taken: "
				+ times_taken + "\n" + "display: " + displayMultiplePages + "\n" + "immediate: " + immediateCorrection
				+ "\n" + "random: " + randomQuestions + "\n";
		return result;
	}

	public String getQuiz_name() {
		return quiz_name;
	}

	public void setQuiz_name(String quiz_name) {
		this.quiz_name = quiz_name;
	}

	public String getQuiz_description() {
		return quiz_description;
	}

	public void setQuiz_description(String quiz_description) {
		this.quiz_description = quiz_description;
	}

	public String getQuiz_author() {
		return quiz_author;
	}

	public void setQuiz_author(String quiz_author) {
		this.quiz_author = quiz_author;
	}

	public int getQuiz_likes() {
		return quiz_likes;
	}

	public void setQuiz_likes(int quiz_likes) {
		this.quiz_likes = quiz_likes;
	}

	public Timestamp getDate_created_timestamp() {
		return date_created_timestamp;
	}

	public void setDate_created_timestamp(Timestamp date_created_timestamp) {
		this.date_created_timestamp = date_created_timestamp;
	}

	public String getQuiz_category() {
		return quiz_category;
	}

	public void setQuiz_category(String quiz_category) {
		this.quiz_category = quiz_category;
	}

	public String getQuiz_difficulty() {
		return quiz_difficulty;
	}

	public void setQuiz_difficulty(String quiz_difficulty) {
		this.quiz_difficulty = quiz_difficulty;
	}

	public int getTimes_taken() {
		return times_taken;
	}

	public void setTimes_taken(int times_taken) {
		this.times_taken = times_taken;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public boolean isDisplayMultiplePages() {
		return displayMultiplePages;
	}

	public void setDisplayMultiplePages(boolean displayMultiplePages) {
		this.displayMultiplePages = displayMultiplePages;
	}

	public boolean isImmediateCorrection() {
		return immediateCorrection;
	}

	public void setImmediateCorrection(boolean immediateCorrection) {
		this.immediateCorrection = immediateCorrection;
	}

	public boolean isRandomQuestions() {
		return randomQuestions;
	}

	public void setRandomQuestions(boolean randomQuestions) {
		this.randomQuestions = randomQuestions;
	}

}