package animals;
public class Rabbit extends Herbo {
    
    public Rabbit(String name, int food, int number, int kindnessLevel) {
        super(name, food, number, kindnessLevel);
    }
    
    @Override
    public String getAnimalType() {
        return "Кролик";
    }
}
