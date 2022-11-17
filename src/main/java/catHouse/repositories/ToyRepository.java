package catHouse.repositories;

import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;

public class ToyRepository implements Repository {

    private Collection<Toy> toys = new ArrayList<>();

    @Override
    public void buyToy(Toy toy) {
        toys.add(toy);
    }

    public Collection<Toy> getToys() {
        return toys;
    }

    @Override
    public boolean removeToy(Toy toy) {
        return toys.remove(toy);
    }

    @Override
    public Toy findFirst(String type) {
        return toys.stream().filter(toy -> toy.getClass().getSimpleName().equals(type))
                .findFirst().orElse(null);
    }
}
