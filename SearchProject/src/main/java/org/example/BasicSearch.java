package org.example;

import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BasicSearch {
    public static final int DEFAULT_LIMIT = 10;
    private final IndexReader reader;

    public BasicSearch(IndexReader reader) {
        this.reader = reader;
    }

    public void searchIndexWithTermQuery(final String toSearch, final String searchField, final int limit) throws IOException, ParseException {
        final IndexSearcher indexSearcher = new IndexSearcher(reader);

        final Term term = new Term(searchField, toSearch);
        final Query query = new TermQuery(term);
        final TopDocs search = indexSearcher.search(query, limit);
        final ScoreDoc[] hits = search.scoreDocs;
        showHits(hits);
    }

    public void searchIndexWithTermQueryByBody(final String toSearch) throws IOException, ParseException {
        searchIndexWithTermQuery(toSearch, "body", DEFAULT_LIMIT);
    }

    public void searchInBody(final String toSearch, final int limit) throws IOException, ParseException {
        final IndexSearcher indexSearcher = new IndexSearcher(reader);

        final QueryParser queryParser = new QueryParser("body", new RussianAnalyzer());
        final Query query = queryParser.parse(toSearch);
//        System.out.println("Type of query: " + query.getClass().getSimpleName());

        final TopDocs search = indexSearcher.search(query, limit);
        final ScoreDoc[] hits = search.scoreDocs;
        showHits(hits);
    }

    public void searchInTitle(final String toSearch, final int limit) throws IOException, ParseException {
        final IndexSearcher indexSearcher = new IndexSearcher(reader);

        final QueryParser queryParser = new QueryParser("title", new RussianAnalyzer());
        final Query query = queryParser.parse(toSearch);
//        System.out.println("Type of query: " + query.getClass().getSimpleName());

        final TopDocs search = indexSearcher.search(query, limit);
        final ScoreDoc[] hits = search.scoreDocs;
        showHits(hits);
    }

    public void searchInBody(final String toSearch) throws IOException, ParseException {
        searchInBody(toSearch, DEFAULT_LIMIT);
    }

    public void searchInTitle(final String toSearch) throws IOException, ParseException {
        searchInTitle(toSearch, DEFAULT_LIMIT);
    }

    public ArrayList<String> fuzzySearch(final String toSearch, final String searchField, final int limit) throws IOException, ParseException {
        final IndexSearcher indexSearcher = new IndexSearcher(reader);

        final Term term = new Term(searchField, toSearch);

        final int maxEdits = 2;
        final Query query = new FuzzyQuery(term, maxEdits);
        final TopDocs search = indexSearcher.search(query, limit);
        final ScoreDoc[] hits = search.scoreDocs;
        System.out.println("Search: " + toSearch + " Field: " + searchField + " MaxScore: " + search.getMaxScore());
        ArrayList<String> resT = new ArrayList<>();
        resT = returnHits(hits);
        return resT;
//        showHits(hits);
    }

    public void fuzzySearch(final String toSearch) throws IOException, ParseException {
        System.out.println("\nПо содержимому:");
        fuzzySearch(toSearch, "body", DEFAULT_LIMIT);
        System.out.println("\nПо названию:");
        fuzzySearch(toSearch, "title", DEFAULT_LIMIT);
        System.out.println("\nПо Доктору:");
        fuzzySearch(toSearch, "doctor", DEFAULT_LIMIT);
        System.out.println("\nПо спутнику:");
        fuzzySearch(toSearch, "companion", DEFAULT_LIMIT);
        System.out.println("\nПо врагам:");
        fuzzySearch(toSearch, "enemy", DEFAULT_LIMIT);
    }

    private void showHits(final ScoreDoc[] hits) throws IOException {
        if (hits.length == 0) {
            System.out.println("\n\tНичего не найдено");
            return;
        }
//        System.out.println("\n\tРезультаты поиска:");
        for (ScoreDoc hit : hits) {
            final String title = reader.document(hit.doc).get("title");
            final String body = reader.document(hit.doc).get("body");
            System.out.println("\n\tID = " + hit.doc + "\n\tНазвание = " + title + "\n\tСодержание = " + body);
        }
    }

    private ArrayList<String> returnHits(final ScoreDoc[] hits) throws IOException {
        if (hits.length == 0) {
//            System.out.println("\n\tНичего не найдено");
            return new ArrayList<>();
        }

        ArrayList<String> resTitles = new ArrayList<>();
//        System.out.println("\n\tРезультаты поиска:");
        for (ScoreDoc hit : hits) {
            final String title = reader.document(hit.doc).get("title");
            resTitles.add(title);
//            final String body = reader.document(hit.doc).get("body");
//            System.out.println("\n\tID = " + hit.doc + "\n\tНазвание = " + title + "\n\tСодержание = " + body);
        }
        return  resTitles;
    }
}