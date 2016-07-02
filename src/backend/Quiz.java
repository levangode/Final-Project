package backend;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import questions.Question;

public class Quiz {
	private String quiz_name;
	private String quiz_description;
	private String quiz_author;
	private int quiz_likes;
	private int time_limit; //(minutes)
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
	public static Quiz retrieveQuiz(HttpServletRequest request, HttpServletResponse response){
		String quiz_name = request.getParameter("quiz_name");
		String quiz_description = request.getParameter("quiz_description");
		String quiz_category = request.getParameter("categories");
		String quiz_difficulty = request.getParameter("difficulty");
		String quiz_author = (String)request.getSession().getAttribute("user_name");
		boolean random_questions = false;
		boolean immediate_correction = false;
		boolean multiplePage = false;
		String show_on = "";
		if(request.getParameter("Random Questions") != null)
			random_questions = true;
		if(request.getParameter("Immediate Correction") != null)
			immediate_correction = true;
		show_on = request.getParameter("Show on");
		if(show_on.equals("Multiple Pages")){
			multiplePage = true;
		}
		Quiz newOne=QuizFactory.getQuiz(quiz_name, quiz_description, quiz_author, 0, quiz_category, quiz_difficulty, 0, multiplePage, immediate_correction, random_questions);
		return newOne;
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
				+ "\n" + "random: " + randomQuestions + "\n ============================";
		
		for(int i=0; i<questions.size(); i++){
			System.out.println(questions.get(i));
			for(int j=0; j<questions.get(i).getAnswers().size(); j++){
				System.out.println(questions.get(i).getAnswers().get(j));
			}
		}
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