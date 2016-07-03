package quizInfoes;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class Statistics implements DrawableInfo{
	private int count;
	private double avgScore;
	public Statistics(int count, double avgScore){
		this.count=count;
		this.setAvgScore(avgScore);
	}
	@Override
	public void showOnCard(JspWriter out) {
		try {
			out.write("<p>Users Took Quiz: "+count+"</p>");
			out.write("<p>Average User Score: "+avgScore+"</p>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
