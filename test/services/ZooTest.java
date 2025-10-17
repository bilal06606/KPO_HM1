package services;

import animals.*;
import things.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса Zoo.
 * Покрывает логику управления зоопарком.
 */
class ZooTest {
    
    private Zoo zoo;
    private VetClinic clinic;
    
    @BeforeEach
    void setUp() {
        clinic = new VetClinic();
        zoo = new Zoo("Тестовый зоопарк", clinic);
    }
    
    @Test
    void testAddAnimal_Success() {
        Animal animal = new Monkey("Тестовая обезьяна", 3, 1001, 7);

        boolean success = false;
        for (int i = 0; i < 10; i++) {
            Animal testAnimal = new Monkey("Тест", 3, 1001 + i, 7);
            if (zoo.addAnimal(testAnimal)) {
                success = true;
                assertEquals(1, zoo.getAnimalCount(), "Количество животных должно увеличиться на 1");
                break;
            }
        }
        
        assertTrue(success, "Хотя бы одно животное должно быть добавлено за 10 попыток");
    }
    
    @Test
    void testAddAnimal_NullAnimal() {
        boolean result = zoo.addAnimal(null);
        
        assertFalse(result, "Добавление null животного должно вернуть false");
        assertEquals(0, zoo.getAnimalCount(), "Количество животных не должно измениться");
    }
    
    @Test
    void testAddAnimal_DuplicateNumber() {
        Animal animal1 = new Monkey("Первая обезьяна", 3, 1001, 7);
        Animal animal2 = new Rabbit("Второй кролик", 1, 1001, 6);

        boolean firstAdded = false;
        for (int i = 0; i < 10; i++) {
            Animal testAnimal = new Monkey("Тест1", 3, 1001 + i, 7);
            if (zoo.addAnimal(testAnimal)) {
                firstAdded = true;
                break;
            }
        }
        
        if (firstAdded) {
            boolean result = zoo.addAnimal(animal2);
            assertFalse(result, "Добавление животного с дублирующимся номером должно вернуть false");
        }
    }
    
    @Test
    void testAddThing_Success() {
        Thing thing = new Table("Тестовый стол", 2001);
        
        boolean result = zoo.addThing(thing);
        
        assertTrue(result, "Добавление вещи должно быть успешным");
        assertEquals(1, zoo.getThingCount(), "Количество вещей должно увеличиться на 1");
    }
    
    @Test
    void testAddThing_NullThing() {
        boolean result = zoo.addThing(null);
        
        assertFalse(result, "Добавление null вещи должно вернуть false");
        assertEquals(0, zoo.getThingCount(), "Количество вещей не должно измениться");
    }
    
    @Test
    void testAddThing_DuplicateNumber() {
        Thing thing1 = new Table("Первый стол", 2001);
        Thing thing2 = new Computer("Второй компьютер", 2001);
        
        boolean result1 = zoo.addThing(thing1);
        assertTrue(result1, "Первая вещь должна быть добавлена");
        
        boolean result2 = zoo.addThing(thing2);
        assertFalse(result2, "Вторая вещь с дублирующимся номером не должна быть добавлена");
        assertEquals(1, zoo.getThingCount(), "Количество вещей должно остаться 1");
    }
    
    @Test
    void testGetTotalFood() {
        Animal animal1 = new Monkey("Обезьяна", 3, 1001, 7);
        Animal animal2 = new Rabbit("Кролик", 1, 1002, 6);
        Animal animal3 = new Tiger("Тигр", 8, 1003);
        zoo.addAnimal(animal1);
        zoo.addAnimal(animal2);
        zoo.addAnimal(animal3);
        
        int totalFood = zoo.getTotalFood();
        assertTrue(totalFood >= 0, "Общее количество еды должно быть неотрицательным");
        assertTrue(totalFood <= 12, "Общее количество еды не должно превышать сумму всех животных");
    }
    
    @Test
    void testGetContactZooAnimals() {
        Monkey monkey = new Monkey("Контактная обезьяна", 3, 1001, 8);
        Rabbit rabbit = new Rabbit("Контактный кролик", 1, 1002, 7);
        Tiger tiger = new Tiger("Агрессивный тигр", 8, 1003);
        
        monkey.setHealthy(true);
        rabbit.setHealthy(true);
        tiger.setHealthy(true);
        zoo.addAnimal(monkey);
        zoo.addAnimal(rabbit);
        zoo.addAnimal(tiger);
        
        Animal[] contactAnimals = zoo.getContactZooAnimals();

        for (Animal animal : contactAnimals) {
            assertTrue(animal instanceof Herbo, "В контактном зоопарке должны быть только травоядные");
            Herbo herbo = (Herbo) animal;
            assertTrue(herbo.getKindnessLevel() > 5, "Доброта должна быть больше 5");
            assertTrue(herbo.isHealthy(), "Животное должно быть здоровым");
        }
    }
    
    @Test
    void testGetAnimalsReport_EmptyZoo() {
        String report = zoo.getAnimalsReport();
        
        assertTrue(report.contains("В зоопарке нет животных"), "Отчет для пустого зоопарка должен быть соответствующим");
    }
    
    @Test
    void testGetThingsReport_EmptyZoo() {
        String report = zoo.getThingsReport();
        
        assertTrue(report.contains("В инвентаре зоопарка нет вещей"), "Отчет для пустого инвентаря должен быть соответствующим");
    }
    
    @Test
    void testGetFullReport() {
        Animal animal = new Monkey("Тестовое животное", 3, 1001, 7);
        Thing thing = new Table("Тестовая вещь", 2001);
        
        zoo.addAnimal(animal);
        zoo.addThing(thing);
        
        String report = zoo.getFullReport();
        
        assertTrue(report.contains("ПОЛНЫЙ ОТЧЕТ ЗООПАРКА"), "Полный отчет должен содержать заголовок");
        assertTrue(report.contains("Тестовый зоопарк"), "Отчет должен содержать название зоопарка");
    }
    
    @Test
    void testGetZooName() {
        assertEquals("Тестовый зоопарк", zoo.getZooName(), "Название зоопарка должно быть корректным");
    }
}
