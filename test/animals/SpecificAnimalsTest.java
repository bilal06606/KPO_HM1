package animals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для конкретных классов животных.
 * Покрывает создание и базовую функциональность каждого типа.
 */
class SpecificAnimalsTest {
    
    @Test
    void testMonkeyCreation() {
        Monkey monkey = new Monkey("Чичи", 3, 1001, 7);
        
        assertEquals("Чичи", monkey.getName());
        assertEquals(3, monkey.getFood());
        assertEquals(1001, monkey.getNumber());
        assertEquals(7, monkey.getKindnessLevel());
        assertEquals("Обезьяна", monkey.getAnimalType());
        assertFalse(monkey.isHealthy());
    }
    
    @Test
    void testRabbitCreation() {
        Rabbit rabbit = new Rabbit("Крош", 1, 1002, 8);
        
        assertEquals("Крош", rabbit.getName());
        assertEquals(1, rabbit.getFood());
        assertEquals(1002, rabbit.getNumber());
        assertEquals(8, rabbit.getKindnessLevel());
        assertEquals("Кролик", rabbit.getAnimalType());
        assertFalse(rabbit.isHealthy());
    }
    
    @Test
    void testTigerCreation() {
        Tiger tiger = new Tiger("Рыжик", 8, 1003);
        
        assertEquals("Рыжик", tiger.getName());
        assertEquals(8, tiger.getFood());
        assertEquals(1003, tiger.getNumber());
        assertEquals("Тигр", tiger.getAnimalType());
        assertFalse(tiger.isHealthy());
    }
    
    @Test
    void testWolfCreation() {
        Wolf wolf = new Wolf("Борз", 6, 1004);
        
        assertEquals("Борз", wolf.getName());
        assertEquals(6, wolf.getFood());
        assertEquals(1004, wolf.getNumber());
        assertEquals("Волк", wolf.getAnimalType());
        assertFalse(wolf.isHealthy());
    }
    
    @Test
    void testMonkeyCanBeInContactZoo() {
        Monkey monkey = new Monkey("Контактная обезьяна", 2, 1005, 8);
        monkey.setHealthy(true);
        
        assertTrue(monkey.canContactZoo(), "Обезьяна с добротой 8 должна быть в контактном зоопарке");
    }
    
    @Test
    void testRabbitCanBeInContactZoo() {
        Rabbit rabbit = new Rabbit("Контактный кролик", 1, 1006, 6);
        rabbit.setHealthy(true);
        
        assertTrue(rabbit.canContactZoo(), "Кролик с добротой 6 должен быть в контактном зоопарке");
    }
}
