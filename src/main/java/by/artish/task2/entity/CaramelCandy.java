package by.artish.task2.entity;

import java.sql.Date;
import java.util.List;

public class CaramelCandy extends AbstractCandy {
    private boolean lollipop;

    public CaramelCandy() {
    }

    public CaramelCandy(boolean lollipop) {
        this.lollipop = lollipop;
    }

    public void setLollipop(boolean lollipop) {
        this.lollipop = lollipop;
    }

    public boolean isLollipop() {
        return lollipop;
    }

    public static class CaramelCandyBuilder{
        private CaramelCandy caramelCandy;

        public CaramelCandyBuilder() {
            caramelCandy = new CaramelCandy();
        }

        public CaramelCandyBuilder(CaramelCandy caramelCandy) {
            this.caramelCandy = caramelCandy;
        }

        public CaramelCandyBuilder withLollipop(boolean lollipop){
            caramelCandy.lollipop = lollipop;
            return this;
        }

        public CaramelCandyBuilder withId(String id){
            caramelCandy.id = id;
            return this;
        }

        public CaramelCandyBuilder withProduction(String production){
            caramelCandy.production = production;
            return this;
        }

        public CaramelCandyBuilder withEnergy(int energy){
            caramelCandy.energy = energy;
            return this;
        }

        public CaramelCandyBuilder withCandyType(CandyType candyType){
            caramelCandy.candyType = candyType;
            return this;
        }

        public CaramelCandyBuilder withDate(Date date){
            caramelCandy.date = date;
            return this;
        }

        public CaramelCandyBuilder withName(String name){
            caramelCandy.name = name;
            return this;
        }

        public CaramelCandyBuilder withValues(List<Value> values){
            caramelCandy.values = values;
            return this;
        }

        public CaramelCandyBuilder withIngredients(List<Ingredient> ingredients){
            caramelCandy.ingredients = ingredients;
            return this;
        }

        public CaramelCandy build(){
            return caramelCandy;
        }
    }
}
