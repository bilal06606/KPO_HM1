package console;

import services.IZooService;
import services.IVetService;
import animals.*;
import things.*;

import java.util.Scanner;

/**
 * Консольное приложение для управления зоопарком.
 * Применяет принцип Single Responsibility.
 * Применяет принцип Dependency Inversion.
 */
public class ZooConsoleApp {
    private final Scanner scanner;
    private final IZooService zoo;
    private final IVetService veterinaryService;
    private boolean running;
    
    public ZooConsoleApp(IZooService zoo, IVetService veterinaryService) {
        this.scanner = new Scanner(System.in);
        this.zoo = zoo;
        this.veterinaryService = veterinaryService;
        this.running = true;
    }
    
    /**
     * Запускает консольное приложение.
     */
    public void run() {
        while (running) {
            showMainMenu();
            int choice = getIntInput("Выберите опцию: ");
            MenuChoice(choice);
        }
        scanner.close();
    }
    
    /**
     * Показывает главное меню.
     */
    private void showMainMenu() {
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("1. Добавить животное");
        System.out.println("2. Добавить вещь");
        System.out.println("3. Показать отчет по животным");
        System.out.println("4. Показать отчет по вещам");
        System.out.println("5. Показать животных для контактного зоопарка");
        System.out.println("6. Показать общее потребление еды");
        System.out.println("7. Показать полный отчет");
        System.out.println("8. Статистика зоопарка");
        System.out.println("0. Выход");
        System.out.println("==================");
    }
    
    /**
     * Обрабатывает выбор пользователя в главном меню.
     */
    private void MenuChoice(int choice) {
        switch (choice) {
            case 1 -> addAnimal();
            case 2 -> addThing();
            case 3 -> showAnimalsReport();
            case 4 -> showThingsReport();
            case 5 -> showContactZooAnimals();
            case 6 -> showTotalFoodConsumption();
            case 7 -> showFullReport();
            case 8 -> showZooStatistics();
            case 0 -> running = false;
            default -> System.out.println("Неверный выбор! Попробуйте снова.");
        }
    }
    
    /**
     * Добавляет новое животное в зоопарк.
     */
    private void addAnimal() {
        System.out.println("\n=== ДОБАВЛЕНИЕ ЖИВОТНОГО ===");
        
        System.out.println("Выберите тип животного:");
        System.out.println("1. Обезьяна");
        System.out.println("2. Кролик");
        System.out.println("3. Тигр");
        System.out.println("4. Волк");
        
        int animalType = getIntInput("Выберите тип (1-4): ");
        if (animalType < 1 || animalType > 4) {
            System.out.println("Неверный выбор типа животного!");
            return;
        }
        
        String name = getStringInput("Введите имя животного: ");
        int food = getIntInput("Введите количество еды в день (кг): ");
        int number = getIntInput("Введите инвентарный номер: ");
        
        Animal animal = createAnimal(animalType, name, food, number);
        if (animal != null) {
            boolean success = zoo.addAnimal(animal);
            if (success) {
                System.out.println("✓ Животное успешно добавлено в зоопарк!");
            } else {
                System.out.println("✗ Не удалось добавить животное в зоопарк.");
            }
        }
    }
    
    /**
     * Создает животное указанного типа.
     */
    private Animal createAnimal(int animalType, String name, int food, int number) {
        switch (animalType) {
            case 1 -> {
                int kindness = getIntInput("Введите уровень доброты обезьяны (1-10): ");
                return new Monkey(name, food, number, kindness);
            }
            case 2 -> {
                int kindness = getIntInput("Введите уровень доброты кролика (1-10): ");
                return new Rabbit(name, food, number, kindness);
            }
            case 3 -> {
                return new Tiger(name, food, number);
            }
            case 4 -> {
                return new Wolf(name, food, number);
            }
            default -> {
                System.out.println("Неверный тип животного!");
                return null;
            }
        }
    }

