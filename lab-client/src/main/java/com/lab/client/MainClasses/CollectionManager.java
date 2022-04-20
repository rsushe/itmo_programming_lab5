package com.lab.client.MainClasses;

import com.lab.client.Data.Route;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Class that manage collection
 */
public class CollectionManager {
    private final NavigableSet<Route> routes;
    private final java.time.LocalDateTime creationDate;

    public CollectionManager(List<Route> routes) {
        this.routes = new TreeSet<>(Comparator.comparing(Route::getDistance));
        this.routes.addAll(routes);
        creationDate = java.time.LocalDateTime.now();
    }

    /**
     * @return current collection
     */
    public NavigableSet<Route> getCollection() {
        return new TreeSet<>(routes);
    }

    /**
     * @return collection creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return collection name
     */
    public String getCollectionName() {
        return routes.getClass().toString();
    }

    /**
     * @return size of collection
     */
    public int getSize() {
        return routes.size();
    }

    /**
     * @param route new Route to add to collection
     * @return true if element successfully added to collection, else return false
     */
    public boolean add(Route route) {
        return routes.add(route);
    }

    /**
     * @return true if collection contains Route with id, else return false
     */
    public boolean existElementWithId(Long id) {
        for (Route setRoute : routes) {
            if (setRoute.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * update Route with given id with the data from given route
     */
    public void updateById(Long id, Route route) {
        for (Route setRoute : routes) {
            if (setRoute.getId().equals(id)) {
                setRoute.update(route);
            }
        }
    }

    /**
     * if collection contains element with given id, this element will be removed, else collection will not be changed
     */
    public void removeById(Long id) {
        routes.removeIf(setRoute -> setRoute.getId().equals(id));
    }

    /**
     * remove all elements from collection
     */
    public void clear() {
        routes.clear();
    }

    /**
     * add new element to collection if distance of given route less than the minimal distance of routes in collection
     * @param route new route that will be added if condition will be true
     * @return true if element added, return false else
     */
    public boolean addIfMin(Route route) {
        boolean success = false;
        if (Double.compare(route.getDistance(), routes.first().getDistance()) < 0) {
            success = routes.add(route);
        }
        return success;
    }

    /**
     * remove all routes in collection which distance greater than distance of given route
     */
    public void removeGreater(Route route) {
        routes.removeIf(setRoute -> Double.compare(setRoute.getDistance(), route.getDistance()) > 0);
    }

    /**
     * remove all routes in collection which distance lower than distance of given route
     */
    public void removeLower(Route route) {
        routes.removeIf(setRoute -> Double.compare(setRoute.getDistance(), route.getDistance()) < 0);
    }

    /**
     * @return route from collection with maximum distance
     */
    public Route maxByDistance() {
        return routes.last();
    }

    /**
     * @return number of routes which distance is lower than the given distance
     */
    public int countLessThanDistance(double distance) {
        int countElements = 0;
        for (Route route : routes) {
            if (Double.compare(route.getDistance(), distance) < 0) {
                countElements++;
            }
        }
        return countElements;
    }

    /**
     * @return number of routes which distance is greater than the given distance
     */
    public int countGreaterThanDistance(double distance) {
        int countElements = 0;
        for (Route route : routes) {
            if (Double.compare(route.getDistance(), distance) > 0) {
                countElements++;
            }
        }
        return countElements;
    }
}
