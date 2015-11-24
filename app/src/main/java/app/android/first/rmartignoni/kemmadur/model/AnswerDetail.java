package app.android.first.rmartignoni.kemmadur.model;

/**
 * Created by rmartignoni on 23/11/2015.
 */
public class AnswerDetail{

    private boolean isRightAnswer;

    private String rightAnswer;

    public AnswerDetail(boolean isRightAnswer, String rightAnswer) {
        this.isRightAnswer = isRightAnswer;
        this.rightAnswer = rightAnswer;
    }

    public boolean isRightAnswer() {
        return isRightAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}