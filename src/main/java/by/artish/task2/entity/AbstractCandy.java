package by.artish.task2.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractCandy {
    protected String id;
    protected String production;
    protected int energy;
    protected CandyType candyType;
    protected List<Value> values;
    protected List<Ingredient> ingredients;
    protected Date date;
    protected String name;

    {
        ingredients = new ArrayList<Ingredient>();
        values = new ArrayList<Value>();
    }

    public AbstractCandy() {
    }

    public AbstractCandy(String id, String production, int energy, CandyType candyType, List<Value> values, List<Ingredient> ingredients, Date date, String name) {
        this.id = id;
        this.production = production;
        this.energy = energy;
        this.candyType = candyType;
        this.values = values;
        this.ingredients = ingredients;
        this.date = date;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setCandyType(CandyType candyType) {
        this.candyType = candyType;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public String getId() {
        return id;
    }

    public String getProduction() {
        return production;
    }

    public CandyType getCandyType() {
        return candyType;
    }

    public List<Value> getValues() {
        return values;
    }

    public Date getDate() {
        return date;
    }

    public List<Ingredient> getIngredients() {
        return List.copyOf(ingredients);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AbstractCandy{" +
                "id='" + id + '\'' +
                ", production='" + production + '\'' +
                ", energy=" + energy +
                ", candyType=" + candyType +
                ", values=" + values +
                ", ingredients=" + ingredients +
                ", date=" + date +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCandy that = (AbstractCandy) o;

        return energy == that.energy && Objects.equals(id, that.id) && Objects.equals(production, that.production) && candyType == that.candyType && Objects.equals(values, that.values) && Objects.equals(ingredients, that.ingredients) && Objects.equals(date, that.date) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, production, energy, candyType, values, ingredients, date, name);
    }
}
