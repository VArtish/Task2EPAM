package by.artish.task2.parser.StAXParser;

import by.artish.task2.exception.XMLParserException;
import by.artish.task2.parser.ReferenceCandy;
import by.artish.task2.parser.builder.CandyBuilderFactory;
import by.artish.task2.parser.builder.ParserBaseBuilder;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CandyStAXParserTest extends TestCase {

    @Test
    public void testBuildCandies() throws XMLParserException {
        ParserBaseBuilder parser = CandyBuilderFactory.createCandyBuilder("STAX");
        parser.buildCandies("D:\\Task2EPAM\\src\\test\\resources\\candyText.xml");
        var a = parser.getCandies();
        var b = ReferenceCandy.getCandyList();
        Assert.assertEquals(a.get(0), b.get(1));
        Assert.assertEquals(a.get(1), b.get(0));
    }
}