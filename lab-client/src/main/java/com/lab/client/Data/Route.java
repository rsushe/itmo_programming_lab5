package com.lab.client.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Main class that stored in collection
 */
public class Route {
    private static Long nextId = 0L;
    @NotNull
    @Min(1)
    private final Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    private Coordinates coordinates; //Поле не может быть null
    @NotNull
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @NotNull
    private Location from; //Поле не может быть null
    private Location to; //Поле может быть null
    @Min(1)
    private double distance; //Значение поля должно быть больше 1

    public Route(String name, Coordinates coordinates, Location from, Location to, double distance) {
        id = nextId++;
        creationDate = java.time.LocalDateTime.now();
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public static void setNextId(Long id) {
        nextId = id;
    }

    /**
     * @return id of Route
     */
    public Long getId() {
        return id;
    }

    /**
     * @return name of Route
     */
    public String getName() {
        return name;
    }

    /**
     * @return coordinates of Route
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return creation date of Route
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return location where Route starts
     */
    public Location getFrom() {
        return from;
    }

    /**
     * @return location where Route ends
     */
    public Location getTo() {
        return to;
    }

    /**
     * @return distance of Route
     */
    public double getDistance() {
        return distance;
    }

    /**
     * replace current route by new information
     * @param route route with new information
     */
    public void update(Route route) {
        name = route.name;
        coordinates = route.coordinates;
        from = route.from;
        to = route.to;
        distance = route.distance;
    }

    /**
     * @return route represented by beautiful string
     */
    @Override
    public String toString() {
        return "Id = " + id + ", name = \"" + name + "\", coordinates = " + coordinates + ", creation date = "
                + creationDate.toString() + ", from = " + from + ", to = " + to + ", distance = " + distance;
    }
}
