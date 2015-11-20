package app.android.first.rmartignoni.kemmadur.presenter;
import android.os.Handler;

import app.android.first.rmartignoni.kemmadur.ExerciseActivity;
import app.android.first.rmartignoni.kemmadur.model.ExerciseGenerator;
import app.android.first.rmartignoni.kemmadur.model.Question;
import app.android.first.rmartignoni.kemmadur.model.Questions;

/**
 * Created by rmartignoni on 13/11/2015.
 */
public class ExercisePresenter {

    private ExerciseActivity exerciseActivity;

    private Questions questions;
    private Question currentQuestion;

    public ExercisePresenter(ExerciseActivity exerciseActivity) {
        this.exerciseActivity = exerciseActivity;
    }

    public void onCreate() {
        ExerciseGenerator exerciseGenerator = new ExerciseGenerator();
        this.questions = exerciseGenerator.getQuestions();
        this.currentQuestion = this.questions.randomQuestion();
        this.exerciseActivity.populateView(this.questions, this.currentQuestion);
    }

    public void validate() {
        String proposal = this.exerciseActivity.getProposal();
        boolean rightAnswer = this.currentQuestion.giveAnswer(proposal);
        this.exerciseActivity.displayToastFeedback(rightAnswer);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               nextQuestion();
            }
        }, 800);
    }

    private void nextQuestion(){
        this.currentQuestion = this.questions.randomQuestion();
        this.exerciseActivity.populateView(this.questions, this.currentQuestion);
        this.exerciseActivity.resetEditText();

    }
}