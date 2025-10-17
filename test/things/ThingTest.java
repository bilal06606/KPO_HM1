package things;

import interfaces.IInventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для базового класса Thing.
 * Покрывает основную функциональность вещей.
 */
class ThingTest {
    
    private Thing testThing;
    
    @BeforeEach
    void setUp() {
        testThing = new Table("Тестовая вещь", 2001);
    }
    
    @Test
    void testThingImplementsIInventory() {
        assertTrue(testThing instanceof IInventory, "Thing должен реализовывать интерфейс IInventory");
    }
    
    @Test
    void testGetSetNumber() {
        assertEquals(2001, testThing.getNumber(), "Начальный номер должен быть 2001");
        
        testThing.setNumber(3001);
        assertEquals(3001, testThing.getNumber(), "Номер должен измениться на 3001");
    }
    
    @Test
    void testGetSetName() {
        assertEquals("Тестовая вещь", testThing.getName(), "Начальное название должно быть 'Тестовая вещь'");
        
        testThing.setName("Новое название");
        assertEquals("Новое название", testThing.getName(), "Название должно измениться на 'Новое название'");
    }
    
    @Test
    void testGetThingType() {
        assertEquals("Стол", testThing.getThingType(), "Тип вещи должен быть 'Стол'");
    }
    
    @Test
    void testToString() {
        String result = testThing.toString();
        assertTrue(result.contains("Стол"), "строка должна должен содержать тип вещи");
        assertTrue(result.contains("Тестовая вещь"), "строка должна должен содержать название");
        assertTrue(result.contains("2001"), "строка должна должен содержать номер");
    }
}
