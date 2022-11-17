package goldDigger.repositories;

import goldDigger.models.discoverer.Discoverer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DiscovererRepository<T> implements Repository<Discoverer> {

    private Collection<Discoverer> discoverers = new ArrayList<>();

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(discoverers);
    }

    @Override
    public void add(Discoverer entity) {
        discoverers.add(entity);
    }

    @Override
    public boolean remove(Discoverer entity) {

        Discoverer discoverer1 = discoverers.stream()
                .filter(discoverer -> discoverer.getName().equals(entity.getName()))
                .findFirst().orElse(null);
        if (discoverer1 != null) {
            discoverers.remove(discoverer1);
            return true;
        }
        return false;
    }

    public Collection<Discoverer> getDiscoverers() {
        return this.discoverers;
    }


    @Override
    public Discoverer byName(String name) {
        return discoverers.stream()
                .filter(discoverer -> discoverer.getName().equals(name))
                .findFirst().orElse(null);
    }
}
