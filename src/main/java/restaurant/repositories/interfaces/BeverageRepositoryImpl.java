package restaurant.repositories.interfaces;

import restaurant.entities.drinks.interfaces.Beverages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {

    private Collection<Beverages> entities = new ArrayList<>();

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return entities.stream()
                .filter(beverages -> beverages.getName().equals(drinkName)
                        && beverages.getBrand().equals(drinkBrand))
                .findFirst().orElse(null);
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return Collections.unmodifiableCollection(entities);
    }

    @Override
    public void add(Beverages entity) {
        entities.add(entity);
    }
}
