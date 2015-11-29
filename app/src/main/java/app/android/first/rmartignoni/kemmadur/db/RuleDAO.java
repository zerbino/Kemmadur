package app.android.first.rmartignoni.kemmadur.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import app.android.first.rmartignoni.kemmadur.model.Rule;

/**
 * Created by rmartignoni on 26/11/2015.
 */
public class RuleDAO {

    private SQLiteDatabase db;

    public RuleDAO(Context context) {
        this.db = KemmadurDbHelper.getInstance(context).getWritableDatabase();
    }

    public RuleDAO(SQLiteDatabase db){
        this.db = db;
    }

    public void insertRule(Rule rule, String languageCode){
        ContentValues contentValuesRule = new ContentValues();
        contentValuesRule.put(KemmadurContract.RuleNameEntry.COLUMN_MUTATION_ID, rule.getId());
        contentValuesRule.put(KemmadurContract.RuleNameEntry.COLUMN_LANGUAGE_FK, languageCode);
        contentValuesRule.put(KemmadurContract.RuleNameEntry.COLUMN_RULE_NAME, rule.getName());
        contentValuesRule.put(KemmadurContract.RuleNameEntry.COLUMN_MUTATION_NAME, rule.getMutatioName());
        db.insert(KemmadurContract.RuleNameEntry.TABLE_NAME, null, contentValuesRule);
        for (String mutationCase : rule.getConditions()) {
            this.insertMutationCase(mutationCase, rule.getId(), languageCode);
        }
    }

    private void insertMutationCase(String mutationCase, long ruleId, String languageCode){
        ContentValues contentValueMutationCase = new ContentValues();
        contentValueMutationCase.put(KemmadurContract.MutationCaseEntry.COLUMN_MUTATION_FK, ruleId);
        contentValueMutationCase.put(KemmadurContract.MutationCaseEntry.COLUMN_LANGUAGE_CODE, languageCode);
        contentValueMutationCase.put(KemmadurContract.MutationCaseEntry.COLUMN_MUTATION_CONDITION, mutationCase);
        db.insert(KemmadurContract.MutationCaseEntry.TABLE_NAME, null, contentValueMutationCase);
    }

}
