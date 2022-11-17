package christmasRaces.repositories.interfaces;

import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

public class DriverRepository implements Repository<Driver> {

    private Collection<Driver> models = new ArrayList<>();

    @Override
    public Driver getByName(String name) {
        return models.stream()
                .filter(driver -> driver.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Driver> getAll() {
        return models;
    }

    @Override
    public void add(Driver model) {
        models.add(model);
    }

    @Override
    public boolean remove(Driver model) {
        Driver driver1 = models.stream()
                .filter(driver -> driver.equals(model)).findFirst().orElse(null);
        if (driver1 != null) {
            models.remove(driver1);
            return true;
        }
        return false;
    }
}
