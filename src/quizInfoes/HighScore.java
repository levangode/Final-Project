package quizInfoes;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class HighScore implements DrawableInfo{
	private int score;
	private String user;
	
	public HighScore(int score, String user){
		this.setScore(score);
		this.setUser(user);
	}
	@Override
	public void showOnCard(JspWriter out) {
		try {
			out.print("<p>User: "+user+" - Score: "+score);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
