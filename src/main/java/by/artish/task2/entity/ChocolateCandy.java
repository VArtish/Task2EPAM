package by.artish.task2.entity;

import java.sql.Date;
import java.util.List;

public class ChocolateCandy extends AbstractCandy{
    private boolean filling;
    private ChocolateType chocolateType;

    public ChocolateCandy(){
    }

    public void setFilling(boolean filling) {
        this.filling = filling;
    }

    public void setChocolateType(ChocolateType chocolateType) {
        this.chocolateType = chocolateType;
    }

    public boolean isFilling() {
        return filling;
    }

    public ChocolateType getChocolateType() {
        return chocolateType;
    }

    public static class ChocolateCandyBuilder{
        private ChocolateCandy chocolateCandy;

        public ChocolateCandyBuilder() {
            chocolateCandy = new ChocolateCandy();
        }

        public ChocolateCandyBuilder(ChocolateCandy chocolateCandy) {
            this.chocolateCandy = chocolateCandy;
        }

        public ChocolateCandyBuilder withChocolateType(ChocolateType chocolateType){
            chocolateCandy.chocolateType = chocolateType;
            return this;
        }

        public ChocolateCandyBuilder withFilling(boolean filling){
            chocolateCandy.filling = filling;
            return this;
        }

        public ChocolateCandyBuilder withId(String id){
            chocolateCandy.id = id;
            return this;
        }

        public ChocolateCandyBuilder withProduction(String production){
            chocolateCandy.production = production;
            return this;
        }

        public ChocolateCandyBuilder withEnergy(int energy){
            chocolateCandy.energy = energy;
            return this;
        }

        public ChocolateCandyBuilder withCandyType(CandyType candyType){
            chocolateCandy.candyType = candyType;
            return this;
        }

        public ChocolateCandyBuilder withDate(Date date){
            chocolateCandy.date = date;
            return this;
        }

        public ChocolateCandyBuilder withName(String name){
            chocolateCandy.name = name;
            return this;
        }

        public ChocolateCandyBuilder withValues(List<Value> values){
            chocolateCandy.values = values;
            return this;
        }

        public ChocolateCandyBuilder withIngredients(List<Ingredient> ingredients){
            chocolateCandy.ingredients = ingredients;
            return this;
        }

        public ChocolateCandy build(){
            return chocolateCandy;
        }
    }
}
