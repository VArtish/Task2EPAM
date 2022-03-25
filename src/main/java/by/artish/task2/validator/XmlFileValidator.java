package by.artish.task2.validator;

import by.artish.task2.exception.XMLParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlFileValidator {
    private static final Logger LOGGER = LogManager.getLogger(XmlFileValidator.class);
    private static XmlFileValidator instance;

    private XmlFileValidator() {
    }

    public static XmlFileValidator getInstance() {
        if (instance == null) {
            instance = new XmlFileValidator();
        }
        return instance;
    }

    public boolean isCorrect(String xmlFileName, String schemaFileName) throws XMLParserException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        try {
            SchemaFactory factory = SchemaFactory.newInstance(language);
            File schemaLocation = new File(schemaFileName);
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFileName);
            validator.validate(source);
            return true;
        } catch (SAXException saxException) {
            LOGGER.log(Level.ERROR, saxException.getMessage());
            return false;
        } catch (IOException ioException) {
            LOGGER.log(Level.ERROR, ioException.getMessage());
            throw new XMLParserException(ioException);
        }
    }

}