import org.example.QueryModifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryModifierTest {
    @Test
    public void companionANDcompanion() {
        QueryModifier qm = new QueryModifier("спутники (\"эми понд\" и \"рори\")");
        String expected = "companion: (\"эми понд\" AND \"рори\")";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void companionAND_NOTtitle() {
        QueryModifier qm = new QueryModifier("спутник \"роза\" и не название \"пустой ребенок\"");
        String expected = "companion: \"роза\" AND NOT title: \"пустой ребенок\"";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void doctorsNameNotEpisode() {
        QueryModifier qm = new QueryModifier(" не серия \"имя доктора\"");
        String expected = " NOT title: \"имя доктора\"";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void doctorAND_NOTenemy() {
        QueryModifier qm = new QueryModifier("двенадцатый доктор и не враги далеки");
        String expected = "двенадцатый доктор AND NOT enemy: далеки";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryFromSheet1() {
        QueryModifier qm = new QueryModifier("название \"двенадцатый доктор\"");
        String expected = "title: \"двенадцатый доктор\"";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryFromSheet2() {
        QueryModifier qm = new QueryModifier("имя доктора");
        String expected = "имя доктора";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryFromSheet3() {
        QueryModifier qm = new QueryModifier("тензалор");
        String expected = "тензалор";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryFromSheet4() {
        QueryModifier qm = new QueryModifier("уязвимости далеков");
        String expected = "уязвимости далеков";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryFromSheet5() {
        QueryModifier qm = new QueryModifier("сонтаранец и (должник доктора)");
        String expected = "сонтаранец AND (должник доктора)";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryFromSheet6() {
        QueryModifier qm = new QueryModifier("(11 или одиннадцатый) доктор");
        String expected = "(11 OR одиннадцатый) доктор";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryFromSheet7() {
        QueryModifier qm = new QueryModifier("\"имя ривер сонг\"");
        String expected = "\"имя ривер сонг\"";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryFromSheet8() {
        QueryModifier qm = new QueryModifier("\"первая встреча\" и доктор и \"ривер сонг\"");
        String expected = "\"первая встреча\" AND доктор AND \"ривер сонг\"";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryFromSheet9() {
        QueryModifier qm = new QueryModifier("спутник \"эми понд\" и доктор");
        String expected = "companion: \"эми понд\" AND доктор";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryFromSheet10() {
        QueryModifier qm = new QueryModifier("название \"клара освальд\" или спутник \"клара освальд\" или \"клара освин освальд\"");
        String expected = "title: \"клара освальд\" OR companion: \"клара освальд\" OR \"клара освин освальд\"";
        String actual = qm.getAfterQuery();
        Assert.assertEquals(expected, actual);
    }
}
