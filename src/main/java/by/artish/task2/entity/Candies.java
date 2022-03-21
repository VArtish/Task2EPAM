package by.artish.task2.entity;

import java.util.ArrayList;
import java.util.List;

public class Candies {
    private List<AbstractCandy> candies;

    {
        candies = new ArrayList<AbstractCandy>();
    }

    public Candies(){
    }

    public Candies(List<AbstractCandy> candies){
        this.candies = candies;
    }

    public List<AbstractCandy> getCandies(){
        return List.copyOf(candies);
    }

    public void setCandies(List<AbstractCandy> candies){
        this.candies = candies;
    }
}
