package by.artish.task2.entity;

import by.artish.task2.exception.XMLParserException;

public enum CandyType {
    CARAMEL("caramel"),
    CHOCOLATE("chocolate");

    private String type;

    CandyType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public static CandyType getCandyType(String type) {
        for (CandyType candyType : CandyType.values()) {
            if(candyType.getType().equals(type)){
                return candyType;
            }
        }

        return null;
    }
}
