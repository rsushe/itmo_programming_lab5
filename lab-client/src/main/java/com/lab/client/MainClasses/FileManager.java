package com.lab.client.MainClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSyntaxException;
import com.lab.client.Data.Route;
import com.lab.client.Exceptions.FileReadPermissionException;
import com.lab.client.Exceptions.RouteValidateException;
import com.lab.client.Utility.RouteValidator;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.Scanner;

/**
 * Class that operates all files
 */
public class FileManager {
    private final File file;

    public FileManager(File file) throws FileNotFoundException {
        this.file = file;
        if (!file.exists()) {
            throw new FileNotFoundException("Файла с таким названием не существует");
        }
        if (file.exists() && file.isDirectory()) {
            throw new FileNotFoundException("По введенному пути находится директория, а не файл");
        }
        if (!file.canRead()) {
            throw new FileReadPermissionException("Нет прав для чтения файла");
        }
    }

    /**
     * turn input file to list of string contains files insides
     * @return list of string
     * @throws FileNotFoundException if file doesn't exist
     */
    public List<String> fileToStringList() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        return list;
    }

    /**
     * reads and return route from json file using JsonParser utility class
     * @return list of routes from file
     */
    public List<Route> readElementsFromFile() throws FileNotFoundException {
        StringBuilder inputArray = new StringBuilder();
        for (String string : fileToStringList()) {
            inputArray.append(string);
        }

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonPrimitive) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString())).create();
        Route[] routes;
        try {
            routes = gson.fromJson(inputArray.toString(), Route[].class);
        } catch (JsonSyntaxException | NumberFormatException | DateTimeParseException e) {
            throw new RouteValidateException("В исходном JSON-файле содержатся ошибки");
        }
        RouteValidator.validateRoutes(routes);
        return new ArrayList<>(Arrays.asList(routes));
    }

    /**
     * save collection to json file
     * @param set collection to save
     */
    public void saveToFile(Set<Route> set) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))).setPrettyPrinting().create();
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            String toPrint = gson.toJson(set);
            out.write(toPrint.getBytes());
            System.out.println("Коллекция успешно сохранена в файл");
        } catch (IOException e) {
            System.out.println("Нет прав записи в файл. Коллекция не сохранена.");
        }
    }
}
