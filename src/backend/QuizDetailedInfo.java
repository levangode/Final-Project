package backend;

import java.sql.Timestamp;

public class QuizDetailedInfo extends QuizInfo{
	private String quiz_category;
	private String quiz_description;

	public QuizDetailedInfo(String quiz_name, int times_taken, String quiz_author, Timestamp quiz_date, int quiz_id, String quiz_category,
			String quiz_description) {
		super(quiz_name, times_taken, quiz_author, quiz_date, quiz_id);
		this.setQuiz_category(quiz_category);
		this.setQuiz_description(quiz_description);
	}
	public String getQuiz_category() {
		return quiz_category;
	}
	public void setQuiz_category(String quiz_category) {
		this.quiz_category = quiz_category;
	}
	public String getQuiz_description() {
		return quiz_description;
	}
	public void setQuiz_description(String quiz_description) {
		this.quiz_description = quiz_description;
	}
	public String showOnCard(){
		String result = "";
		
		return result;
	}
	
	
}
