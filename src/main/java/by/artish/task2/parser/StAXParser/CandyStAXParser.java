package by.artish.task2.parser.StAXParser;

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

    public void buildCandies(String path) throws XMLParserException{
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("chocolate-candy")) {
                        candy = new ChocolateCandy();
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        Attribute productionAttr = startElement.getAttributeByName(new QName("production"));
                        if (idAttr != null) {
                            candy.setId(idAttr.getValue());
                        }
                        if (productionAttr != null) {
                            candy.setProduction(productionAttr.getValue());
                        }
                    } else if (startElement.getName().getLocalPart().equals("caramel-candy")) {
                        candy = new CaramelCandy();
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        Attribute productionAttr = startElement.getAttributeByName(new QName("production"));
                        if (idAttr != null) {
                            candy.setId(idAttr.getValue());
                        }
                        if (productionAttr != null) {
                            candy.setProduction(productionAttr.getValue());
                        }
                    } else if (startElement.getName().getLocalPart().equals("energy")) {
                        xmlEvent = reader.nextEvent();
                        candy.setEnergy(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("name")) {
                        xmlEvent = reader.nextEvent();
                        candy.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("type")) {
                        xmlEvent = reader.nextEvent();
                        candy.setCandyType(CandyType.getCandyType(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("filling")) {
                        xmlEvent = reader.nextEvent();
                        ChocolateCandy chocolateCandy = (ChocolateCandy) candy;
                        chocolateCandy.setFilling(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
                        candy = chocolateCandy;
                    } else if (startElement.getName().getLocalPart().equals("lollipop")) {
                        xmlEvent = reader.nextEvent();
                        CaramelCandy caramelCandy = (CaramelCandy) candy;
                        caramelCandy.setLollipop(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
                        candy = caramelCandy;
                    } else if (startElement.getName().getLocalPart().equals("chocolate-type")) {
                        xmlEvent = reader.nextEvent();
                        ChocolateCandy chocolateCandy = (ChocolateCandy) candy;
                        chocolateCandy.setChocolateType(ChocolateType.getChocolateType(xmlEvent.asCharacters().getData()));
                        candy = chocolateCandy;
                    } else if (startElement.getName().getLocalPart().equals("date")) {
                        xmlEvent = reader.nextEvent();
                        candy.setDate(getDateContent(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("ingredients")) {
                        xmlEvent = reader.nextEvent();
                        ingredients = new ArrayList<Ingredient>();
                    } else if (startElement.getName().getLocalPart().equals("values")) {
                        xmlEvent = reader.nextEvent();
                        values = new ArrayList<Value>();
                    } else if (startElement.getName().getLocalPart().equals("value")) {
                        xmlEvent = reader.nextEvent();
                        value = new Value();
                    } else if (startElement.getName().getLocalPart().equals("ingredient")) {
                        xmlEvent = reader.nextEvent();
                        ingredient = new Ingredient();
                    } else if (startElement.getName().getLocalPart().equals("ingredient-name")) {
                        xmlEvent = reader.nextEvent();
                        ingredient.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("value-name")) {
                        xmlEvent = reader.nextEvent();
                        value.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("value-weight")) {
                        xmlEvent = reader.nextEvent();
                        value.setWeight(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("ingredient-weight")) {
                        xmlEvent = reader.nextEvent();
                        ingredient.setWeight(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }
                }
                    if (xmlEvent.isEndElement()) {
                        EndElement endElement = xmlEvent.asEndElement();
                        if (endElement.getName().getLocalPart().equals("chocolate-candy") || endElement.getName().getLocalPart().equals("caramel-candy")) {
                            candies.add(candy);
                        } else if (endElement.getName().getLocalPart().equals("ingredients")) {
                            candy.setIngredients(ingredients);
                        } else if (endElement.getName().getLocalPart().equals("values")) {
                            candy.setValues(values);
                        } else if (endElement.getName().getLocalPart().equals("value")) {
                            values.add(value);
                        } else if (endElement.getName().getLocalPart().equals("ingredient")) {
                            ingredients.add(ingredient);
                        }
                    }
                }

            }
         catch (FileNotFoundException | XMLStreamException exc) {
            LOGGER.log(Level.ERROR, exc.getMessage());
            throw new XMLParserException(exc.getMessage());
        }
    }

    private Date getDateContent(String date){
        return Date.valueOf(date);
    }
}
