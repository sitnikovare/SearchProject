package org.example;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.ScoreDoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {

    // вывод сета с результатами поиска
    public static void printResults(HashSet<String> results) {
        for (String str : results) {
            System.out.println(str);
        }
    }
    public static void printResults(ArrayList<String> results) {
        for (String str : results) {
            System.out.println(str);
        }
    }

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
            System.out.println("\t\tРезультаты поиска по запросу: " + query + "\n");

            ArrayList<HashSet<String>> resultSets = new ArrayList<>();
            // производим поиск по каждому терму и запоминаем в сет
            for (String term : termsQuery) {
                HashSet<String> resTitle = new HashSet<>();
                resTitle = bsearch.fuzzySearch(term, "title", 5);
                HashSet<String> resBody = new HashSet<>();
                resBody = bsearch.fuzzySearch(term, "body", 5);
                // объединяем сеты
                HashSet<String> resultSet = new HashSet<>();
                if (!resTitle.isEmpty()) {
                    resultSet.addAll(resTitle);
                }
                if (!resBody.isEmpty()) {
                    resultSet.addAll(resBody);
                }
//                resultSet.addAll(fullQuery);
                // запоминаем сет
                resultSets.add(resultSet);
            }

            ArrayList<String> allEntries = new ArrayList<>();
            ArrayList<String> exeptOne = new ArrayList<>();
            // идем по каждому сету
            for (int i = 0; i < resultSets.size(); i++) {
                // по всем файлам в нем
                // ищем те документы, которые вошли везде
                boolean allEnt = true;
                // ищем те, которые не вошли только в один
                int k = 2;
                for (String title : resultSets.get(i)) {
                    for (int j = 0; j < resultSets.size(); j++) {
                        if (!resultSets.get(j).contains(title)) {
                            allEnt = false;
                            k -= 1;
                        }
                        if (k == 0) {
                            break;
                        }
                    }
                    if (allEnt) { allEntries.add(title); }
                    if (k != 0 && !allEnt) {exeptOne.add(title); }
                }
            }

            System.out.println("\n\tПолное вхождение");
            printResults(allEntries);
            System.out.println("\n\tЧастичное вхождение");
            printResults(exeptOne);
            System.out.println("\n\tИзначальные результаты");
            for (int i = 0; i < resultSets.size(); i++) {
                printResults(resultSets.get(i));
            }

        }
        catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}