package aquarium.repositories;

import aquarium.entities.decorations.Decoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DecorationRepository implements Repository {

    private Collection<Decoration> decorations = new ArrayList<>();

    @Override
    public void add(Decoration decoration) {
        decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        return decorations.remove(decoration);
    }

    @Override
    public Decoration findByType(String type) {
        return decorations.stream()
                .filter(decoration -> decoration.getClass().getSimpleName().equals(type))
                .findFirst().orElse(null);
    }

    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(decorations);
    }
}
