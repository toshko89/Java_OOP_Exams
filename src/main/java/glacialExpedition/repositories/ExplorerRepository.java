package glacialExpedition.repositories;


import glacialExpedition.models.explorers.Explorer;

import java.util.*;

public class ExplorerRepository implements Repository<Explorer> {

    private Map<String, Explorer> explorers = new LinkedHashMap<>();

    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableCollection(this.explorers.values());
    }

    @Override
    public void add(Explorer model) {
        this.explorers.putIfAbsent(model.getName(), model);
    }

    @Override
    public boolean remove(Explorer model) {
        return this.explorers.remove(model.getName(), model);
    }

    @Override
    public Explorer byName(String name) {
        return this.explorers.get(name);
    }
}
