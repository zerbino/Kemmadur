package app.android.first.rmartignoni.kemmadur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.android.first.rmartignoni.kemmadur.model.Game;
import app.android.first.rmartignoni.kemmadur.model.Question;
import app.android.first.rmartignoni.kemmadur.model.Questions;
import app.android.first.rmartignoni.kemmadur.presenter.ExercisePresenter;

public class ExerciseActivity extends AppCompatActivity {

    private ExercisePresenter exercisePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        this.exercisePresenter = new ExercisePresenter(this);
        this.exercisePresenter.onCreate();
    }

    public void populateView(Game game){
        Questions questions = game.getQuestions();
        Question currentQuestion = game.getCurrentQuestion();
        List<String> proposals = game.getProposals();
        TextView instructionTextView = (TextView) this.findViewById(R.id.exercise_instruction_text);
        instructionTextView.setText(questions.getInstructions());
        TextView dottedSentenceTextView = (TextView) this.findViewById(R.id.exercise_dotted_sentence_text);
        dottedSentenceTextView.setText(currentQuestion.getDottedSentence());
        TextView wordToMutateTextView = (TextView) this.findViewById(R.id.word_to_mutate);
        wordToMutateTextView.setText(currentQuestion.getUnmutatedWord());
        LinearLayout buttonProposalLayout = (LinearLayout) findViewById(R.id.exercise_proposal_buttons);
        buttonProposalLayout.removeAllViews();
        for (final String proposal : proposals) {
            Button proposalButton = new Button(this);
            proposalButton.setText(proposal);
            buttonProposalLayout.addView(proposalButton);
            proposalButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exercisePresenter.validate(proposal);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayToastFeedback(boolean rightAnswer){
        // TODO : the well done message should be in resources for internationalization
        LayoutInflater inflater = getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.answer_toast,
                (ViewGroup) findViewById(R.id.answer_toast_root));
        ImageView toastImageView = (ImageView) toastLayout.findViewById(R.id.answer_toast_imageview);
        if (rightAnswer){
            toastImageView.setImageResource(R.drawable.ic_message_ok);
        }
        else{
            toastImageView.setImageResource(R.drawable.ic_message_red_cross);
        }
        Toast toast = new Toast(getApplicationContext());
        toast.setView(toastLayout);
        toast.setGravity(Gravity.TOP, 0, 150);
        toast.show();
    }

}
