package org.example;

import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
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
        System.out.println("Документы содержат следующие поля:");
        System.out.println("\ttitle, doctor, companion, enemy, body\n");
        System.out.print("Введите запрос с указанием полей или без них (body - поле по умолчанию): ");

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
//        Построение индексов
//        IndexCreator ic = new IndexCreator();
        IndexReader ir;
        MessageIndexer midx = new MessageIndexer("D:\\CSIT\\SearchProject\\SearchProject\\indexesForDocs");

        try {
            ir = midx.readIndex();
            // получаем изначальный запрос (в формате поиска по полям)
            String query = getQuery();
            QueryModifier qm = new QueryModifier(query);
            String newQuery = qm.getAfterQuery();
            System.out.println("\nПроизводится поиск по запросу: " + newQuery);
            BasicSearch bsearch = new BasicSearch(ir);
            ArrayList<String> results = bsearch.searchWithParsing(newQuery, 10);
            printResults(results);
            //********************************************************************************************************//
            //***************** старый вариант парсинга запросов ******************//
            // делим строку на термы
            //
//
//            String[] termsQuery = termQuery(query);
//            System.out.println("\t\tРезультаты поиска по запросу: " + query + "\n");
//
//            ArrayList<ArrayList<String>> resultSets = new ArrayList<>();
//            // производим поиск по каждому терму и запоминаем в сет
//            for (String term : termsQuery) {
//                ArrayList<String> resTitle = new ArrayList<>();
//                resTitle = bsearch.fuzzySearch(term, "title", 5);
//                ArrayList<String> resDoctor = new ArrayList<>();
//                resDoctor = bsearch.fuzzySearch(term, "doctor", 5);
//                ArrayList<String> resComp = new ArrayList<>();
//                resComp = bsearch.fuzzySearch(term, "companion", 5);
//                ArrayList<String> resEnemy = new ArrayList<>();
//                resEnemy = bsearch.fuzzySearch(term, "enemy", 5);
//                ArrayList<String> resBody = new ArrayList<>();
//                resBody = bsearch.fuzzySearch(term, "body", 5);
//
//                // объединяем сеты
//                ArrayList<String> resultSet = new ArrayList<>();
//                if (!resTitle.isEmpty()) {
//                    resultSet.addAll(resTitle);
//                }
//                if (!resBody.isEmpty()) {
//                    resultSet.addAll(resBody);
//                }
//                if (!resBody.isEmpty()) {
//                    resultSet.addAll(resDoctor);
//                }
//                if (!resBody.isEmpty()) {
//                    resultSet.addAll(resComp);
//                }
//                if (!resBody.isEmpty()) {
//                    resultSet.addAll(resEnemy);
//                }
////                resultSet.addAll(fullQuery);
//                // запоминаем сет
//                resultSets.add(resultSet);
//            }
//
//            ArrayList<String> allEntries = new ArrayList<>();
//            ArrayList<String> exeptOne = new ArrayList<>();
//            // идем по каждому сету
//            for (int i = 0; i < resultSets.size(); i++) {
//                // по всем файлам в нем
//                // ищем те документы, которые вошли везде
//                boolean allEnt = true;
//                // ищем те, которые не вошли только в один
//                int k = 2;
//                for (String title : resultSets.get(i)) {
//                    for (int j = 0; j < resultSets.size(); j++) {
//                        if (!resultSets.get(j).contains(title)) {
//                            allEnt = false;
//                            k -= 1;
//                        }
//                        if (k == 0) {
//                            break;
//                        }
//                    }
//                    if (allEnt) { allEntries.add(title); }
//                    if (k != 0 && !allEnt) {exeptOne.add(title); }
//                }
//            }
//
//            System.out.println("\n\tПолное вхождение");
//            printResults(allEntries);
//            System.out.println("\n\tЧастичное вхождение");
//            printResults(exeptOne);
//            System.out.println("\n\tИзначальные результаты");
//            for (int i = 0; i < resultSets.size(); i++) {
//                printResults(resultSets.get(i));
//            }
            //********************************************************************************************************//
        }
        catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}