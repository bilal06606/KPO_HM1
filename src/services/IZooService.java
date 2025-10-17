package services;

import animals.Animal;
import things.Thing;

public interface IZooService {

    boolean addAnimal(Animal animal);
    boolean addThing(Thing thing);
    int getAnimalCount();
    int getThingCount();
    int getTotalFood();
    Animal[] getContactZooAnimals();
    String getAnimalsReport();
    String getThingsReport();
    String getFullReport();
}
