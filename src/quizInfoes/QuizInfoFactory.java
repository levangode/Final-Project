package quizInfoes;

import java.sql.Timestamp;
import java.util.ArrayList;

public class QuizInfoFactory {
	public static QuizInfo getQuizInfo(String quiz_name, int times_taken, String quiz_author, Timestamp quiz_date,
			int quiz_id) {
		return new QuizInfo(quiz_name, times_taken, quiz_author, quiz_date, quiz_id);
	}

	public static QuizDetailedInfo getDetailedInfo(String quiz_name, int times_taken, String quiz_author,
			Timestamp quiz_date, int quiz_id, String quiz_category, String quiz_description, int quiz_likes) {
		return new QuizDetailedInfo(quiz_name, times_taken, quiz_author, quiz_date, quiz_id, quiz_category,
				quiz_description, quiz_likes);
	}

	public static QuizFullSummary getFullSummary(String quiz_name, int times_taken, String quiz_author,
			Timestamp quiz_date, int quiz_id, String quiz_category, String quiz_description, int quiz_likes, String quiz_difficulty, boolean immediate_correction) {
		ArrayList<QuizInfo> usersPastPerformance = new ArrayList<QuizInfo>();
		ArrayList<String> highestPerformers = new ArrayList<String>();
		ArrayList<String> performersOfTheDay = new ArrayList<String>();
		ArrayList<String> statistics = new ArrayList<String>();
		return new QuizFullSummary(quiz_name, times_taken, quiz_author, quiz_date, quiz_id, quiz_category,
				quiz_description, quiz_likes, quiz_difficulty, immediate_correction);
	}
}
