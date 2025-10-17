package things;

import interfaces.IInventory;

public abstract class Thing implements IInventory {
    private String name;
    private int number;
    
    public Thing(String name, int number) {
        this.name = name;
        this.number = number;
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
    

    public abstract String getThingType();
    
    @Override
    public String toString() {
        return String.format("%s '%s' (№%d)", 
                           getThingType(), name, number);
    }
}