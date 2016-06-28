package quizInfoes;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class Statistics implements DrawableInfo{
	private int count;
	private int avgScore;
	public Statistics(int count, int avgScore){
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
	public int getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(int avgScore) {
		this.avgScore = avgScore;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
