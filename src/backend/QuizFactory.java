package backend;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import questions.Question;

public class QuizFactory {
	public static Quiz getQuiz(String quiz_name, String quiz_description, String quiz_author, int quiz_likes,
			String quiz_category, String quiz_difficulty, int times_taken, boolean displayMultiplePages,
			boolean immediateCorrection, boolean randomQuestions, int time_limit) {
		ArrayList<Question> questions = new ArrayList<Question>();
		Date d = new Date();
		Timestamp date_created_timestamp = new Timestamp(d.getTime());
		return new Quiz(quiz_name, quiz_description, quiz_author, quiz_likes, date_created_timestamp, quiz_category,
				quiz_difficulty, times_taken, questions, displayMultiplePages, immediateCorrection, randomQuestions,
				time_limit);
	}

}
