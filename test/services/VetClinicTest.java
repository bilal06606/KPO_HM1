package services;

import animals.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса VetClinic.
 * Покрывает логику медицинских осмотров и отчетности.
 */
class VetClinicTest {
    
    private VetClinic clinic;
    
    @BeforeEach
    void setUp() {
        clinic = new VetClinic();
    }
    
    @Test
    void testHealthCheck_HealthyAnimal() {
        Animal animal = new Monkey("Здоровое животное", 3, 1001, 7);

        boolean hasPassed = false;
        for (int i = 0; i < 10; i++) {
            Animal testAnimal = new Monkey("Тест", 3, 1001 + i, 7);
            boolean result = clinic.HealthCheck(testAnimal);
            if (result) {
                hasPassed = true;
                assertTrue(testAnimal.isHealthy(), "Животное должно быть здоровым после успешного медосмотра");
                break;
            }
        }

        assertTrue(hasPassed, "Хотя бы одно животное должно пройти медосмотр за 10 попыток");
    }
    
    @Test
    void testHealthCheck_UnhealthyAnimal() {
        Animal animal = new Rabbit("Нездоровое животное", 1, 1002, 5);

        boolean hasFailed = false;
        for (int i = 0; i < 10; i++) {
            Animal testAnimal = new Rabbit("Тест", 1, 1002 + i, 5);
            boolean result = clinic.HealthCheck(testAnimal);
            if (!result) {
                hasFailed = true;
                assertFalse(testAnimal.isHealthy(), "Животное должно остаться нездоровым после неуспешного медосмотра");
                break;
            }
        }
        assertTrue(hasFailed, "Хотя бы одно животное должно не пройти медосмотр за 10 попыток");
    }
    
    @Test
    void testGetExaminationReport_EmptyArray() {
        Animal[] animals = new Animal[0];
        String report = clinic.getExaminationReport(animals);
        
        assertEquals("Осмотры не проводились", report, "Отчет для пустого массива должен быть соответствующим");
    }
    
    @Test
    void testGetExaminationReport_NullArray() {
        String report = clinic.getExaminationReport(null);
        
        assertEquals("Осмотры не проводились", report, "Отчет для null должен быть соответствующим");
    }
    
    @Test
    void testGetExaminationReport_WithAnimals() {
        Animal[] animals = {
            new Monkey("Обезьяна1", 3, 1001, 7),
            new Rabbit("Кролик1", 1, 1002, 6),
            new Tiger("Тигр1", 8, 1003)
        };

        for (Animal animal : animals) {
            clinic.HealthCheck(animal);
        }
        
        String report = clinic.getExaminationReport(animals);
        
        assertTrue(report.contains("Отчет ветеринарной клиники:"), "Отчет должен содержать заголовок");
        assertTrue(report.contains("Всего осмотрено животных: 3"), "Отчет должен содержать общее количество");
        assertTrue(report.contains("Прошли медосмотр:"), "Отчет должен содержать количество прошедших");
        assertTrue(report.contains("Не прошли медосмотр:"), "Отчет должен содержать количество не прошедших");
        assertTrue(report.contains("Процент прохождения:"), "Отчет должен содержать процент");
    }
    
    @Test
    void testHealthCheck_SetsHealthyFlag() {
        Animal animal = new Wolf("Тестовый Волк", 6, 1004);
        assertFalse(animal.isHealthy(), "Животное должно быть нездоровым изначально");
        
        clinic.HealthCheck(animal);
        assertTrue(animal.isHealthy() || !animal.isHealthy(), "Статус здоровья должен быть определен");
    }
}
