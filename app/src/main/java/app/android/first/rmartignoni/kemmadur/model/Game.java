package app.android.first.rmartignoni.kemmadur.model;

import java.util.List;

/**
 * Created by rmartignoni on 13/11/2015.
 */
public class Game {

    private int numberQuestions;

    private Questions questions;

    private Question currentQuestion;

    private int currentQuestionIndex;

    private List<String> proposals;

    public Game(int numberQuestions, Questions questions) {
        this.numberQuestions = numberQuestions;
        this.questions = questions;
        this.currentQuestion = questions.randomQuestion();
        this.currentQuestionIndex = 0;
        this.proposals = currentQuestion.getShuffledProposals();
    }

    public int getNumberQuestions() {
        return numberQuestions;
    }

    public Questions getQuestions() {
        return questions;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public List<String> getProposals(){
        return this.proposals;
    }

    public AnswerDetail giveAnswer(String answer){
        boolean rightAnswer = this.currentQuestion.giveAnswer(answer);
        return new AnswerDetail(rightAnswer, this.currentQuestion.getAnswer());
    }

    public Question nextQuestion() throws QuestionEndException{
        this.currentQuestionIndex ++;
        if (this.currentQuestionIndex > this.getNumberQuestions() - 1) {
            throw new QuestionEndException();
        }
        this.currentQuestion = this.questions.randomQuestion();
        this.proposals = this.currentQuestion.getShuffledProposals();
        return this.currentQuestion;
    }

    public class QuestionEndException extends Exception{
        public QuestionEndException(){
            super("The end of the game is reached");
        }
    }
}
