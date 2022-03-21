package by.artish.task2.entity;

import java.util.Objects;

public class Ingredient {
    private String name;
    private int weight;

    public Ingredient(){
    }

    public Ingredient(String name){
        this.name = name;
    }

    public Ingredient(int weight){
        this.weight = weight;
    }

    public Ingredient(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;

        return weight == that.weight && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
