package app.android.first.rmartignoni.kemmadur.model;

import java.util.List;

/**
 * Created by rmartignoni on 10/11/2015.
 */
public class Questions {

    private String instructions;

    private List<Question> questions;

    public Questions(String instructions, List<Question> questions) {
        this.instructions = instructions;
        this.questions = questions;
    }

    public String getInstructions() {
        return instructions;
    }

    private List<Question> getQuestions() {
        return questions;
    }

    public Question randomQuestion(){
        int randIndex = (int) Math.floor(Math.random() * this.questions.size());
        return questions.get(randIndex);
    }
}
