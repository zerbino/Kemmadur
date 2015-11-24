package app.android.first.rmartignoni.kemmadur.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rmartignoni on 10/11/2015.
 */
public class Question {
    private String dottedSentence;
    private String unmutatedWord;
    private String answer;
    private List<String> proposals;
    private int MAX_PROPOSAL_VALUE = 4;

    public Question(String dottedSentence, String unmutatedWord, String answer, List<String> proposals) {
        this.dottedSentence = dottedSentence;
        this.unmutatedWord = unmutatedWord;
        this.answer = answer;
        // Proposal arraylist deep copy
        this.proposals = new ArrayList<>();
        for (String proposal: proposals){
            this.proposals.add(proposal);
        }
    }

    public List<String> getShuffledProposals(){
        List<String> proposals = new ArrayList<>(this.proposals);
        Collections.shuffle(proposals);
        List<String> slicedProposals = new ArrayList<>(this.proposals.subList(0, MAX_PROPOSAL_VALUE - 2));
        slicedProposals.add(this.answer);
        Collections.shuffle(slicedProposals);
        return slicedProposals;
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
