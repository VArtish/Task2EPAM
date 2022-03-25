package by.artish.task2.entity;

import java.sql.Date;
import java.util.List;

public abstract class AbstractCandyBuilder<T extends AbstractCandy> {
    protected T candy;

    public AbstractCandyBuilder(T candy) {
        this.candy = candy;
    }

    public AbstractCandyBuilder() {
    }

    public AbstractCandyBuilder<T> withId(String id) {
        candy.id = id;
        return this;
    }

    public AbstractCandyBuilder<T> withProduction(String production) {
        candy.production = production;
        return this;
    }

    public AbstractCandyBuilder<T> withEnergy(int energy) {
        candy.energy = energy;
        return this;
    }

    public AbstractCandyBuilder<T> withCandyType(CandyType candyType) {
        candy.candyType = candyType;
        return this;
    }

    public AbstractCandyBuilder<T> withDate(Date date) {
        candy.date = date;
        return this;
    }

    public AbstractCandyBuilder<T> withName(String name) {
        candy.name = name;
        return this;
    }

    public AbstractCandyBuilder<T> withValues(List<Value> values) {
        candy.values = values;
        return this;
    }

    public AbstractCandyBuilder<T> withIngredients(List<Ingredient> ingredients) {
        candy.ingredients = ingredients;
        return this;
    }

    public T build() {
        return candy;
    }

}
