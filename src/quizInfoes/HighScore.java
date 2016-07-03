package quizInfoes;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class HighScore implements DrawableInfo{
	private double score;
	private String user;
	
	public HighScore(double score, String user){
		this.score=score;
		this.user=user;
	}
	@Override
	public void showOnCard(JspWriter out) {
		try {
			out.print("<p>User: "+user+" - Score: "+score);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
