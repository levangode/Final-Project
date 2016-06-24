package questions;

import java.util.ArrayList;

import answers.AnswerParent;

public class MultipleChoiceQuestion extends Question {
	private int answers_to_show;
	private int answers_to_be_correct;

	public MultipleChoiceQuestion(String question_text, String question_type, String question_description,
			long question_time_limit, int question_score, int answers_to_show, int answers_to_be_correct,
			ArrayList<AnswerParent> answers) {
		super(question_text, question_type, question_description, question_time_limit, question_score, answers);
		this.answers_to_show = answers_to_show;
		this.answers_to_be_correct = answers_to_be_correct;
	}
	
	public String toString(){
		String result = super.toString()+"answers to show: "+answers_to_show+"\n"+"answers correct: "+answers_to_be_correct+"\n";
		return result;
		
	}
	
	public int getNumanswersdisplay(){
		return answers_to_show;
	}
	
	public int getNumanswerscorrect(){
		return answers_to_be_correct;
	}

}
