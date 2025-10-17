package animals;
public class Wolf extends Predator {
    
    public Wolf(String name, int food, int number) {
        super(name, food, number);
    }
    
    @Override
    public String getAnimalType() {
        return "Волк";
    }
}
