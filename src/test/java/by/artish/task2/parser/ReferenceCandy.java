package by.artish.task2.parser;

import by.artish.task2.entity.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReferenceCandy {

    private static List<AbstractCandy> candiesList = new ArrayList<>();

    public static List<AbstractCandy> getCandyList() {
        List<Value> values = new ArrayList<Value>();
        values.add(new Value("Ahaha", 103));
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(new Ingredient("Ahaha", 103));
        AbstractCandy candy1 = new CaramelCandy.CaramelCandyBuilder().withLollipop(true).withEnergy(123).withDate(Date.valueOf("2020-05-11"))
                .withName("123").withValues(values).withIngredients(ingredients).withId("Candy22").withProduction("production").withCandyType(CandyType.getCandyType("caramel")).build();
        AbstractCandy candy2 = new ChocolateCandy.ChocolateCandyBuilder().withFilling(true).withChocolateType(ChocolateType.getChocolateType("lactic")).withCandyType(CandyType.getCandyType("caramel")).withEnergy(123).withDate(Date.valueOf("2020-05-11"))
                .withName("123").withValues(values).withIngredients(ingredients).withId("Candy2").withProduction("production").build();
        candiesList.add(candy1);
        candiesList.add(candy2);
        return candiesList;
    }
}