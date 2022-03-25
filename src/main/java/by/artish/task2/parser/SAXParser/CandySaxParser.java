package by.artish.task2.parser.SAXParser;

import by.artish.task2.parser.builder.ParserBaseBuilder;
import by.artish.task2.exception.XMLParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class CandySaxParser extends ParserBaseBuilder {
    private static final Logger LOGGER = LogManager.getLogger(CandySaxParser.class);

    public void buildCandies(String path) throws XMLParserException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        CandySaxParserHandler handler = new CandySaxParserHandler();
        SAXParser parser = null;

        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new XMLParserException("Open SAXParser Exception!");
        }

        File file = new File(path);
        try {
            parser.parse(file, handler);
            candies = handler.getCandies();
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new XMLParserException("SAXParsing exception!");
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new XMLParserException("IOParsing exception!");
        }
    }
}
