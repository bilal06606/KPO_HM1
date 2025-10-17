package animals;


public abstract class Herbo extends Animal {
    private int kindnessLevel;
    
    public Herbo(String name, int food, int number, int kindnessLevel) {
        super(name, food, number);
        this.kindnessLevel = Math.max(1, Math.min(10, kindnessLevel));
    }
    
    public int getKindnessLevel() {
        return kindnessLevel;
    }
    
    public void setKindnessLevel(int kindnessLevel) {
        this.kindnessLevel = Math.max(1, Math.min(10, kindnessLevel));
    }

    public boolean canContactZoo() {
        return kindnessLevel > 5 && isHealthy();
    }
    
    @Override
    public String toString() {
        return String.format("%s '%s' (№%d, еда: %d кг/день, доброта: %d/10, здоровье: %s)", 
                           getAnimalType(), getName(), getNumber(), getFood(), kindnessLevel,
                           isHealthy() ? "здоров" : "не проверен");
    }
}
