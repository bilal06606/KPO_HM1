package di;

import services.IVetService;
import services.IZooService;
import services.VetClinic;
import services.Zoo;

public class DIConfiguration {

    public static void configure(DIContainer container) {
        container.registerSingleton(IVetService.class, new VetClinic());

        container.register(IZooService.class, () -> {
            IVetService veterinaryService = container.get(IVetService.class);
            return new Zoo("Московский зоопарк", veterinaryService);
        });
    }

    public static DIContainer createConfiguredContainer() {
        DIContainer container = new DIContainer();
        configure(container);
        return container;
    }
}
