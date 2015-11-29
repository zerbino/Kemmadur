package app.android.first.rmartignoni.kemmadur.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.android.first.rmartignoni.kemmadur.model.Question;

/**
 * Created by rmartignoni on 25/11/2015.
 */
public class QuestionDAO {

    private SQLiteDatabase db;

    public QuestionDAO(Context context) {
        this.db = KemmadurDbHelper.getInstance(context).getWritableDatabase();
    }

    public QuestionDAO(SQLiteDatabase db){
        this.db = db;
    }

    public List<Question> getList(int limit) {

        String projection[] = {
                KemmadurContract.QuestionEntry._ID,
                KemmadurContract.QuestionEntry.COLUMN_UNMUTATED_WORD,
                KemmadurContract.QuestionEntry.COLUMN_ANSWER,
                KemmadurContract.QuestionEntry.COLUMN_DOTTED_SENTENCE
        };

        Cursor cursor = this.db.query(
                KemmadurContract.QuestionEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                "" + limit
        );

        ArrayList<Question> questions = new ArrayList<>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Question question = new Question();
            question.setId(cursor.getLong(cursor.getColumnIndexOrThrow(KemmadurContract.QuestionEntry._ID)));
            question.setUnmutatedWord(cursor.getString(cursor.getColumnIndexOrThrow(KemmadurContract.QuestionEntry.COLUMN_UNMUTATED_WORD)));
            question.setAnswer(cursor.getString(cursor.getColumnIndexOrThrow(KemmadurContract.QuestionEntry.COLUMN_ANSWER)));
            question.setDottedSentence(cursor.getString(cursor.getColumnIndexOrThrow(KemmadurContract.QuestionEntry.COLUMN_DOTTED_SENTENCE)));
            questions.add(question);
        }

        for (Question question : questions) {
            this.getAndSetProposals(question);
        }

        return questions;
    }

    /**
     * Get the proposals corresponding to the question and add them to the Question object
     * @param question
     */
    private void getAndSetProposals(Question question) {
        if (question.getId() == -1) return;
        long questionId = question.getId();
        String projection[] = {
                KemmadurContract.QuestionProposalEntry.COLUMN_PROPOSAL
        };
        Cursor cursor = this.db.query(
                KemmadurContract.QuestionProposalEntry.TABLE_NAME,
                projection,
                KemmadurContract.QuestionProposalEntry.COLUMN_QUESTION_FK + "=?",
                new String[]{"" + questionId},
                null,
                null,
                null
        );

        ArrayList<String> proposals = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String proposal = cursor.getString(cursor.getColumnIndexOrThrow(KemmadurContract.QuestionProposalEntry.COLUMN_PROPOSAL));
            proposals.add(proposal);
        }
        question.setProposals(proposals);
    }

    public void insertQuestion(Question question, long ruleId){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KemmadurContract.QuestionEntry.COLUMN_DOTTED_SENTENCE, question.getDottedSentence());
        contentValues.put(KemmadurContract.QuestionEntry.COLUMN_UNMUTATED_WORD, question.getUnmutatedWord());
        contentValues.put(KemmadurContract.QuestionEntry.COLUMN_ANSWER, question.getAnswer());
        contentValues.put(KemmadurContract.QuestionEntry.COLUMN_RULE_FK, ruleId);
        long questionInsertedId = this.db.insert(KemmadurContract.QuestionEntry.TABLE_NAME, null, contentValues);
        for (String proposal : question.getProposals()) {
            this.insertProposal(proposal, questionInsertedId);
        }
    }

    private void insertProposal(String proposal, long questionId){
        ContentValues contentValuesProposal1 = new ContentValues();
        contentValuesProposal1.put(KemmadurContract.QuestionProposalEntry.COLUMN_QUESTION_FK, questionId);
        contentValuesProposal1.put(KemmadurContract.QuestionProposalEntry.COLUMN_PROPOSAL, proposal);
        this.db.insert(KemmadurContract.QuestionProposalEntry.TABLE_NAME, null, contentValuesProposal1);
    }

}
