package by.artish.task2.entity;

import java.sql.Date;
import java.util.List;

public class ChocolateCandy extends AbstractCandy {
    private boolean filling;
    private ChocolateType chocolateType;

    public ChocolateCandy() {
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

    public static class ChocolateCandyBuilder extends AbstractCandyBuilder<ChocolateCandy> {
        public ChocolateCandyBuilder() {
            candy = new ChocolateCandy();
        }

        public ChocolateCandyBuilder withChocolateType(ChocolateType chocolateType) {
            candy.chocolateType = chocolateType;
            return this;
        }

        public ChocolateCandyBuilder withFilling(boolean filling) {
            candy.filling = filling;
            return this;
        }
    }
}
