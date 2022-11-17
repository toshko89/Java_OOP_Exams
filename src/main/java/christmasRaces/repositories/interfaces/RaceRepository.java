package christmasRaces.repositories.interfaces;

import christmasRaces.entities.races.Race;

import java.util.ArrayList;
import java.util.Collection;

public class RaceRepository implements Repository<Race> {

    private Collection<Race> models = new ArrayList<>();

    @Override
    public Race getByName(String name) {
        return models.stream()
                .filter(race -> race.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return models;
    }

    @Override
    public void add(Race model) {
        models.add(model);
    }

    @Override
    public boolean remove(Race model) {
        Race race1 = models.stream()
                .filter(race -> race.equals(model)).findFirst().orElse(null);
        if (race1 != null) {
            models.remove(race1);
            return true;
        }
        return false;
    }
}
