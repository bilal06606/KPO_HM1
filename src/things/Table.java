package things;

public class Table extends Thing {
    
    public Table(String name, int number) {
        super(name, number);
    }
    
    @Override
    public String getThingType() {
        return "Стол";
    }
}
