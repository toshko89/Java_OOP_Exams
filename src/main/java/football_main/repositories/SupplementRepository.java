package football_main.repositories;

import football_main.entities.supplement.Supplement;

public interface SupplementRepository {
    void add(Supplement supplement);

    boolean remove(Supplement supplement);

    Supplement findByType(String type);
}
