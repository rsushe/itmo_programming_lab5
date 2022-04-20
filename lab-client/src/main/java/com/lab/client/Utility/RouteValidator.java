package com.lab.client.Utility;

import com.lab.client.Data.Route;
import com.lab.client.Exceptions.RouteValidateException;

import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that validate Route
 * This class should not be instantiated
 */
public final class RouteValidator {
    private static final Map<Long, Long> ID_MAP;
    private static ValidatorFactory factory;
    private static Validator validator;

    static {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        ID_MAP = new HashMap<>();
    }

    private RouteValidator() {
        throw new UnsupportedOperationException("This class should not be instantiated");
    }

    /**
     * validate input routes using hibernate validator
     * @param routes input routes to validate
     */
    public static void validateRoutes(Route... routes) {
        long maxId = 0;
        for (Route route : routes) {
            ID_MAP.put(route.getId(), ID_MAP.getOrDefault(route.getId(), 0L) + 1);
            if (validator.validate(route).size() > 0 || validator.validate(route.getFrom()).size() > 0
                    || (route.getTo() != null && validator.validate(route.getTo()).size() > 0)
                    || validator.validate(route.getCoordinates()).size() > 0 || ID_MAP.get(route.getId()) > 1) {
                throw new RouteValidateException("В исходном JSON-файле содержатся ошибки");
            }
            maxId = Math.max(maxId, route.getId());
        }
        Route.setNextId(maxId + 1);
    }
}
