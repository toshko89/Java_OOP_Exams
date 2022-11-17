package christmasRaces.repositories.interfaces;

import christmasRaces.entities.cars.Car;

import java.util.ArrayList;
import java.util.Collection;

public class CarRepository implements Repository<Car> {

    private Collection<Car> models = new ArrayList<>();

    @Override
    public Car getByName(String name) {
        return models.stream()
                .filter(car -> car.getModel().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Car> getAll() {
        return models;
    }

    @Override
    public void add(Car model) {
        models.add(model);
    }

    @Override
    public boolean remove(Car model) {
        Car car1 = models.stream()
                .filter(car -> car.equals(model)).findFirst().orElse(null);
        if (car1 != null) {
            models.remove(car1);
            return true;
        }
        return false;
    }
}
