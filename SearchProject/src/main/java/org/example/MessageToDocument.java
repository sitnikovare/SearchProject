package org.example;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;

import java.util.ArrayList;

/**
 * Класс для создание Lucene-документов
 */
public class MessageToDocument {

    /**
     * Создание документа с использованием тела и заголовка
     * @return созданный документ
     */
    public static Document createWith(final String filename, final String titleStr, final String bodyStr, final ArrayList<String[]> filedsForFiles) {
        // поиск дополнительных полей
        String doctorField = "";
        String compFiled = "";
        String enemyField = "";
        for (String[] fileFileds : filedsForFiles) {
            if (filename.equals(fileFileds[0])) {
                doctorField = fileFileds[1];
                compFiled = fileFileds[2];
                enemyField = fileFileds[3];
            }
        }

        final Document document = new Document();

        final FieldType textIndexedType = new FieldType();
        textIndexedType.setStored(true);
        textIndexedType.setIndexOptions(IndexOptions.DOCS);
        textIndexedType.setTokenized(true);

        //Заголовок индекса
        Field title = new Field("title", titleStr, textIndexedType);
        //Тело индекса
        Field body = new Field("body", bodyStr, textIndexedType);
        Field doctor = new Field("doctor", doctorField, textIndexedType);
        Field companion = new Field("companion", compFiled, textIndexedType);
        Field enemy = new Field("enemy", enemyField, textIndexedType);

        document.add(title);
        document.add(body);
        document.add(doctor);
        document.add(companion);
        document.add(enemy);
        return document;
    }
}