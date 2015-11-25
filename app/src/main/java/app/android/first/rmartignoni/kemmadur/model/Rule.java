package app.android.first.rmartignoni.kemmadur.model;

/**
 * Created by rmartignoni on 24/11/2015.
 */
public class Rule {

    private int id;

    private String rule;

    public Rule(int id, String rule) {
        this.id = id;
        this.rule = rule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
