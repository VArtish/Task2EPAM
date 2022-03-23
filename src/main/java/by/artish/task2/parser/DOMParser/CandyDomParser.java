package by.artish.task2.parser.DOMParser;

import by.artish.task2.parser.CandyXmlAttribute;
import by.artish.task2.parser.CandyXmlTag;
import by.artish.task2.parser.builder.ParserBaseBuilder;
import by.artish.task2.entity.*;
import by.artish.task2.exception.XMLParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CandyDomParser extends ParserBaseBuilder {
    private DocumentBuilder documentBuilder;
    private static final Logger LOGGER = LogManager.getLogger(CandyDomParser.class);

    public CandyDomParser(){
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }

    @Override
    public void buildCandies(String path) throws XMLParserException {
        Document doc;
        try{
            doc = documentBuilder.parse(path);
            Element root = doc.getDocumentElement();
            NodeList caramelCandies = root.getElementsByTagName(CandyXmlTag.CARAMEL_CANDY.getName());
            NodeList chocolateCandies = root.getElementsByTagName(CandyXmlTag.CHOCOLATE_CANDY.getName());

            for(int i = 0; i < caramelCandies.getLength(); i++){
                if(caramelCandies.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element candyElement = (Element) caramelCandies.item(i);
                    AbstractCandy candy = buildCaramelCandy(candyElement);
                    candies.add(candy);
                }
            }

            for(int j = 0; j < chocolateCandies.getLength(); j++){
                if (chocolateCandies.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element candyElement = (Element) chocolateCandies.item(j);
                    AbstractCandy candy = buildChocolateCandy(candyElement);
                    candies.add(candy);
                }
            }
        } catch(IOException | SAXException e){
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new XMLParserException(e.getMessage());
        }
    }

    public CaramelCandy buildCaramelCandy(Element element){
        CaramelCandy caramelCandy = new CaramelCandy();
        buildCandy(caramelCandy, element);
        caramelCandy.setLollipop(getBooleanContent(element, CandyXmlTag.LOLLIPOP.getName()));

        if(element.hasAttribute(CandyXmlAttribute.PRODUCTION.getName())){
            caramelCandy.setProduction(element.getAttribute(CandyXmlAttribute.PRODUCTION.getName()));
        }

        return caramelCandy;
    }

    public ChocolateCandy buildChocolateCandy(Element element){
        ChocolateCandy chocolateCandy = new ChocolateCandy();
        buildCandy(chocolateCandy, element);
        chocolateCandy.setFilling(getBooleanContent(element, CandyXmlTag.FILLING.getName()));
        chocolateCandy.setChocolateType(ChocolateType.getChocolateType(getTextContent(element, CandyXmlTag.CHOCOLATE_TYPE.getName())));

        if(element.hasAttribute(CandyXmlAttribute.PRODUCTION.getName())){
            chocolateCandy.setProduction(element.getAttribute(CandyXmlAttribute.PRODUCTION.getName()));
        }

        return chocolateCandy;
    }

    public void buildCandy(AbstractCandy candy, Element element){
        candy.setEnergy(getIntContent(element, CandyXmlTag.ENERGY.getName()));
        candy.setName(getTextContent(element, CandyXmlTag.CANDY_NAME.getName()));
        candy.setCandyType(CandyType.getCandyType(getTextContent(element, CandyXmlTag.CANDY_TYPE.getName())));
        candy.setDate(getDateContent(element, CandyXmlTag.DATE.getName()));
        candy.setIngredients(buildIngredients(element));
        candy.setValues(buildValues(element));
        candy.setId(element.getAttribute(CandyXmlAttribute.ID.getName()));
    }

    public List<Value> buildValues(Element element){
        NodeList valuesList = element.getElementsByTagName(CandyXmlTag.VALUES.getName());
        Element valuesNode = (Element) valuesList.item(0);
        NodeList valueList = valuesNode.getElementsByTagName(CandyXmlTag.VALUE.getName());
        List<Value> values = new ArrayList<Value>();

        for(int i = 0; i < valueList.getLength(); i++){
            values.add(getValue((Element) valueList.item(0)));
        }

        return values;
    }

    public Value getValue(Element element){
        Value value = new Value();
        String name = getTextContent(element, CandyXmlTag.VALUE_NAME.getName());
        int weight = getIntContent(element, CandyXmlTag.VALUE_WEIGHT.getName());
        value.setName(name);
        value.setWeight(weight);

        return value;
    }

    public List<Ingredient> buildIngredients(Element element){
        NodeList ingredientsList = element.getElementsByTagName(CandyXmlTag.INGREDIENTS.getName());
        Element ingredientsNode = (Element) ingredientsList.item(0);
        NodeList ingredientList = ingredientsNode.getElementsByTagName(CandyXmlTag.INGREDIENT.getName());
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        for(int i = 0; i < ingredientList.getLength(); i++){
            ingredients.add(getIngredient((Element) ingredientList.item(i)));
        }

        return ingredients;
    }

    public Ingredient getIngredient(Element element){
        Ingredient ingredient = new Ingredient();
        String name = getTextContent(element, CandyXmlTag.INGREDIENT_NAME.getName());
        int weight = getIntContent(element, CandyXmlTag.INGREDIENT_WEIGHT.getName());
        ingredient.setWeight(weight);
        ingredient.setName(name);
        return ingredient;
    }

    public String getTextContent(Element element, String name){
        Node node = element.getElementsByTagName(name).item(0);
        return node.getTextContent();
    }

    public Date getDateContent(Element element, String name){
        return Date.valueOf(getTextContent(element, name));
    }

    public int getIntContent(Element element, String name){
        String intElement = getTextContent(element, name);
        return Integer.parseInt(intElement);
    }

    public boolean getBooleanContent(Element element, String name){
        String booleanElement = getTextContent(element, name);
        return Boolean.parseBoolean(booleanElement);
    }
}
