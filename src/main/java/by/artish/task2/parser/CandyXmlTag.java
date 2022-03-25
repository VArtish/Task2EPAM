package by.artish.task2.parser;

public enum CandyXmlTag {
    CANDIES("candies"),
    CARAMEL_CANDY("caramel-candy"),
    CHOCOLATE_CANDY("chocolate-candy"),
    INGREDIENTS("ingredients"),
    INGREDIENT("ingredient"),
    VALUES("values"),
    VALUE("value"),
    CANDY_NAME("name"),
    CHOCOLATE_TYPE("chocolate-type"),
    LOLLIPOP("lollipop"),
    FILLING("filling"),
    INGREDIENT_NAME("ingredient-name"),
    INGREDIENT_WEIGHT("ingredient-weight"),
    VALUE_WEIGHT("value-weight"),
    VALUE_NAME("value-name"),
    ENERGY("energy"),
    DATE("date"),
    CANDY_TYPE("type");

    private String name;

    CandyXmlTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CandyXmlTag getCandyXmlTag(String name) {
        for (CandyXmlTag tag : CandyXmlTag.values()) {
            if (name.equals(tag.getName())) {
                return tag;
            }
        }

        return null;
    }
}
