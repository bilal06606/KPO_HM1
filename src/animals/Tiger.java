package animals;

public class Tiger extends Predator {
    
    public Tiger(String name, int food, int number) {
        super(name, food, number);
    }
    
    @Override
    public String getAnimalType() {
        return "Тигр";
    }
}
