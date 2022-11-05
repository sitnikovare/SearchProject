import org.example.IndexCreator;
import org.junit.*;

import java.io.File;

public class IndexCreatorTest {

    int currentDocumentsAmount = 1044;
    IndexCreator ic;
    @Test
    @Before
    public void IndexCreatorInit() {
        this.ic = new IndexCreator();
    }

    @Test
    public void FilesListLengthTest() {
        Assert.assertEquals(currentDocumentsAmount, ic.getFiles().length);
    }

    @Test
    public void LuceneDocumentsLengthTest() {
        Assert.assertEquals(currentDocumentsAmount, ic.getLuceneDocs().size());
    }
}
