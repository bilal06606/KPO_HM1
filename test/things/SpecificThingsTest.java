package things;

import interfaces.IInventory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для конкретных классов вещей.
 * Покрывает создание и базовую функциональность каждого типа.
 */
class SpecificThingsTest {
    
    @Test
    void testTableCreation() {
        Table table = new Table("Рабочий стол", 2001);
        
        assertEquals("Рабочий стол", table.getName());
        assertEquals(2001, table.getNumber());
        assertEquals("Стол", table.getThingType());
    }
    
    @Test
    void testComputerCreation() {
        Computer computer = new Computer("Рабочий компьютер", 2002);
        
        assertEquals("Рабочий компьютер", computer.getName());
        assertEquals(2002, computer.getNumber());
        assertEquals("Компьютер", computer.getThingType());
    }
    
    @Test
    void testTableToString() {
        Table table = new Table("Тестовый стол", 2003);
        String result = table.toString();
        
        assertTrue(result.contains("Стол"), "строка должна содержать тип вещи");
        assertTrue(result.contains("Тестовый стол"), "строка должна содержать название");
        assertTrue(result.contains("2003"), "строка должна содержать номер");
    }
    
    @Test
    void testComputerToString() {
        Computer computer = new Computer("Тестовый компьютер", 2004);
        String result = computer.toString();
        
        assertTrue(result.contains("Компьютер"), "строка должна содержать тип вещи");
        assertTrue(result.contains("Тестовый компьютер"), "строка должна содержать название");
        assertTrue(result.contains("2004"), "строка должна содержать номер");
    }
    
    @Test
    void testTableInheritance() {
        Table table = new Table("Наследование", 2005);
        
        assertTrue(table instanceof Thing, "Table должен наследоваться от Thing");
        assertTrue(table instanceof IInventory, "Table должен реализовывать IInventory");
    }
    
    @Test
    void testComputerInheritance() {
        Computer computer = new Computer("Наследование", 2006);
        
        assertTrue(computer instanceof Thing, "Computer должен наследоваться от Thing");
        assertTrue(computer instanceof IInventory, "Computer должен реализовывать IInventory");
    }
}
