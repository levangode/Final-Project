package database;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import backend.*;

public class sampleQuiz {
	public sampleQuiz() {

	}

	public ArrayList<Question> buildQuestions() {
		ArrayList<Answer> answers = new ArrayList<>();
		Question a = new Question("What is one plus two?", QuestionTypes.MultipleChoice, "Some math", -1, answers);
		Answer aa = new Answer("three", "kinda correct", true);
		Answer ab = new Answer("two", "meh", false);
		Answer ac = new Answer("3", "blah", true);

		a.addAnswer(aa);
		a.addAnswer(ab);
		a.addAnswer(ac);

		ArrayList<Answer> answers2 = new ArrayList<>();
		Question b = new Question("3 X 7 = ?", QuestionTypes.MultipleChoice, "more math", -1, answers2);

		Answer ba = new Answer("21", "kinda true", true);
		Answer bb = new Answer("30", "blah", false);
		b.addAnswer(ba);
		b.addAnswer(bb);

		ArrayList<Question> questions = new ArrayList<>();
		questions.add(a);
		questions.add(b);
		return questions;

	}

	public Quiz getSampleQuiz() {
		Quiz quiz = new Quiz("math warmup", "just simple math", "mesxi", 999,
				new Timestamp(new java.util.Date().getTime()), "Math", "Ez", 0, buildQuestions(), false, false, false);
		quiz.setQuestions(buildQuestions());
		return quiz;
	}

	public void addQuiz() {

	}

}
