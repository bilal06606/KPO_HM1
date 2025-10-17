package animals;
/**
 * Класс для хищных животных.
 * Наследует от Animal.
 */
public abstract class Predator extends Animal {
    
    public Predator(String name, int food, int number) {
        super(name, food, number);
    }
    
    @Override
    public String toString() {
        return String.format("%s '%s' (№%d, еда: %d кг/день, здоровье: %s)", 
                           getAnimalType(), getName(), getNumber(), getFood(),
                           isHealthy() ? "здоров" : "не проверен");
    }
}
