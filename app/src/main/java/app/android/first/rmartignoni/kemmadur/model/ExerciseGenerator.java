package app.android.first.rmartignoni.kemmadur.model;

import java.util.ArrayList;

/**
 * Created by rmartignoni on 10/11/2015.
 */
public class ExerciseGenerator {

    public Questions questions;

    public ExerciseGenerator() {
        ArrayList<Question> questionList = new ArrayList<>();
        Question question1 = new Question("Ma ... eo !", "tad", "z");
        Question question2 = new Question("Da ... eo !", "tad", "d");
        Question exercise3 = new Question("Da ... eo !", "mec'h", "v");
        Question question4 = new Question("O ... emaon !", "debrin", "t");
        questionList.add(question1);
        questionList.add(question2);
        questionList.add(exercise3);
        questionList.add(question4);
        this.questions = new Questions("Complete the sentence by mutating the word correctly.", questionList);
    }

    public Questions getQuestions(){
        return this.questions;
    }
}
