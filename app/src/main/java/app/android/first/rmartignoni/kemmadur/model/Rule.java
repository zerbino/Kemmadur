package app.android.first.rmartignoni.kemmadur.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmartignoni on 24/11/2015.
 */
public class Rule {

    private int id;

    private String name;

    private String mutatioName;

    private List<String> conditions;

    public Rule(int id, String name, String mutatioName, List<String> conditions) {
        this.id = id;
        this.name = name;
        this.mutatioName = mutatioName;
        this.conditions = conditions;
    }

    public Rule(){
        this.conditions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMutatioName() {
        return mutatioName;
    }

    public void setMutatioName(String mutatioName) {
        this.mutatioName = mutatioName;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }
}
