package services;

import animals.Animal;

/**
 * Ветеринарная клиника для проверки здоровья животных.
 */
public class VetClinic implements IVetService {
    
    public VetClinic() {
    }
    public boolean HealthCheck(Animal animal) {
        System.out.println(String.format("Проводится медицинский осмотр %s '%s'...", 
                                        animal.getAnimalType(), animal.getName()));

        boolean isHealthy = simulateHealthCheck(animal);
        
        if (isHealthy) {
            animal.setHealthy(true);
            System.out.println(String.format("✓ %s '%s' прошел медосмотр и может быть принят в зоопарк", 
                                            animal.getAnimalType(), animal.getName()));
        } else {
            System.out.println(String.format("✗ %s '%s' не прошел медосмотр и не может быть принят в зоопарк", 
                                            animal.getAnimalType(), animal.getName()));
        }
        
        return isHealthy;
    }

    private boolean simulateHealthCheck(Animal animal) {
        return Math.random() > 0.2;
    }
    public String getExaminationReport(Animal[] examinedAnimals) {
        if (examinedAnimals == null || examinedAnimals.length == 0) {
            return "Осмотры не проводились";
        }

        int healthyCount = 0;
        int totalCount = examinedAnimals.length;

        for (Animal animal : examinedAnimals) {
            if (animal.isHealthy()) {
                healthyCount++;
            }
        }

        return String.format("Отчет ветеринарной клиники:\n" +
                           "Всего осмотрено животных: %d\n" +
                           "Прошли медосмотр: %d\n" +
                           "Не прошли медосмотр: %d\n" +
                           "Процент прохождения: %.1f%%",
                           totalCount, healthyCount,
                           totalCount - healthyCount,
                           (double) healthyCount / totalCount * 100);
    }
}
