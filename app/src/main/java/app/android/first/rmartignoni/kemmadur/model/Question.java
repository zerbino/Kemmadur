package app.android.first.rmartignoni.kemmadur.model;

/**
 * Created by rmartignoni on 10/11/2015.
 */
public class Question {
    private String dottedSentence;
    private String unmutatedWord;
    private String answer;

    public Question(String dottedSentence, String unmutatedWord, String answer) {
        this.dottedSentence = dottedSentence;
        this.unmutatedWord = unmutatedWord;
        this.answer = answer;
    }

    public String getDottedSentence() {
        return dottedSentence;
    }

    public String getUnmutatedWord() {
        return unmutatedWord;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean giveAnswer(String answer){
        return this.answer.toLowerCase().equals(answer.toLowerCase());
    }
}
