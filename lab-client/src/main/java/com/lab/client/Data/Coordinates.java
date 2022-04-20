package com.lab.client.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Coordinates of Route represented by x, y coordinates
 */
public class Coordinates {
    private static final int MAX_X_VALUE = 412;
    @Max(MAX_X_VALUE)
    private int x; //Максимальное значение поля: 412
    @NotNull
    private Long y; //Поле не может быть null

    public Coordinates(int x, Long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return y coordinate
     */
    public Long getY() {
        return y;
    }

    /**
     * @return coordinates represented by beautiful string
     */
    @Override
    public String toString() {
        return "{" + x + ", " + y + "}";
    }
}
