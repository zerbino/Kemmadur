package app.android.first.rmartignoni.kemmadur.db;

import android.provider.BaseColumns;

/**
 * Created by rmartignoni on 24/11/2015.
 */
public class KemmadurContract {

    public KemmadurContract() {
    }

    public static abstract class QuestionEntry implements BaseColumns{
        public static final String TABLE_NAME = "questions";
        public static final String COLUMN_DOTTED_SENTENCE = "dotted_sentence";
        public static final String COLUMN_UNMUTATED_WORD = "unmutated_word";
        public static final String COLUMN_ANSWER = "answer";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                COLUMN_DOTTED_SENTENCE + " TEXT, " +
                COLUMN_UNMUTATED_WORD + " TEXT, " +
                COLUMN_ANSWER + " TEXT)";
        public static final String SQL_DELETE_ENTRIES = "DELETE FROM " + TABLE_NAME;
    }

    public static abstract class QuestionProposalEntry implements BaseColumns{
        public static final String TABLE_NAME = "question_proposals";
        public static final String COLUMN_QUESTION_FK = "question_fk";
        public static final String COLUMN_PROPOSAL = "proposal";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                COLUMN_QUESTION_FK + " TEXT, " +
                COLUMN_PROPOSAL + " TEXT)";
        public static final String SQL_DELETE_ENTRIES = "DELETE FROM " + TABLE_NAME;
    }
}
