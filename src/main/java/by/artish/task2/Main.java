package by.artish.task2;

import by.artish.task2.parser.StAXParser.CandyStAXParser;
import by.artish.task2.entity.AbstractCandy;
import by.artish.task2.exception.XMLParserException;

import java.util.List;

public class Main {
    public static void main(String[] args) throws XMLParserException {
        CandyStAXParser parser  = new CandyStAXParser();
        try {
            parser.buildCandies("D:\\Task2EPAM\\src\\main\\resources\\candy.xml");
        } catch(XMLParserException e){
            e.getMessage();
        }
        List<AbstractCandy> candies = parser.getCandies();

        for(AbstractCandy candy: candies){
            System.out.println(candy.getName());
            System.out.println(candy.getCandyType());
        }
    }
}
