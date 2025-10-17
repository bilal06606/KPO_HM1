package animals;
public class Monkey extends Herbo {
    
    public Monkey(String name, int food, int number, int kindnessLevel) {
        super(name, food, number, kindnessLevel);
    }
    
    @Override
    public String getAnimalType() {
        return "Обезьяна";
    }
}
