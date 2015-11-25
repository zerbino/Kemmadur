package app.android.first.rmartignoni.kemmadur.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rmartignoni on 10/11/2015.
 */
public class Question {
    private long id = -1;
    /**
     * The incomplete sentence to be displayed to the user, with dots in place of the word to be mutated.
     */
    private String dottedSentence;
    /**
     * The word to be mutated, consideraring it should be place in the dots of the dotted sentence, in its unmutated form.
     */
    private String unmutatedWord;
    /**
     * The right answer to the question, i.e. the mutated word.
     */
    private String answer;
    /**
     * The list of wrong proposals to display with the right answer.
     */
    private List<String> proposals;
    /**
     * The max number of proposals to be displayed  when performing the exercise.
     */
    private int MAX_PROPOSAL_VALUE = 4;
    /**
     * The grammatical rule linked to that question.
     */
    private Rule rule;

    public Question() {
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

    public Rule getRule() {
        return rule;
    }

    public void setDottedSentence(String dottedSentence) {
        this.dottedSentence = dottedSentence;
    }

    public void setUnmutatedWord(String unmutatedWord) {
        this.unmutatedWord = unmutatedWord;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getProposals() {
        return proposals;
    }

    public void setProposals(List<String> proposals) {
        this.proposals = proposals;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean giveAnswer(String answer){
        return this.answer.toLowerCase().equals(answer.toLowerCase());
    }

    public List<String> getShuffledProposals(){
        List<String> proposals = new ArrayList<>(this.proposals);
        Collections.shuffle(proposals);
        try {
            proposals = new ArrayList<>(proposals.subList(0, MAX_PROPOSAL_VALUE - 2));
        }
        catch(IndexOutOfBoundsException e){
            // That means the MAX_PROPOSAL_VALUE is greater than 1 + proposals.size(), i.e. we want to
            // display more values that there is actually (considering the answer and the list of proposals).
        }
        proposals.add(this.answer);
        Collections.shuffle(proposals);
        return proposals;
    }

}
