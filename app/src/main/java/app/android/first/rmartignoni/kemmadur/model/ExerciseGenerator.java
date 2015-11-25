package app.android.first.rmartignoni.kemmadur.model;

import android.content.Context;

import java.util.List;

import app.android.first.rmartignoni.kemmadur.db.QuestionDAO;

/**
 * Created by rmartignoni on 10/11/2015.
 */
public class ExerciseGenerator {

    public Questions questions;

    public ExerciseGenerator(Context context) {
        QuestionDAO questionDAO = new QuestionDAO(context);
        List<Question> questionList = questionDAO.getList(10);
        this.questions = new Questions("Complete the sentence by mutating the word correctly.", questionList);
    }

    public Questions getQuestions(){
        return this.questions;
    }
}
