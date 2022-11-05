package org.example;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MessageIndexer {
    private final String pathToIndexFolder;

    /**
     * Метод создает индексы
     * @param pathToIndexFolder Путь, по которому хранятся индексы
     */
    public MessageIndexer(final String pathToIndexFolder) {
        this.pathToIndexFolder = pathToIndexFolder;
    }

    /**
     * Индексирование документа с использованием анализатора
     * @param create true - создать новый индекс, false - обновить существующий
     * @throws IOException
     */
    public void index(final Boolean create, List<Document> documents, Analyzer analyzer) throws IOException {
        final Directory dir = FSDirectory.open(Paths.get(pathToIndexFolder));
        final IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        if (create) {
            // Нужно удалить существующие индексы
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        }
        else {
            // Добавить документ к существующим индексам
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        }

        final IndexWriter w = new IndexWriter(dir, iwc);
        w.addDocuments(documents);
        w.close();
    }

    /**
     * Индексирование документа
     * @param create true - создать новый индекс, false - обновить существующий
     * @throws IOException
     */
    public void index(final Boolean create, List<Document> documents) throws IOException {
        final Analyzer analyzer = new RussianAnalyzer();
        index(create, documents, analyzer);
    }

    /**
     * Создание одного документа
     * @param create true - создать новый индекс, false - обновить существующий
     * @throws IOException
     */
    public void index(final Boolean create, Document document) throws IOException {
        final List<Document> oneDocumentList = new ArrayList<>();
        oneDocumentList.add(document);
        index(create, oneDocumentList);
    }

    /**
     * Получить IndexReader с использованием пути до папки с индексами pathToIndexFolder
     * @return Исключение или экземпляр IndexReader
     * @throws IOException
     */
    public IndexReader readIndex() throws IOException {
        final Directory dir = FSDirectory.open(Paths.get(pathToIndexFolder));
        return DirectoryReader.open(dir);
    }

    public String getPathToIndexFolder() {
        return pathToIndexFolder;
    }
}