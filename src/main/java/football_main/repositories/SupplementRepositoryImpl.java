package football_main.repositories;

import football_main.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public class SupplementRepositoryImpl implements SupplementRepository {

    private Collection<Supplement> supplements = new ArrayList<>();

    @Override
    public void add(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public boolean remove(Supplement supplement) {
        Supplement supplement1 = this.supplements.stream()
                .filter(sup -> sup.equals(supplement))
                .findFirst().orElse(null);
        if (supplement1 != null) {
            this.supplements.remove(supplement1);
            return true;
        }
        return false;
    }

    @Override
    public Supplement findByType(String type) {
        return this.supplements.stream()
                .filter(supplement -> supplement.getClass().getSimpleName().equals(type))
                .findFirst().orElse(null);
    }
}
