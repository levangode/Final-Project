package backend;

import java.sql.Timestamp;

public class QuizDetailedInfo extends QuizInfo{
	private String quiz_category;
	private String quiz_description;
	private int quiz_likes;

	public QuizDetailedInfo(String quiz_name, int times_taken, String quiz_author, Timestamp quiz_date, int quiz_id, String quiz_category,
			String quiz_description, int quiz_likes) {
		super(quiz_name, times_taken, quiz_author, quiz_date, quiz_id);
		this.quiz_category=quiz_category;
		this.quiz_description=quiz_description;
		this.setQuiz_likes(quiz_likes);
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
	@Override
	public String showOnCard(){
		String result = "";
		result=""
			+"<div id=\"card\" class=\"box\" style=\"position:relative; text-align:center;\""
			+"style=\"text-align: left; margin-left: auto; margin-right: auto;\">"
			+"<h3>Name: "+getQuiz_name()+"</h3>"
			+"<p class=\"descr\">Description: "+getQuiz_description()+"</p>"
			+"<p class=\"para\" style=\"left:0; top:0;\">Category: "+getQuiz_category()+"</p>"
			+"<p class=\"para\" style=\"right:0; top:0;\">Author: "+getQuiz_author()+"</p>"
			+"Date Created: "+getQuiz_date()+"<br>"
			+"Times Taken: "+getTimes_taken()+"<br>"
			+"<p class=\"para\" style=\"left:0; bottom:0;\">Likes: "+getQuiz_likes()+"</p>"
			+"<a class=\"btn\" style=\"position: absolute; right:0; bottom:0; padding:5px;\" href=\"QuizInformation.jsp?id="+getQuiz_id()+"\">Check It Out</a>"
			+"</div>";
		return result;
	}
	public int getQuiz_likes() {
		return quiz_likes;
	}
	public void setQuiz_likes(int quiz_likes) {
		this.quiz_likes = quiz_likes;
	}
	
	
}
