package RetakeExam22August2022.goldDigger.goldDigger.models.discoverer;

import goldDigger.models.museum.Museum;

public interface Discoverer {
    String getName();

    double getEnergy();

    boolean canDig();

    Museum getMuseum();

    void dig();
}
