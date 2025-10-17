package animals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса Herbo.
 * Покрывает логику контактного зоопарка и уровень доброты.
 */
class HerboTest {
    
    private Herbo testHerbo;
    
    @BeforeEach
    void setUp() {
        testHerbo = new Monkey("Тестовая обезьяна", 3, 1001, 7);
    }
    
    @Test
    void testGetSetKindnessLevel() {
        assertEquals(7, testHerbo.getKindnessLevel(), "Начальный уровень доброты должен быть 7");
        
        testHerbo.setKindnessLevel(9);
        assertEquals(9, testHerbo.getKindnessLevel(), "Уровень доброты должен измениться на 9");
    }
    
    @Test
    void testSetKindnessLevelWithBound() {
        testHerbo.setKindnessLevel(0);
        assertEquals(1, testHerbo.getKindnessLevel(), "Уровень доброты должен быть ограничен минимумом 1");

        testHerbo.setKindnessLevel(15);
        assertEquals(10, testHerbo.getKindnessLevel(), "Уровень доброты должен быть ограничен максимумом 10");
    }
    
    @Test
    void testContactZoo_KindnessOver5() {
        testHerbo.setKindnessLevel(6);
        testHerbo.setHealthy(true);
        
        assertTrue(testHerbo.canContactZoo(),
                  "Травоядное с добротой > 5 и здоровое должно быть в контактном зоопарке");
    }
    
    @Test
    void testContactZoo_KindnessExactly5() {
        testHerbo.setKindnessLevel(5);
        testHerbo.setHealthy(true);
        
        assertFalse(testHerbo.canContactZoo(),
                   "Травоядное с добротой = 5 не должно быть в контактном зоопарке");
    }
    
    @Test
    void testContactZoo_KindnessUnder5() {
        testHerbo.setKindnessLevel(4);
        testHerbo.setHealthy(true);
        
        assertFalse(testHerbo.canContactZoo(),
                   "Травоядное с добротой < 5 не должно быть в контактном зоопарке");
    }
    
    @Test
    void testContactZoo_Unhealthy() {
        testHerbo.setKindnessLevel(8);
        testHerbo.setHealthy(false);
        
        assertFalse(testHerbo.canContactZoo(),
                   "Нездоровое травоядное не должно быть в контактном зоопарке");
    }
    
    @Test
    void testToString() {
        String result = testHerbo.toString();
        assertTrue(result.contains("Обезьяна"), "toString должен содержать тип животного");
        assertTrue(result.contains("Тестовая обезьяна"), "toString должен содержать имя");
        assertTrue(result.contains("1001"), "toString должен содержать номер");
        assertTrue(result.contains("доброта: 7/10"), "toString должен содержать уровень доброты");
    }
    
    @Test
    void testConstructorWithBound() {
        Herbo lowKindness = new Monkey("Низкая доброта", 2, 1002, 0);
        assertEquals(1, lowKindness.getKindnessLevel(), "Конструктор должен ограничить доброту минимумом 1");
        
        Herbo highKindness = new Monkey("Высокая доброта", 2, 1003, 15);
        assertEquals(10, highKindness.getKindnessLevel(), "Конструктор должен ограничить доброту максимумом 10");
    }
}
