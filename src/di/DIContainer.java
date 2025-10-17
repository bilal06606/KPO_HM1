package di;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Применяет принцип Dependency Inversion - управляет созданием объектов через интерфейсы.
 * Использует внедрение зависимостей через конструктор.
 */
public class DIContainer {
    private final Map<Class<?>, Supplier<?>> registrations = new HashMap<>();

    public <T> void register(Class<T> type, Supplier<T> supplier) {
        registrations.put(type, supplier);
    }

    public <T> void registerSingleton(Class<T> type, T instance) {
        registrations.put(type, () -> instance);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> type) {
        Supplier<?> supplier = registrations.get(type);
        if (supplier == null) {
            throw new IllegalStateException("Type " + type.getName() + " is not registered");
        }
        return (T) supplier.get();
    }

    public boolean isRegistered(Class<?> type) {
        return registrations.containsKey(type);
    }

    public void clear() {
        registrations.clear();
    }

    public int size() {
        return registrations.size();
    }
}
