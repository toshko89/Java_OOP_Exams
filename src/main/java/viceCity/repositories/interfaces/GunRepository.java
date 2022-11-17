package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

public class GunRepository implements Repository<Gun> {
    private Collection<Gun> models = new ArrayList<>();

    @Override
    public Collection<Gun> getModels() {
        return models;
    }

    @Override
    public void add(Gun model) {
        models.add(model);
    }

    @Override
    public boolean remove(Gun model) {
        return models.remove(model);
    }

    @Override
    public Gun find(String name) {
        return models.stream()
                .filter(gun -> gun.getName().equals(name))
                .findFirst().orElse(null);
    }
}
