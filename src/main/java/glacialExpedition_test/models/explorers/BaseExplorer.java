package glacialExpedition_test.models.explorers;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

import static glacialExpedition.common.ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO;
import static glacialExpedition.common.ExceptionMessages.EXPLORER_NAME_NULL_OR_EMPTY;

public abstract class BaseExplorer implements Explorer {
    private String name;
    private double energy;
    private Suitcase suitcase;

    public BaseExplorer(String name, double energy) {
        setName(name);
        this.energy = energy;
        this.suitcase = new Carton();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(EXPLORER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public boolean canSearch() {
        return (energy > 0);
    }

    @Override
    public Suitcase getSuitcase() {
        return suitcase;
    }

    @Override
    public void search() {
        setEnergy(Math.max(energy - 15, 0));
    }

    @Override
    public String toString() {
        String exhibitsString = "Suitcase exhibits: " + (!suitcase.getExhibits()
                .isEmpty() ? String.join(", ", suitcase.getExhibits())
                : "None");
        return String.format("Name: %s%nEnergy: %.0f%n%s", name, energy, exhibitsString);
    }
}
