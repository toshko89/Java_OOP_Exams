package fairyShop.repositories;

import fairyShop.models.Present;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PresentRepository<T> implements Repository<Present> {

    private Collection<Present> presents = new ArrayList<>();

    @Override
    public Collection getModels() {
        return Collections.unmodifiableCollection(presents);
    }

    @Override
    public void add(Present model) {
        presents.add(model);

    }

    @Override
    public boolean remove(Present model) {
        Present present1 = presents.stream()
                .filter(present -> present.equals(model)).findFirst().orElse(null);
        if (present1 != null) {
            presents.remove(present1);
            return true;
        }
        return false;
    }

    @Override
    public Present findByName(String name) {
        return presents.stream().filter(present -> present.getName().equals(name)).findFirst().orElse(null);
    }
}
