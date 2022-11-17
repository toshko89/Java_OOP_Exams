package goldDigger.repositories;

import goldDigger.models.spot.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SpotRepository<T> implements Repository<Spot> {

    private Collection<Spot> spots = new ArrayList<>();

    @Override
    public Collection getCollection() {
        return Collections.unmodifiableCollection(spots);
    }

    @Override
    public void add(Spot entity) {
        spots.add(entity);
    }

    @Override
    public boolean remove(Spot entity) {
        Spot spot1 = spots.stream()
                .filter(spot -> spot.equals(entity)).findFirst().orElse(null);
        if (spot1 != null) {
            spots.remove(spot1);
            return true;
        }
        return false;
    }


    @Override
    public Spot byName(String name) {
        return spots.stream()
                .filter(spot -> spot.getName().equals(name))
                .findFirst().orElse(null);
    }
}
