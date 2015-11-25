package app.android.first.rmartignoni.kemmadur.db;

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

    private KemmadurDbHelper kemmadurDbHelper;

    public QuestionDAO(KemmadurDbHelper kemmadurDbHelper) {
        this.kemmadurDbHelper = kemmadurDbHelper;
    }

    public QuestionDAO(Context context) {
        this.kemmadurDbHelper = KemmadurDbHelper.getInstance(context);
    }

    public List<Question> getList(int limit) {
        SQLiteDatabase db = this.kemmadurDbHelper.getReadableDatabase();

        String projection[] = {
                KemmadurContract.QuestionEntry._ID,
                KemmadurContract.QuestionEntry.COLUMN_UNMUTATED_WORD,
                KemmadurContract.QuestionEntry.COLUMN_ANSWER,
                KemmadurContract.QuestionEntry.COLUMN_DOTTED_SENTENCE
        };

        Cursor cursor = db.query(
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

    private void getAndSetProposals(Question question) {
        if (question.getId() == -1) return;
        long questionId = question.getId();
        SQLiteDatabase db = this.kemmadurDbHelper.getReadableDatabase();
        String projection[] = {
                KemmadurContract.QuestionProposalEntry.COLUMN_PROPOSAL
        };
        Cursor cursor = db.query(
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

}
