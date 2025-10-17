package animals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса Predator.
 * Покрывает базовую функциональность хищников.
 */
class PredatorTest {
    
    private Predator testPredator;
    
    @BeforeEach
    void setUp() {
        testPredator = new Tiger("Тестовый тигр", 8, 1001);
    }
    
    @Test
    void testToString() {
        String result = testPredator.toString();
        assertTrue(result.contains("Тигр"), "строка должна содержать тип животного");
        assertTrue(result.contains("Тестовый тигр"), "строка должна содержать имя");
        assertTrue(result.contains("1001"), "строка должна содержать номер");
        assertTrue(result.contains("еда: 8 кг/день"), "строка должна содержать количество еды");
    }
}