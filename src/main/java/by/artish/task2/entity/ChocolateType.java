package by.artish.task2.entity;

import by.artish.task2.exception.XMLParserException;

public enum ChocolateType {
    POROUS("porous"),
    LACTIC("lactic"),
    BITTER("bitter");

    private String type;

    ChocolateType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public static ChocolateType getChocolateType(String type){
        for (ChocolateType chocolateType : ChocolateType.values()) {
            if(chocolateType.getType().equals(type)){
                return chocolateType;
            }
        }

        return null;
    }
}
