package animals;

import interfaces.IAlive;
import interfaces.IInventory;

/**
 * Базовый класс для всех животных в зоопарке.
 * Реализует интерфейсы IAlive и IInventory.
 * Применяет принцип Single Responsibility.
 */
public abstract class Animal implements IAlive, IInventory {
    private String name;
    private int food;
    private int number;
    private boolean isHealthy;
    
    public Animal(String name, int food, int number) {
        this.name = name;
        this.food = food;
        this.number = number;
        this.isHealthy = false;
    }
    
    @Override
    public int getFood() {
        return food;
    }
    
    @Override
    public void setFood(int food) {
        this.food = food;
    }
    
    @Override
    public int getNumber() {
        return number;
    }
    
    @Override
    public void setNumber(int number) {
        this.number = number;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isHealthy() {
        return isHealthy;
    }
    
    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public abstract String getAnimalType();
    
    @Override
    public String toString() {
        return String.format("%s '%s' (№%d, еда: %d кг/день, здоровье: %s)", 
                           getAnimalType(), name, number, food, 
                           isHealthy ? "здоров" : "не проверен");
    }
}
