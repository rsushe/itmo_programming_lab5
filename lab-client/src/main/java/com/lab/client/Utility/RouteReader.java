package com.lab.client.Utility;

import com.lab.client.Data.Coordinates;
import com.lab.client.Data.Location;
import com.lab.client.Data.Route;
import com.lab.client.Exceptions.ReadElementFromScriptException;

import java.util.Locale;
import java.util.Scanner;

/**
 * Class that read new Route from console or from script
 */
public class RouteReader {
    private Scanner scanner;

    public RouteReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * @param scanner new scanner
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * @return return current scanner
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * @return new Route read from console
     */
    public Route readRouteFromConsole() {
        return new Route(readName(), readCoordinates(), readFrom(), readTo(), readDistance());
    }

    /**
     * @return new Route read from script
     */
    public Route readRouteFromScript() {
        try {
            String routeName = scanner.nextLine();

            int coordinatesX = Integer.parseInt(scanner.nextLine());
            long coordinatesY = Long.parseLong(scanner.nextLine());

            int fromX = Integer.parseInt(scanner.nextLine());
            int fromY = Integer.parseInt(scanner.nextLine());
            double fromZ = Double.parseDouble(scanner.nextLine());
            String fromName = scanner.nextLine();

            int toX = Integer.parseInt(scanner.nextLine());
            int toY = Integer.parseInt(scanner.nextLine());
            double toZ = Double.parseDouble(scanner.nextLine());
            String toName = scanner.nextLine();

            double distance = Double.parseDouble(scanner.nextLine());
            Route route = new Route(routeName, new Coordinates(coordinatesX, coordinatesY),
                    new Location(fromX, fromY, fromZ, fromName), new Location(toX, toY, toZ, toName), distance);
            RouteValidator.validateRoutes(route);
            return route;
        } catch (Exception e) {
            throw new ReadElementFromScriptException("Ошибка при чтении элемента из скрипта. Проверьте правильность данных", e);
        }
    }

    /**
     * @return read name of Route from console
     */
    public String readName() {
        System.out.print("Введите название маршрута: ");
        String name = scanner.nextLine();
        while (name == null || name.isEmpty()) {
            System.out.print("Название маршрута не может быть пустым, повторите попытку: ");
            name = scanner.nextLine();
        }
        return name;
    }

    /**
     * @return read coordinates of Route from console
     */
    public Coordinates readCoordinates() {
        final int xMaxValue = 412;
        int x;
        long y;
        System.out.print("Введите координату X маршрута: ");
        x = readInt();
        while (x > xMaxValue) {
            System.out.print("Координата X не может быть больше 412, повторите попытку: ");
            x = readInt();
        }
        System.out.print("Введите координату Y маршрута: ");
        y = readLong();
        return new Coordinates(x, y);
    }

    /**
     * @return read start location of Route from console
     */
    public Location readFrom() {
        System.out.print("Введите координату X точки начала маршрута: ");
        int x = readInt();
        System.out.print("Введите координату Y точки начала маршрута: ");
        int y = readInt();
        System.out.print("Введите координату Z точки начала маршрута: ");
        double z = readDouble();
        System.out.print("Введите название начальной локации маршрута: ");
        String name = scanner.nextLine();
        while (name == null || name.isEmpty()) {
            System.out.print("Имя начальной локации не может быть пустым, повторите попытку: ");
            name = scanner.nextLine();
        }
        return new Location(x, y, z, name);
    }

    /**
     * @return read end location of Route from console
     */
    public Location readTo() {
        String response;
        while (true) {
            System.out.print("Известна ли конечная точка маршрута? (y/n): ");
            response = scanner.nextLine().toLowerCase(Locale.ROOT);
            if ("n".equals(response)) {
                return null;
            } else if ("y".equals(response)) {
                System.out.print("Введите координату X точки конца маршрута: ");
                int x = readInt();
                System.out.print("Введите координату Y точки конца маршрута: ");
                int y = readInt();
                System.out.print("Введите координату Z точки конца маршрута: ");
                double z = readDouble();
                System.out.print("Введите название конечной локации маршрута: ");
                String name = scanner.nextLine();
                while (name == null || name.isEmpty()) {
                    System.out.print("Имя конечной локации не может быть пустым, повторите попытку: ");
                    name = scanner.nextLine();
                }
                return new Location(x, y, z, name);
            }
        }
    }

    /**
     * @return read distance of Route from console
     */
    public double readDistance() {
        System.out.print("Введите дистанцию маршрута: ");
        double value = readDouble();
        while (Double.compare(value, 1) <= 0) {
            System.out.print("Дистанция должна быть больше 1, повторите попытку: ");
            value = readDouble();
        }
        return value;
    }

    private int readInt() {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Ошибка при вводе, повторите попытку: ");
            }
        }
        return value;
    }

    private long readLong() {
        long value;
        while (true) {
            try {
                value = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Ошибка при вводе, повторите попытку: ");
            }
        }
        return value;
    }

    private double readDouble() {
        double value;
        while (true) {
            try {
                value = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException | NullPointerException e) {
                System.out.print("Ошибка при вводе, повторите попытку: ");
            }
        }
        return value;
    }
}
