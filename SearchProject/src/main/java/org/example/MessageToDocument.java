package org.example;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;

/**
 * Класс для создание Lucene-документов
 */
public class MessageToDocument {

    /**
     * Создание документа с использованием тела и заголовка
     * @return созданный документ
     */
    public static Document createWith(final String titleStr, final String bodyStr) {
        final Document document = new Document();

        final FieldType textIndexedType = new FieldType();
        textIndexedType.setStored(true);
        textIndexedType.setIndexOptions(IndexOptions.DOCS);
        textIndexedType.setTokenized(true);

        //Заголовок индекса
        Field title = new Field("title", titleStr, textIndexedType);
        //Тело индекса
        Field body = new Field("body", bodyStr, textIndexedType);

        document.add(title);
        document.add(body);
        return document;
    }
}