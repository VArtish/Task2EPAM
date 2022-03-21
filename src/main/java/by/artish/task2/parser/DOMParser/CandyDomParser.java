package by.artish.task2.parser.DOMParser;

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
            NodeList caramelCandies = root.getElementsByTagName("caramel-candy");
            NodeList chocolateCandies = root.getElementsByTagName("chocolate-candy");

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
        caramelCandy.setLollipop(getBooleanContent(element, "lollipop"));

        if(element.hasAttribute("production")){
            caramelCandy.setProduction(element.getAttribute("production"));
        }

        return caramelCandy;
    }

    public ChocolateCandy buildChocolateCandy(Element element){
        ChocolateCandy chocolateCandy = new ChocolateCandy();
        buildCandy(chocolateCandy, element);
        chocolateCandy.setFilling(getBooleanContent(element, "filling"));
        chocolateCandy.setChocolateType(ChocolateType.getChocolateType(getTextContent(element, "chocolate-type")));

        if(element.hasAttribute("production")){
            chocolateCandy.setProduction(element.getAttribute("production"));
        }

        return chocolateCandy;
    }

    public void buildCandy(AbstractCandy candy, Element element){
        candy.setEnergy(getIntContent(element, "energy"));
        candy.setName(getTextContent(element, "name"));
        candy.setCandyType(CandyType.getCandyType(getTextContent(element, "type")));
        candy.setDate(getDateContent(element, "date"));
        candy.setIngredients(buildIngredients(element));
        candy.setValues(buildValues(element));
        candy.setId(element.getAttribute("id"));
    }

    public List<Value> buildValues(Element element){
        NodeList valuesList = element.getElementsByTagName("values");
        Element valuesNode = (Element) valuesList.item(0);
        NodeList valueList = valuesNode.getElementsByTagName("value");
        List<Value> values = new ArrayList<Value>();

        for(int i = 0; i < valueList.getLength(); i++){
            values.add(getValue((Element) valueList.item(0)));
        }

        return values;
    }

    public Value getValue(Element element){
        Value value = new Value();
        String name = getTextContent(element, "value-name");
        int weight = getIntContent(element, "value-weight");
        value.setName(name);
        value.setWeight(weight);

        return value;
    }

    public List<Ingredient> buildIngredients(Element element){
        NodeList ingredientsList = element.getElementsByTagName("ingredients");
        Element ingredientsNode = (Element) ingredientsList.item(0);
        NodeList ingredientList = ingredientsNode.getElementsByTagName("ingredient");
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        for(int i = 0; i < ingredientList.getLength(); i++){
            ingredients.add(getIngredient((Element) ingredientList.item(i)));
        }

        return ingredients;
    }

    public Ingredient getIngredient(Element element){
        Ingredient ingredient = new Ingredient();
        String name = getTextContent(element, "ingredient-name");
        int weight = getIntContent(element, "ingredient-weight");
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
