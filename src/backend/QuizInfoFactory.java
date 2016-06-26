package backend;

import java.sql.Timestamp;

public class QuizInfoFactory {
	public static QuizInfo getQuizInfo(String quiz_name, int times_taken, String quiz_author, Timestamp quiz_date, int quiz_id){
		return new QuizInfo(quiz_name, times_taken, quiz_author, quiz_date, quiz_id);
	}
}
