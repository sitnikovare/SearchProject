package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QueryModifier {
    public String query_before;
    public String query_after;
    public HashMap<String, ArrayList<String>> specialKeys;

    public HashMap<String, ArrayList<Integer>> specialKeysEntries;

    public QueryModifier() {}

    public QueryModifier(String query) {
        this.query_before = query;
        setBasicSpecialKeys();
        replaceSpecialKeys();
    }

    public void setQuery(String query) {
        this.query_before = query;
    }

    public String getBeforeQuery() {
        return this.query_before;
    }

    public String getAfterQuery() {
        return this.query_after;
    }

    public HashMap<String, ArrayList<Integer>> getSpecialKeysEntries() {
        return this.specialKeysEntries;
    }

    public void setBasicSpecialKeys() {
        ArrayList<String> wordsTitle = new ArrayList<>();
        wordsTitle.add("серия");
        wordsTitle.add("эпизод");
        wordsTitle.add("название");

        ArrayList<String> wordsEnemy = new ArrayList<>();
        wordsEnemy.add("враги");
        wordsEnemy.add("враг");

        ArrayList<String> wordsCompanions = new ArrayList<>();
        wordsCompanions.add("компаньоны");
        wordsCompanions.add("компаньон");
        wordsCompanions.add("спутники");
        wordsCompanions.add("спутник");

        ArrayList<String> wordsDoctor = new ArrayList<>();
        wordsDoctor.add("доктор ");
        ArrayList<String> wordsAND = new ArrayList<>();
        wordsAND.add(" и ");
        ArrayList<String> wordsOR = new ArrayList<>();
        wordsOR.add(" или ");
        ArrayList<String> wordsNOT = new ArrayList<>();
        wordsNOT.add(" не ");

        HashMap<String, ArrayList<String>> spKeys = new HashMap<>();
        spKeys.put("title:", wordsTitle);
        spKeys.put("enemy:", wordsEnemy);
        spKeys.put("companion:", wordsCompanions);
//        spKeys.put("doctor: ", wordsDoctor);
        spKeys.put(" AND ", wordsAND);
        spKeys.put(" OR ", wordsOR);
        spKeys.put(" NOT ", wordsNOT);

        this.specialKeys = spKeys;
    }

    public void replaceSpecialKeys() {
        String temp_query = this.query_before;
        for (String field : this.specialKeys.keySet()) {
            ArrayList<String> synonim = specialKeys.get(field);
//            System.out.println("Замена на " + field);
            for (String syn : synonim) {
//                System.out.println("\tЗамена слова" + syn);
//                System.out.println("\t\tТекущий запрос:" + temp_query);
                temp_query = temp_query.replaceAll(syn, field);
//                System.out.println("\t\t\tНовый запрос:" + temp_query);
            }
        this.query_after = temp_query;
        }
    }


}
