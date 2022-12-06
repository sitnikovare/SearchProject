package org.example;

import org.apache.lucene.document.Document;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Создает индексы для спаршенных документов
 */
public class IndexCreator {

    /**
     * Содержит список всех файлов для индексирования
     */
    private File[] files;
    private List<Document> luceneDocs = new ArrayList<>();

    /**
     * Получает список всех файлов в resources/DocumentsToIndex
     * @return список всех файлов
     */
    private File[] getFilesList() {
        File dir = new File(getClass().getClassLoader().getResource("DocumentsToIndex").getFile());
        File[] files = dir.listFiles();
        return files;
    }


    ArrayList<String[]> filedsForFiles = new ArrayList<>();

    public void getCSV() {
        ArrayList<String[]> csvList = new ArrayList<>();
        // получить дополнительные поля файла
        File csvFile = new File(getClass().getClassLoader().getResource("res.csv").getFile());
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = "";
            String cvsSplitBy = ",";
            Scanner scanner;
            int index = 0;
            while ((line = br.readLine()) != null) {
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                String[] fields = {"0", "1", "2", "3"};
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == 4) {break;}
                    fields[index] = data;
                    index++;
                }
                index = 0;
                csvList.add(fields);
//                System.out.println("Filename= " + fields[0]
//                        + " , Doctor= " + fields[1]
//                        + " , Companions= " + fields[2]
//                        + " , Enemies= " + fields[3]);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        this.filedsForFiles = csvList;
    }

    /**
     * Строит один lucene-документ
     * @return lucene-документ для соответствуюещго файла
     */
    public Document createIndexForOneDocument(File currentFile) {
        // хранит название файла
        String filename = currentFile.getName();
        String fileTitle = filename.substring(0, filename.indexOf("."));

        // хранит тело файла
        String fileBody = "";

        // открыть файл и записать его тело
        try(FileInputStream fin=new FileInputStream(currentFile))
        {
            InputStreamReader isr = new InputStreamReader(fin, "UTF8");
            BufferedReader buf = new BufferedReader(isr);
            String s = "";
            s = buf.readLine();
            while(s != null){
                fileBody+=s;
                s = buf.readLine();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }


        // строим lucene-документ для данного файла
        Document doc = MessageToDocument.createWith(filename, fileTitle, fileBody, filedsForFiles);

        return doc;
    }

    /**
     * Заполняет лист с Lucene документами
     */
    private void creatingLuceneDocuments() {
        for (File curFile : files) {
            Document curDoc = createIndexForOneDocument(curFile);
            luceneDocs.add(curDoc);
        }
    }
    /**
     * Конструктор класса
     */
    public IndexCreator() {
        // получаем список всех файлов
        files = getFilesList();
        getCSV();
        // создаем Lucene документы для всех файлов
        creatingLuceneDocuments();
        // строим индексы для документов
        MessageIndexer midx = new MessageIndexer("D:\\CSIT\\SearchProject\\SearchProject\\indexesForDocs");
        try {
            midx.index(true, luceneDocs);
        }
        catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * Геттер для списка файлов
     * @return список файлов текущего экземпляра IndexCreator
     */
    public File[] getFiles() {
        return this.files;
    }

    public List<Document> getLuceneDocs() {
        return luceneDocs;
    }
}