    private void addThing() {
        System.out.println("\n=== ДОБАВЛЕНИЕ ВЕЩИ ===");
        
        System.out.println("Выберите тип вещи:");
        System.out.println("1. Стол");
        System.out.println("2. Компьютер");
        
        int thingType = getIntInput("Выберите тип (1-2): ");
        if (thingType < 1 || thingType > 2) {
            System.out.println("Неверный выбор типа вещи!");
            return;
        }
        
        String name = getStringInput("Введите название вещи: ");
        int number = getIntInput("Введите инвентарный номер: ");
        
        Thing thing = createThing(thingType, name, number);
        if (thing != null) {
            boolean success = zoo.addThing(thing);
            if (success) {
                System.out.println("✓ Вещь успешно добавлена в инвентарь!");
            } else {
                System.out.println("✗ Не удалось добавить вещь в инвентарь.");
            }
        }
    }
    
    /**
     * Создает вещь указанного типа.
     */
    private Thing createThing(int thingType, String name, int number) {
        switch (thingType) {
            case 1 -> {
                return new Table(name, number);
            }
            case 2 -> {
                return new Computer(name, number);
            }
            default -> {
                System.out.println("Неверный тип вещи!");
                return null;
            }
        }
    }
    
    /**
     * Показывает отчет по животным.
     */
    private void showAnimalsReport() {
        System.out.println("\n=== ОТЧЕТ ПО ЖИВОТНЫМ ===");
        System.out.println(zoo.getAnimalsReport());
        waitForEnter();
    }
    
    /**
     * Показывает отчет по вещам.
     */
    private void showThingsReport() {
        System.out.println("\n=== ОТЧЕТ ПО ВЕЩАМ ===");
        System.out.println(zoo.getThingsReport());
        waitForEnter();
    }
    
    /**
     * Показывает животных для контактного зоопарка.
     */
    private void showContactZooAnimals() {
        System.out.println("\n=== ЖИВОТНЫЕ ДЛЯ КОНТАКТНОГО ЗООПАРКА ===");
        Animal[] contactAnimals = zoo.getContactZooAnimals();
        
        if (contactAnimals.length == 0) {
            System.out.println("Нет животных, подходящих для контактного зоопарка.");
        } else {
            System.out.println(String.format("Найдено %d животных для контактного зоопарка:", contactAnimals.length));
            for (Animal animal : contactAnimals) {
                System.out.println(String.format("- %s", animal.toString()));
            }
        }
        waitForEnter();
    }
    
    /**
     * Показывает общее потребление еды.
     */
    private void showTotalFoodConsumption() {
        System.out.println("\n=== ОБЩЕЕ ПОТРЕБЛЕНИЕ ЕДЫ ===");
        int totalFood = zoo.getTotalFood();
        System.out.println(String.format("Все животные потребляют %d кг еды в день", totalFood));
        waitForEnter();
    }
    
    /**
     * Показывает полный отчет.
     */
    private void showFullReport() {
        System.out.println("\n=== ПОЛНЫЙ ОТЧЕТ ===");
        System.out.println(zoo.getFullReport());
        waitForEnter();
    }
    
    /**
     * Показывает статистику зоопарка.
     */
    private void showZooStatistics() {
        System.out.println("\n=== СТАТИСТИКА ЗООПАРКА ===");
        System.out.println(String.format("Количество животных: %d", zoo.getAnimalCount()));
        System.out.println(String.format("Количество вещей: %d", zoo.getThingCount()));
        System.out.println(String.format("Общее потребление еды: %d кг/день", zoo.getTotalFood()));
        System.out.println(String.format("Животных для контактного зоопарка: %d", zoo.getContactZooAnimals().length));
        waitForEnter();
    }
    
    /**
     * Получает строковый ввод от пользователя.
     */
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * Получает целочисленный ввод от пользователя.
     */
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число!");
            }
        }
    }

    private void waitForEnter() {
        System.out.println("\nНажмите Enter для продолжения...");
        scanner.nextLine();
    }
}
