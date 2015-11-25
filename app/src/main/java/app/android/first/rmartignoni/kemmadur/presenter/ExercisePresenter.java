package app.android.first.rmartignoni.kemmadur.presenter;

import android.os.Handler;
import android.util.Log;

import app.android.first.rmartignoni.kemmadur.ExerciseActivity;
import app.android.first.rmartignoni.kemmadur.model.AnswerDetail;
import app.android.first.rmartignoni.kemmadur.model.ExerciseGenerator;
import app.android.first.rmartignoni.kemmadur.model.Game;
import app.android.first.rmartignoni.kemmadur.model.Questions;

/**
 * Created by rmartignoni on 13/11/2015.
 */
public class ExercisePresenter {

    private ExerciseActivity exerciseActivity;

    private static final String TAG = ExercisePresenter.class.getSimpleName();

    private Game game;

    public ExercisePresenter(ExerciseActivity exerciseActivity) {
        this.exerciseActivity = exerciseActivity;
    }

    public void onCreate() {
        ExerciseGenerator exerciseGenerator = new ExerciseGenerator(this.exerciseActivity);
        Questions questions = exerciseGenerator.getQuestions();
        this.game = new Game(10, questions);
        this.exerciseActivity.populateView(this.game);
    }

    public void validate(String proposal) {
        AnswerDetail rightAnswer = this.game.giveAnswer(proposal);
        this.exerciseActivity.displayToastFeedback(rightAnswer.isRightAnswer());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               nextQuestion();
            }
        }, 800);
    }

    private void nextQuestion(){
        try {
            this.game.nextQuestion();
            this.exerciseActivity.populateView(this.game);
        } catch (Game.QuestionEndException e) {
            Log.v(TAG, "End of the exercise");
        }
    }

}