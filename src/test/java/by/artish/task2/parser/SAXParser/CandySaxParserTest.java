package by.artish.task2.parser.SAXParser;

import by.artish.task2.exception.XMLParserException;
import by.artish.task2.parser.builder.CandyBuilderFactory;
import by.artish.task2.parser.builder.ParserBaseBuilder;
import by.artish.task2.parser.ReferenceCandy;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class CandySaxParserTest extends TestCase {

    @Test
    public void testBuildCandies() throws XMLParserException {
        ParserBaseBuilder parser = CandyBuilderFactory.createCandyBuilder("SAX");
        parser.buildCandies("D:\\Labs\\Task2EPAM\\src\\test\\resources\\candyText.xml");
        var a = parser.getCandies();
        var b = ReferenceCandy.getCandyList();
        Assert.assertEquals(a.get(0), b.get(1));
        Assert.assertEquals(a.get(1), b.get(0));
    }
}