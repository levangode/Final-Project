package quizInfoes;

import java.sql.Timestamp;

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
			Timestamp quiz_date, int quiz_id, String quiz_category, String quiz_description, int quiz_likes,
			String quiz_difficulty, boolean immediate_correction) {
		return new QuizFullSummary(quiz_name, times_taken, quiz_author, quiz_date, quiz_id, quiz_category,
				quiz_description, quiz_likes, quiz_difficulty, immediate_correction);
	}

	public static UserActivity getUserActivity(Timestamp time_finished, Timestamp time_taken, int score, String user) {
		return new UserActivity(time_finished, time_taken, score, user);
	}

	public static HighScore getHighScore(int score, String user) {
		return new HighScore(score, user);
	}
	public static Statistics getStatistics(int count, int avgScore){
		return new Statistics(count, avgScore);
	}
}
