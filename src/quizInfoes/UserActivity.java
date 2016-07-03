package quizInfoes;

import java.sql.Timestamp;

import javax.servlet.jsp.JspWriter;

public class UserActivity implements DrawableInfo {
	private Timestamp time_finished;
	private int time_taken;
	private double score;
	private String user;

	public UserActivity(Timestamp time_finished, int time_taken, double score, String user) {
		this.setTime_finished(time_finished);
		this.setTime_taken(time_taken);
		this.setScore(score);
		this.setUser(user);
	}

	@Override
	public void showOnCard(JspWriter out) {
		try {
			out.write("<p>Time finished: " + time_finished + "<br>Score: " + score+"</p>");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Timestamp getTime_finished() {
		return time_finished;
	}

	public void setTime_finished(Timestamp time_finished) {
		this.time_finished = time_finished;
	}

	public int getTime_taken() {
		return time_taken;
	}

	public void setTime_taken(int time_taken) {
		this.time_taken = time_taken;
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
