import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.example.Constants;
import org.example.MessageIndexer;
import org.example.MessageToDocument;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Random;

public class MessageIndexerTest {
    private final Random rnd = new Random(); // генерирует случайное имя для документа

    private final MessageIndexer indexer = new MessageIndexer(Constants.TMP_DIR + "/tutorial_test" + rnd.nextInt());
    private final String body = "Это тестовое тело документа.";
    private final String title = "Это тестовый заголовок документа.";

    private Document document = null;

    @Test
    @Before
    public void testIndex() throws Exception {
        document = MessageToDocument.createWith(title, body);
        Assert.assertNotNull("Created document should not be null", document);
        Assert.assertEquals(body, document.get("body"));
        Assert.assertEquals(title, document.get("title"));

    }

    @Test
    @After
    public void testReadIndex() throws Exception {
        indexer.index(true, document); // индекс должен создаться без ошибок

        final IndexReader indexReader = indexer.readIndex();
        Assert.assertNotNull("IndexReader should not be null", indexReader);

        FileUtils.deleteQuietly(new File(indexer.getPathToIndexFolder())); // удал
    }
}