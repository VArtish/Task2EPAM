package by.artish.task2.parser.builder;

import by.artish.task2.entity.AbstractCandy;
import by.artish.task2.exception.XMLParserException;

import java.util.ArrayList;
import java.util.List;

public abstract class ParserBaseBuilder {
    protected List<AbstractCandy> candies;

    public ParserBaseBuilder() {
        candies = new ArrayList<AbstractCandy>();
    }

    public abstract void buildCandies(String path) throws XMLParserException;

    public List<AbstractCandy> getCandies() {
        return List.copyOf(candies);
    }
}
