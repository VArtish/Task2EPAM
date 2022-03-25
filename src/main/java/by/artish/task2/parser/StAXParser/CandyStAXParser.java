package by.artish.task2.parser.StAXParser;

import by.artish.task2.parser.CandyXmlAttribute;
import by.artish.task2.parser.CandyXmlTag;
import by.artish.task2.parser.builder.ParserBaseBuilder;
import by.artish.task2.entity.*;
import by.artish.task2.exception.XMLParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CandyStAXParser extends ParserBaseBuilder {
    private static final Logger LOGGER = LogManager.getLogger(CandyStAXParser.class);
    private Ingredient ingredient;
    private Value value;
    private List<Ingredient> ingredients;
    private List<Value> values;
    private AbstractCandy candy;


    public CandyStAXParser() {
        super();
        ingredients = new ArrayList<Ingredient>();
        values = new ArrayList<Value>();
    }

    public void buildCandies(String path) throws XMLParserException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals(CandyXmlTag.CHOCOLATE_CANDY.getName())) {
                        candy = new ChocolateCandy();
                        checkAttribute(candy, startElement);
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.CARAMEL_CANDY.getName())) {
                        candy = new CaramelCandy();
                        checkAttribute(candy, startElement);
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.ENERGY.getName())) {
                        xmlEvent = reader.nextEvent();
                        candy.setEnergy(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.CANDY_NAME.getName())) {
                        xmlEvent = reader.nextEvent();
                        candy.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.CANDY_TYPE.getName())) {
                        xmlEvent = reader.nextEvent();
                        candy.setCandyType(CandyType.getCandyType(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.FILLING.getName())) {
                        xmlEvent = reader.nextEvent();
                        ChocolateCandy chocolateCandy = (ChocolateCandy) candy;
                        chocolateCandy.setFilling(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
                        candy = chocolateCandy;
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.LOLLIPOP.getName())) {
                        xmlEvent = reader.nextEvent();
                        CaramelCandy caramelCandy = (CaramelCandy) candy;
                        caramelCandy.setLollipop(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
                        candy = caramelCandy;
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.CHOCOLATE_TYPE.getName())) {
                        xmlEvent = reader.nextEvent();
                        ChocolateCandy chocolateCandy = (ChocolateCandy) candy;
                        chocolateCandy.setChocolateType(ChocolateType.getChocolateType(xmlEvent.asCharacters().getData()));
                        candy = chocolateCandy;
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.DATE.getName())) {
                        xmlEvent = reader.nextEvent();
                        candy.setDate(getDateContent(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.INGREDIENTS.getName())) {
                        xmlEvent = reader.nextEvent();
                        ingredients = new ArrayList<Ingredient>();
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.VALUES.getName())) {
                        xmlEvent = reader.nextEvent();
                        values = new ArrayList<Value>();
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.VALUE.getName())) {
                        xmlEvent = reader.nextEvent();
                        value = new Value();
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.INGREDIENT.getName())) {
                        xmlEvent = reader.nextEvent();
                        ingredient = new Ingredient();
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.INGREDIENT_NAME.getName())) {
                        xmlEvent = reader.nextEvent();
                        ingredient.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.VALUE_NAME.getName())) {
                        xmlEvent = reader.nextEvent();
                        value.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.VALUE_WEIGHT.getName())) {
                        xmlEvent = reader.nextEvent();
                        value.setWeight(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(CandyXmlTag.INGREDIENT_WEIGHT.getName())) {
                        xmlEvent = reader.nextEvent();
                        ingredient.setWeight(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals(CandyXmlTag.CHOCOLATE_CANDY.getName()) || endElement.getName().getLocalPart().equals(CandyXmlTag.CARAMEL_CANDY.getName())) {
                        candies.add(candy);
                    } else if (endElement.getName().getLocalPart().equals(CandyXmlTag.INGREDIENTS.getName())) {
                        candy.setIngredients(ingredients);
                    } else if (endElement.getName().getLocalPart().equals(CandyXmlTag.VALUES.getName())) {
                        candy.setValues(values);
                    } else if (endElement.getName().getLocalPart().equals(CandyXmlTag.VALUE.getName())) {
                        values.add(value);
                    } else if (endElement.getName().getLocalPart().equals(CandyXmlTag.INGREDIENT.getName())) {
                        ingredients.add(ingredient);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException exc) {
            LOGGER.log(Level.ERROR, exc.getMessage());
            throw new XMLParserException(exc.getMessage());

        }
    }

    private void checkAttribute(AbstractCandy candy, StartElement startElement) {
        Attribute idAttr = startElement.getAttributeByName(new QName(CandyXmlAttribute.ID.getName()));
        Attribute productionAttr = startElement.getAttributeByName(new QName(CandyXmlAttribute.PRODUCTION.getName()));
        if (idAttr != null) {
            candy.setId(idAttr.getValue());
        }
        if (productionAttr != null) {
            candy.setProduction(productionAttr.getValue());
        }
    }

    private Date getDateContent(String date) {
        return Date.valueOf(date);
    }
}
