package com.lab.client.MainClasses;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class that start interactive mode and operates user command
 */
public class Console {
    private final CommandManager commandManager;
    private final Scanner scanner;

    public Console(Scanner scanner, CommandManager commandManager) {
        this.scanner = scanner;
        this.commandManager = commandManager;
    }

    /**
     * start reading and operates user command until end
     */
    public void startInteractiveMode() {
        System.out.println("Файл успешно считан. Добро пожаловать.");
        System.out.println("Доступные команды: [info, show, add, update, remove_by_id, clear, save, execute_script, exit, add_if_min, remove_greater, remove_lower, max_by_distance, count_less_than_distance, count_greater_than_distance]");
        System.out.println("Если хотите узнать описание конкретной команды, введите команду help");

        String inputLine;
        boolean needExit = false;
        while (!needExit) {
            System.out.print("Введите команду: ");
            try {
                inputLine = scanner.nextLine();
            } catch (NoSuchElementException e) {
                break;
            }
            needExit = commandManager.executeCommand(inputLine);
        }
        System.out.println("Исполнение программы остановлено");
    }
}
