package by.artish.task2.entity;

import java.sql.Date;
import java.util.List;

public class CaramelCandy extends AbstractCandy {
    private boolean lollipop;

    public CaramelCandy() {
    }

    public void setLollipop(boolean lollipop) {
        this.lollipop = lollipop;
    }

    public boolean isLollipop() {
        return lollipop;
    }

    public static class CaramelCandyBuilder extends AbstractCandyBuilder<CaramelCandy> {
        public CaramelCandyBuilder() {
            this.candy = new CaramelCandy();
        }

        public CaramelCandyBuilder withLollipop(boolean lollipop) {
            candy.lollipop = lollipop;
            return this;
        }
    }
}
