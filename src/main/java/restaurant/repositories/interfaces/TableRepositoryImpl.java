package restaurant.repositories.interfaces;

import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TableRepositoryImpl implements TableRepository<Table> {

    private Collection<Table> entities = new ArrayList<>();

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableCollection(entities);
    }

    @Override
    public void add(Table entity) {
        entities.add(entity);
    }

    @Override
    public Table byNumber(int number) {
        return entities.stream()
                .filter(table -> table.getTableNumber() == number)
                .findFirst().orElse(null);
    }
}
