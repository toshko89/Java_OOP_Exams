package restaurant.repositories.interfaces;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {

    private Collection<HealthyFood> entities = new ArrayList<>();

    @Override
    public HealthyFood foodByName(String name) {
        return entities.stream()
                .filter(healthyFood -> healthyFood.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return Collections.unmodifiableCollection(entities);
    }

    @Override
    public void add(HealthyFood entity) {
        entities.add(entity);
    }
}
