package services;

import animals.Animal;
import animals.Herbo;
import things.Thing;

import java.util.ArrayList;
import java.util.List;

public class Zoo implements IZooService {
    private final IVetService veterinaryService;
    private final List<Animal> animals;
    private final List<Thing> things;
    private final String zooName;
    
    public Zoo(String zooName, IVetService veterinaryService) {
        this.zooName = zooName;
        this.veterinaryService = veterinaryService;
        this.animals = new ArrayList<>();
        this.things = new ArrayList<>();
    }
    
    @Override
    public boolean addAnimal(Animal animal) {
        if (animal == null) {
            System.out.println("Ошибка: животное отсутствует");
            return false;
        }
        
        for (Animal existingAnimal : animals) {
            if (existingAnimal.getNumber() == animal.getNumber()) {
                System.out.println(String.format("Ошибка: животное с номером %d уже существует в зоопарке", 
                                                animal.getNumber()));
                return false;
            }
        }
        
        System.out.println(String.format("Попытка добавить %s '%s' в зоопарк '%s'", 
                                        animal.getAnimalType(), animal.getName(), zooName));
        
        boolean isHealthy = veterinaryService.HealthCheck(animal);
        
        if (isHealthy) {
            animals.add(animal);
            System.out.println(String.format("✓ %s '%s' успешно добавлен в зоопарк", 
                                            animal.getAnimalType(), animal.getName()));
            return true;
        } else {
            System.out.println(String.format("✗ %s '%s' не может быть принят в зоопарк",
                                            animal.getAnimalType(), animal.getName()));
            return false;
        }
    }
    
    @Override
    public boolean addThing(Thing thing) {
        if (thing == null) {
            System.out.println("Ошибка: вещь отсутствует");
            return false;
        }
        
        for (Thing existingThing : things) {
            if (existingThing.getNumber() == thing.getNumber()) {
                System.out.println(String.format("Ошибка: вещь с номером %d уже существует в инвентаре", 
                                                thing.getNumber()));
                return false;
            }
        }
        
        things.add(thing);
        System.out.println(String.format("✓ %s '%s' успешно добавлен в инвентарь зоопарка", 
                                        thing.getThingType(), thing.getName()));
        return true;
    }
    
    @Override
    public int getAnimalCount() {
        return animals.size();
    }
    
    @Override
    public int getThingCount() {
        return things.size();
    }
    
    @Override
    public int getTotalFood() {
        return animals.stream()
                     .mapToInt(Animal::getFood)
                     .sum();
    }
    
    @Override
    public Animal[] getContactZooAnimals() {
        return animals.stream()
                     .filter(animal -> animal instanceof Herbo)
                     .filter(animal -> ((Herbo) animal).canContactZoo())
                     .toArray(Animal[]::new);
    }
    
    @Override
    public String getAnimalsReport() {
        if (animals.isEmpty()) {
            return "В зоопарке нет животных";
        }
        
        StringBuilder report = new StringBuilder();
        report.append(String.format("Отчет о животных зоопарка '%s':\n", zooName));
        report.append(String.format("Всего животных: %d\n", animals.size()));
        report.append(String.format("Общее потребление еды в день: %d кг\n\n", getTotalFood()));
        
        report.append("Список животных:\n");
        for (Animal animal : animals) {
            report.append(String.format("- %s\n", animal.toString()));
        }
        
        Animal[] contactAnimals = getContactZooAnimals();
        if (contactAnimals.length > 0) {
            report.append(String.format("\nЖивотные для контактного зоопарка (%d):\n", contactAnimals.length));
            for (Animal animal : contactAnimals) {
                report.append(String.format("- %s\n", animal.toString()));
            }
        }
        
        return report.toString();
    }
    
    @Override
    public String getThingsReport() {
        if (things.isEmpty()) {
            return "В инвентаре зоопарка нет вещей";
        }
        
        StringBuilder report = new StringBuilder();
        report.append(String.format("Отчет о вещах зоопарка '%s':\n", zooName));
        report.append(String.format("Всего вещей: %d\n\n", things.size()));
        
        report.append("Список вещей:\n");
        for (Thing thing : things) {
            report.append(String.format("- %s\n", thing.toString()));
        }
        
        return report.toString();
    }
    
    @Override
    public String getFullReport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("=== ПОЛНЫЙ ОТЧЕТ ЗООПАРКА '%s' ===\n\n", zooName));
        report.append(getAnimalsReport()).append("\n");
        report.append(getThingsReport()).append("\n");

        Animal[] allAnimals = animals.toArray(new Animal[0]);
        if (allAnimals.length > 0) {
            report.append(veterinaryService.getExaminationReport(allAnimals));
        }
        
        return report.toString();
    }
    
    public String getZooName() {
        return zooName;
    }
    
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }
    public List<Thing> getThings() {
        return new ArrayList<>(things);
    }
}
