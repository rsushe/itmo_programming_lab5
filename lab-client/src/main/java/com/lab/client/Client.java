package com.lab.client;

import com.lab.client.Exceptions.FileReadPermissionException;
import com.lab.client.Exceptions.RouteValidateException;
import com.lab.client.MainClasses.CollectionManager;
import com.lab.client.MainClasses.CommandManager;
import com.lab.client.MainClasses.Console;
import com.lab.client.MainClasses.FileManager;
import com.lab.client.Utility.RouteReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class that start interactive mode
 *
 * @author Sushenko Roman P3115
 */
public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        try {
            String fileName = args[0];
            File file = new File(fileName);
            Scanner scanner = new Scanner(System.in);

            RouteReader routeReader = new RouteReader(scanner);
            FileManager fileManager = new FileManager(file);
            CollectionManager collectionManager = new CollectionManager(fileManager.readElementsFromFile());
            CommandManager commandManager = new CommandManager(fileManager, routeReader, collectionManager);

            Console console = new Console(scanner, commandManager);
            console.startInteractiveMode();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Имя файла не указано");
        } catch (FileNotFoundException | FileReadPermissionException | RouteValidateException e) {
            System.out.println(e.getMessage());
        }
    }
}
