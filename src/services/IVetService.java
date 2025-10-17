package services;

import animals.Animal;

public interface IVetService {

    boolean HealthCheck(Animal animal);

    String getExaminationReport(Animal[] examinedAnimals);
}
