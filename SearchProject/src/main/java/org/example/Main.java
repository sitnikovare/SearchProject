package org.example;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.ScoreDoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String getQuery() {
        System.out.print("Введите запрос: ");

        Scanner in = new Scanner(System.in);
        String query = in.nextLine();

        return query;
    }

    // возвращает строку, разделенную по пробелам
    public static String[] termQuery(String strQuery) {
        String[] res = strQuery.split(" ");
//        for (String st : res) {
//            System.out.println(st);
//        }
        return res;
    }

    public static void main(String[] args) {
        IndexCreator ic = new IndexCreator();
        IndexReader ir;
        MessageIndexer midx = new MessageIndexer("D:\\CSIT\\SearchProject\\SearchProject\\indexesForDocs");

        try {
            ir = midx.readIndex();
            BasicSearch bsearch = new BasicSearch(ir);
            String query = getQuery();
            // делим строку на термы
            String[] termsQuery = termQuery(query);
            System.out.println("\tРезультаты поиска по запросу: " + query);

            ArrayList<HashSet<String>> resultSets = new ArrayList<>();
            // производим поиск по каждому терму и запоминаем в сет
            for (String term : termsQuery) {
                HashSet<String> resTitle = new HashSet<>();
                resTitle = bsearch.fuzzySearch(term, "title", 5);
                HashSet<String> resBody = new HashSet<>();
                resBody = bsearch.fuzzySearch(term, "body", 5);
                // объединяем сеты
                HashSet<String> resultSet = new HashSet<>();
                resultSet.addAll(resTitle);
                resultSet.addAll(resBody);
                // запоминаем сет
                resultSets.add(resultSet);
            }
            System.out.println(resultSets.size());
//            bsearch.searchInTitle(query);
//            bsearch.searchInBody(query);
        }
        catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}