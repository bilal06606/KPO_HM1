import di.DIContainer;
import di.DIConfiguration;
import services.IZooService;
import services.IVetService;
import console.ZooConsoleApp;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== СИСТЕМА УПРАВЛЕНИЯ МОСКОВСКИМ ЗООПАРКОМ ===");
        DIContainer container = DIConfiguration.createConfiguredContainer();
        IZooService zoo = container.get(IZooService.class);
        IVetService veterinaryService = container.get(IVetService.class);
        ZooConsoleApp consoleApp = new ZooConsoleApp(zoo, veterinaryService);
        consoleApp.run();
    }
}
