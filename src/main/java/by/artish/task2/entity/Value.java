package by.artish.task2.entity;

import java.util.Objects;

public class Value {
    private String name;
    private int weight;

    public Value(){
    }

    public Value(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value = (Value) o;

        return weight == value.weight && Objects.equals(name, value.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    @Override
    public String toString() {
        return "Value{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
