package by.artish.task2.parser;

public enum CandyXmlAttribute {
    ID("id"),
    PRODUCTION("production");
    private String name;

    CandyXmlAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CandyXmlAttribute getCandyXmlAttribute(String name) {
        for (CandyXmlAttribute attribute : CandyXmlAttribute.values()) {
            if (name.equals(attribute.getName())) {
                return attribute;
            }
        }

        return null;
    }
}