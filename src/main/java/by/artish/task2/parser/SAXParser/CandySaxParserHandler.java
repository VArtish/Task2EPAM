package by.artish.task2.parser.SAXParser;

import by.artish.task2.entity.*;
import by.artish.task2.parser.CandyXmlAttribute;
import by.artish.task2.parser.CandyXmlTag;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CandySaxParserHandler extends DefaultHandler {
    private final List<AbstractCandy> candies;
    private List<Ingredient> currentIngredients;
    private AbstractCandy currentCandy;
    private Ingredient currentIngredient;
    private CandyXmlTag currentXmlTag;
    private List<Value> currentValues;
    private Value currentValue;

    public CandySaxParserHandler() {
        candies = new ArrayList<AbstractCandy>();
    }

    public List<AbstractCandy> getCandies() {
        return candies;
    }

    private void initializationCandy(Attributes attributes){
        int indexId = attributes.getIndex(CandyXmlAttribute.ID.getName());
        currentCandy.setId(attributes.getValue(indexId));

        if (attributes.getLength() >= 2) {
            int productionId = attributes.getIndex(CandyXmlAttribute.PRODUCTION.getName());
            currentCandy.setProduction(attributes.getValue(productionId));
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (CandyXmlTag.CHOCOLATE_CANDY.getName().equals(qName)) {
            currentCandy = new ChocolateCandy();
            initializationCandy(attributes);
        } else if (CandyXmlTag.CARAMEL_CANDY.getName().equals(qName)) {
            currentCandy = new CaramelCandy();
            initializationCandy(attributes);
        } else if (CandyXmlTag.INGREDIENTS.getName().equals(qName)) {
            currentIngredients = new ArrayList<Ingredient>();
        } else if (CandyXmlTag.INGREDIENT.getName().equals(qName)) {
            currentIngredient = new Ingredient();
        } else if (CandyXmlTag.VALUES.getName().equals(qName)) {
            currentValues = new ArrayList<Value>();
        } else if (CandyXmlTag.VALUE.getName().equals(qName)) {
            currentValue = new Value();
        } else {
            currentXmlTag = CandyXmlTag.getCandyXmlTag(qName);
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (CandyXmlTag.CHOCOLATE_CANDY.getName().equals(qName) || CandyXmlTag.CARAMEL_CANDY.getName().equals(qName)) {
            candies.add(currentCandy);
        } else if (CandyXmlTag.INGREDIENT.getName().equals(qName)) {
            currentIngredients.add(currentIngredient);
        } else if (CandyXmlTag.INGREDIENTS.getName().equals(qName)) {
            currentCandy.setIngredients(currentIngredients);
        } else if (CandyXmlTag.VALUES.getName().equals(qName)) {
            currentCandy.setValues(currentValues);
        } else if (CandyXmlTag.VALUE.getName().equals(qName)) {
            currentValues.add(currentValue);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case CANDY_NAME -> currentCandy.setName(data);
                case ENERGY -> currentCandy.setEnergy(Integer.parseInt(data));
                case CANDY_TYPE -> currentCandy.setCandyType(CandyType.getCandyType(data));
                case CHOCOLATE_TYPE -> {
                    ChocolateCandy chocolateCandy = (ChocolateCandy) currentCandy;
                    chocolateCandy.setChocolateType(ChocolateType.getChocolateType(data));
                    currentCandy = chocolateCandy;
                }
                case LOLLIPOP -> {
                    CaramelCandy caramelCandy = (CaramelCandy) currentCandy;
                    caramelCandy.setLollipop(Boolean.parseBoolean(data));
                    currentCandy = caramelCandy;
                }
                case FILLING ->{
                    ChocolateCandy chocolateCandy = (ChocolateCandy) currentCandy;
                    chocolateCandy.setFilling(Boolean.parseBoolean(data));
                    currentCandy = chocolateCandy;
                }
                case VALUE_NAME -> currentValue.setName(data);
                case VALUE_WEIGHT -> currentValue.setWeight(Integer.parseInt(data));
                case INGREDIENT_NAME -> currentIngredient.setName(data);
                case INGREDIENT_WEIGHT -> currentIngredient.setWeight(Integer.parseInt(data));
                case DATE -> {
                    currentCandy.setDate(Date.valueOf(data));
                }
            }
        }
        currentXmlTag = null;
    }
}