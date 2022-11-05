package org.example;

import org.apache.lucene.index.IndexReader;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String getQuery() {
        System.out.print("Введите запрос: ");

        Scanner in = new Scanner(System.in);
        String query = in.next();

        return query;
    }

    public static void main(String[] args) {
        IndexCreator ic = new IndexCreator();
        IndexReader ir;
        MessageIndexer midx = new MessageIndexer("D:\\CSIT\\SearchProject\\SearchProject\\indexesForDocs");

        try {
            ir = midx.readIndex();
            BasicSearch bsearch = new BasicSearch(ir);
            String query = getQuery();
            System.out.println("Результаты поиска");
            bsearch.searchInTitle(query);
            bsearch.searchInBody(query);
        }
        catch(Exception e) {
            System.out.println(e.getStackTrace());
        }


    }
}