package app.android.first.rmartignoni.kemmadur.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rmartignoni on 25/11/2015.
 */
public class KemmadurDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Kemmadur.db";

    private static KemmadurDbHelper singleton;

    private KemmadurDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static KemmadurDbHelper getInstance(Context context){
        if (singleton == null) {
            return new KemmadurDbHelper(context.getApplicationContext());
        }
        return singleton;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table creation
        db.execSQL(KemmadurContract.QuestionEntry.SQL_CREATE_ENTRIES);
        db.execSQL(KemmadurContract.QuestionProposalEntry.SQL_CREATE_ENTRIES);

        //Insert some values
        // TODO : find a better way to have a complete table with many entries on app creation
        ContentValues contentValues = new ContentValues();
        contentValues.put(KemmadurContract.QuestionEntry.COLUMN_DOTTED_SENTENCE, "Ma ... eo !");
        contentValues.put(KemmadurContract.QuestionEntry.COLUMN_UNMUTATED_WORD, "tad");
        contentValues.put(KemmadurContract.QuestionEntry.COLUMN_ANSWER, "z");
        long questionInsertedId = db.insert(KemmadurContract.QuestionEntry.TABLE_NAME, null, contentValues);

        ContentValues contentValuesProposal1 = new ContentValues();
        contentValuesProposal1.put(KemmadurContract.QuestionProposalEntry.COLUMN_QUESTION_FK, questionInsertedId);
        contentValuesProposal1.put(KemmadurContract.QuestionProposalEntry.COLUMN_PROPOSAL, "t");
        db.insert(KemmadurContract.QuestionProposalEntry.TABLE_NAME, null, contentValuesProposal1);

        ContentValues contentValuesProposal2 = new ContentValues();
        contentValuesProposal2.put(KemmadurContract.QuestionProposalEntry.COLUMN_QUESTION_FK, questionInsertedId);
        contentValuesProposal2.put(KemmadurContract.QuestionProposalEntry.COLUMN_PROPOSAL, "d");
        db.insert(KemmadurContract.QuestionProposalEntry.TABLE_NAME, null, contentValuesProposal2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Nothing to do here.
    }

}
