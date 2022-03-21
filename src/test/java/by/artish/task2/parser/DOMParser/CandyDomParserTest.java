package by.artish.task2.parser.DOMParser;

import by.artish.task2.exception.XMLParserException;
import by.artish.task2.parser.builder.CandyBuilderFactory;
import by.artish.task2.parser.builder.ParserBaseBuilder;
import by.artish.task2.parser.ReferenceCandy;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CandyDomParserTest extends TestCase {

    @Test
    public void testBuildCandies() throws XMLParserException {
        ParserBaseBuilder parser = CandyBuilderFactory.createCandyBuilder("DOM");
        parser.buildCandies("D:\\Task2EPAM\\src\\test\\resources\\candyText.xml");
        var a = parser.getCandies();
        var b = ReferenceCandy.getCandyList();
        Assert.assertArrayEquals(a.toArray(), b.toArray());
    }
}