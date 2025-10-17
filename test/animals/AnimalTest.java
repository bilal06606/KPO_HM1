package animals;

import animals.Animal;
import animals.Monkey;
import interfaces.IAlive;
import interfaces.IInventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для базового класса Animal.
 * Покрывает основную функциональность животных.
 */
class AnimalTest {
    
    private Animal testAnimal;
    
    @BeforeEach
    void setUp() {
        testAnimal = new Monkey("Тестовое животное", 5, 1001, 7);
    }
    
    @Test
    void testAnimalImplementsIAlive() {
        assertTrue(testAnimal instanceof IAlive, "Animal должен реализовывать интерфейс IAlive");
    }
    
    @Test
    void testAnimalImplementsIInventory() {
        assertTrue(testAnimal instanceof IInventory, "Animal должен реализовывать интерфейс IInventory");
    }
    
    @Test
    void testGetSetFood() {
        assertEquals(5, testAnimal.getFood(), "Начальное количество еды должно быть 5");
        
        testAnimal.setFood(10);
        assertEquals(10, testAnimal.getFood(), "Количество еды должно измениться на 10");
    }
    
    @Test
    void testGetSetNumber() {
        assertEquals(1001, testAnimal.getNumber(), "Начальный номер должен быть 1001");
        
        testAnimal.setNumber(2001);
        assertEquals(2001, testAnimal.getNumber(), "Номер должен измениться на 2001");
    }
    
    @Test
    void testGetSetName() {
        assertEquals("Тестовое животное", testAnimal.getName(), "Начальное имя должно быть 'Тестовое животное'");
        
        testAnimal.setName("Новое имя");
        assertEquals("Новое имя", testAnimal.getName(), "Имя должно измениться на 'Новое имя'");
    }
    
    @Test
    void testIsHealthyInitiallyFalse() {
        assertFalse(testAnimal.isHealthy(), "По умолчанию животное должно быть нездоровым");
    }
    
    @Test
    void testSetHealthy() {
        testAnimal.setHealthy(true);
        assertTrue(testAnimal.isHealthy(), "Животное должно стать здоровым");
        
        testAnimal.setHealthy(false);
        assertFalse(testAnimal.isHealthy(), "Животное должно стать нездоровым");
    }
    
    @Test
    void testGetAnimalType() {
        assertEquals("Обезьяна", testAnimal.getAnimalType(), "Тип животного должен быть 'Обезьяна'");
    }
    
    @Test
    void testToString() {
        String result = testAnimal.toString();
        assertTrue(result.contains("Обезьяна"), "toString должен содержать тип животного");
        assertTrue(result.contains("Тестовое животное"), "toString должен содержать имя");
        assertTrue(result.contains("1001"), "toString должен содержать номер");
        assertTrue(result.contains("не проверен"), "toString должен содержать статус здоровья");
    }
}
