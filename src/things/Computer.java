package things;

public class Computer extends Thing {
    
    public Computer(String name, int number) {
        super(name, number);
    }
    
    @Override
    public String getThingType() {
        return "Компьютер";
    }
}
