package fairyShop.repositories;

import fairyShop.models.Helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HelperRepository<T> implements Repository<Helper> {

    private List<Helper> helpers = new ArrayList<>();

    @Override
    public Collection<Helper> getModels() {
        return Collections.unmodifiableList(this.helpers);
    }

    @Override
    public void add(Helper model) {
        helpers.add(model);
    }

    @Override
    public boolean remove(Helper model) {
        Helper helper1 = helpers.stream().filter(helper -> helper.equals(model))
                .findFirst().orElse(null);
        if (helper1 != null) {
            this.helpers.remove(helper1);
            return true;
        }
        return false;
    }

    @Override
    public Helper findByName(String name) {
        return helpers.stream()
                .filter(helper -> helper.getName().equals(name)).findFirst().orElse(null);
    }
}
