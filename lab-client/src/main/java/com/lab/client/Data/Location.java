package com.lab.client.Data;

import javax.validation.constraints.NotNull;

/**
 * Location of Route
 */
public class Location {
    @NotNull
    private Integer x; //Поле не может быть null
    private int y;
    @NotNull
    private Double z; //Поле не может быть null
    @NotNull
    private String name; //Строка не может быть пустой, Поле не может быть null

    public Location(Integer x, int y, Double z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     * @return x coordinate of Location
     */
    public Integer getX() {
        return x;
    }

    /**
     * @return y coordinate of Location
     */
    public int getY() {
        return y;
    }

    /**
     * @return z coordinate of Location
     */
    public Double getZ() {
        return z;
    }

    /**
     * @return name of Location
     */
    public String getName() {
        return name;
    }

    /**
     * @return Location represented by beautiful String
     */
    @Override
    public String toString() {
        return "{" + x + ", " + y + ", " + z + ", \"" + name + "\"}";
    }
}
