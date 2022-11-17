package fairyShop.models;

import static fairyShop.common.ExceptionMessages.PRESENT_ENERGY_LESS_THAN_ZERO;
import static fairyShop.common.ExceptionMessages.PRESENT_NAME_NULL_OR_EMPTY;

public class PresentImpl implements Present {

    private String name;
    private int energyRequired = 0;

    public PresentImpl(String name, int energyRequired) {
        setName(name);
        setEnergyRequired(energyRequired);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(PRESENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setEnergyRequired(int energyRequired) {
        if (energyRequired < 0) {
            throw new IllegalArgumentException(PRESENT_ENERGY_LESS_THAN_ZERO);
        }
        this.energyRequired = energyRequired;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getEnergyRequired() {
        return energyRequired;
    }

    @Override
    public boolean isDone() {
        return this.energyRequired == 0;
    }

    @Override
    public void getCrafted() {
        if (this.energyRequired - 10 < 0) {
            this.energyRequired = 0;
        } else {
            this.energyRequired -= 10;
        }
    }
}
