package app.android.first.rmartignoni.kemmadur.db;

import android.provider.BaseColumns;

/**
 * Created by rmartignoni on 24/11/2015.
 */
public class KemmadurContract {

    public KemmadurContract() {
    }

    public static abstract class RuleNameEntry implements BaseColumns {
        public static final String TABLE_NAME = "rule_name_i18n";
        public static final String COLUMN_MUTATION_ID = "mutation_id";
        public static final String COLUMN_LANGUAGE_FK = "language_id";
        public static final String COLUMN_RULE_NAME = "rule_name";
        public static final String COLUMN_MUTATION_NAME = "mutation_name";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_MUTATION_ID + " INTEGER," +
                COLUMN_LANGUAGE_FK + " TEXT," +
                COLUMN_RULE_NAME + " TEXT," +
                COLUMN_MUTATION_NAME + " TEXT," +
                "PRIMARY KEY (" + COLUMN_MUTATION_ID + "," + COLUMN_LANGUAGE_FK + "));";
    }

    public static abstract class MutationCaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "mutation_case_i18n";
        public static final String COLUMN_MUTATION_FK = "mutation_fk";
        public static final String COLUMN_LANGUAGE_CODE = "language_id";
        public static final String COLUMN_MUTATION_CONDITION = "mutation_condition";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_MUTATION_FK + " INTEGER," +
                        COLUMN_LANGUAGE_CODE + " TEXT," +
                COLUMN_MUTATION_CONDITION + " TEXT);";
        public static final String SQL_CREATE_INDEX =
                "CREATE INDEX IX_MUTATION_CASE_I18N_MUTATION_FK_LANGUAGE_ID ON mutation_case_i18n(" + COLUMN_MUTATION_FK + "," + COLUMN_LANGUAGE_CODE + ")";
    }

    public static abstract class QuestionEntry implements BaseColumns {
        public static final String TABLE_NAME = "questions";
        public static final String COLUMN_DOTTED_SENTENCE = "dotted_sentence";
        public static final String COLUMN_UNMUTATED_WORD = "unmutated_word";
        public static final String COLUMN_ANSWER = "answer";
        public static final String COLUMN_RULE_FK = "rule_fk";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                COLUMN_DOTTED_SENTENCE + " TEXT, " +
                COLUMN_UNMUTATED_WORD + " TEXT, " +
                COLUMN_ANSWER + " TEXT," +
                COLUMN_RULE_FK + " INTEGER)";
        public static final String SQL_DELETE_ENTRIES = "DELETE FROM " + TABLE_NAME;
    }

    public static abstract class QuestionProposalEntry implements BaseColumns {
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
