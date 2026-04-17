package src;

import java.time.LocalDate;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Start {

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите: " +
                    "\n1 Создать Task" +
                    "\n2 Посмотреть все Task" +
                    "\n3 Выйти");

            int input = sc.nextInt();
            sc.nextLine();

            if (input == 1) {
                createTask(sc);
            } else if (input == 2) {
                showTask();
            } else if (input == 3) {
                System.out.println("Пока");
                break;
            }
        }
        sc.close();
    }

    private void createTask(Scanner sc) {
        System.out.print("Название задачи: ");
        String taskName = sc.nextLine();

        System.out.print("Описание задачи: ");
        String description = sc.nextLine();

        Task task = new Task(taskName, description);

        // Запись в файл
        try (FileWriter writer = new FileWriter("Tasks.txt", true)) {
            writer.write("ID: " + task.getId() + "\n");
            writer.write("Название: " + task.getName() + "\n");
            writer.write("Описание: " + task.getDescription() + "\n");
            writer.write("Дата: " + task.getDate() + "\n");
            System.out.println("Задача сохранена");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private void showTask() {
        File file = new File("Tasks.txt");

        try (Scanner sc = new Scanner(file)) {
            System.out.println("\n=== Все задачи ===");
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл с задачами не найден. Сначала создайте хотя бы одну задачу.");
        }
    }
}

class Task {
    private String name;
    private String description;
    private int id;
    private LocalDate date;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = new Random().nextInt(10000);
        this.date = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }
}
