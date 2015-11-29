package app.android.first.rmartignoni.kemmadur.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import app.android.first.rmartignoni.kemmadur.model.Question;
import app.android.first.rmartignoni.kemmadur.model.Rule;

/**
 * Created by rmartignoni on 25/11/2015.
 */
public class KemmadurDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Kemmadur.db";

    private static KemmadurDbHelper singleton;

    private Context context;

    private KemmadurDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static KemmadurDbHelper getInstance(Context context){
        if (singleton == null) {
            singleton = new KemmadurDbHelper(context.getApplicationContext());
        }
        return singleton;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        QuestionDAO questionDAO = new QuestionDAO(db);
        RuleDAO ruleDAO = new RuleDAO(db);

        // Table creation
        db.execSQL(KemmadurContract.RuleNameEntry.SQL_CREATE_ENTRIES);
        db.execSQL(KemmadurContract.MutationCaseEntry.SQL_CREATE_ENTRIES);
        db.execSQL(KemmadurContract.MutationCaseEntry.SQL_CREATE_INDEX);
        db.execSQL(KemmadurContract.QuestionEntry.SQL_CREATE_ENTRIES);
        db.execSQL(KemmadurContract.QuestionProposalEntry.SQL_CREATE_ENTRIES);
        db.execSQL(KemmadurContract.QuestionProposalEntry.SQL_CREATE_ENTRIES);

        //Insert some values
        // TODO : find a better way to have a complete table with many entries on app creation
        Rule rule1  = new Rule();
        rule1.setId(1);
        rule1.setName("Règle n° 1");
        rule1.setMutatioName("Mutation adoucissante");
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("après da, dre, war, dindan, ...");
        conditions.add("après daou et div (2)");
        conditions.add("après pe");
        rule1.setConditions(conditions);
        ruleDAO.insertRule(rule1, "fr");

        Rule rule2 = new Rule();
        rule2.setId(2);
        rule2.setName("Règle n°2");
        rule2.setMutatioName("Mutation par spiration");
        ruleDAO.insertRule(rule2, "fr");

        Question question = new Question();
        question.setDottedSentence("Da ... eo !");
        question.setUnmutatedWord("tad");
        question.setAnswer("d");
        List<String> proposals = new ArrayList<>();
        proposals.add("t");
        proposals.add("z");
        question.setProposals(proposals);

        Question question2 = new Question();
        question2.setDottedSentence("Daou ... am eus !");
        question2.setUnmutatedWord("tad");
        question2.setAnswer("d");
        List<String> proposals2 = new ArrayList<>();
        proposals2.add("t");
        proposals2.add("z");
        question2.setProposals(proposals2);

        Question question3 = new Question();
        question3.setDottedSentence("Da ... eo !");
        question3.setUnmutatedWord("mec'h");
        question3.setAnswer("v");
        List<String> proposals3 = new ArrayList<>();
        proposals3.add("m");
        proposals3.add("b");
        question3.setProposals(proposals3);

        questionDAO.insertQuestion(question, 1);
        questionDAO.insertQuestion(question2, 1);
        questionDAO.insertQuestion(question3, 1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Nothing to do here.
    }

}
