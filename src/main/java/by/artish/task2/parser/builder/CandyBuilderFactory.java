package by.artish.task2.parser.builder;

import by.artish.task2.parser.DOMParser.CandyDomParser;
import by.artish.task2.parser.SAXParser.CandySaxParser;
import by.artish.task2.parser.StAXParser.CandyStAXParser;
import by.artish.task2.exception.XMLParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CandyBuilderFactory {
    private static final Logger LOGGER = LogManager.getLogger(CandyBuilderFactory.class);

    private enum TypeParser {
        SAX,
        STAX,
        DOM,
    }

    private CandyBuilderFactory() {
    }

    public static ParserBaseBuilder createCandyBuilder(String typeParser) throws XMLParserException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM -> {
                LOGGER.log(Level.INFO, "CandyDomParser created!");
                return new CandyDomParser();
            }
            case SAX -> {
                LOGGER.log(Level.INFO, "CandySaxParser created!");
                return new CandySaxParser();
            }
            case STAX -> {
                LOGGER.log(Level.INFO, "CandyStAXParser created!");
                return new CandyStAXParser();
            }
            default -> {
                LOGGER.log(Level.ERROR, "Enum constant not present. " + type.getDeclaringClass() + " " + type.name());
                throw new XMLParserException("Enum constant not present. " + type.getDeclaringClass() + " " + type.name());
            }
        }
    }
}