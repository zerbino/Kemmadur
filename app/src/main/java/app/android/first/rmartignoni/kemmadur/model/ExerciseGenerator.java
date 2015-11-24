package app.android.first.rmartignoni.kemmadur.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmartignoni on 10/11/2015.
 */
public class ExerciseGenerator {

    public Questions questions;

    public ExerciseGenerator() {
        ArrayList<Question> questionList = new ArrayList<>();
        List<String> proposals1 = new ArrayList<>();
        proposals1.add("t");
        proposals1.add("d");
        Question question1 = new Question("Ma ... eo !", "tad", "z", proposals1);
        List<String> proposals2 = new ArrayList<>();
        proposals2.add("z");
        proposals2.add("t");
        Question question2 = new Question("Da ... eo !", "tad", "d", proposals2);
        List<String> proposals3 = new ArrayList<>();
        proposals3.add("m");
        proposals3.add("b");
        Question question3 = new Question("Da ... eo !", "mec'h", "v", proposals3);
        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        this.questions = new Questions("Complete the sentence by mutating the word correctly.", questionList);
    }

    public Questions getQuestions(){
        return this.questions;
    }
}
