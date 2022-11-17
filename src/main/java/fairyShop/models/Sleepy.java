package fairyShop.models;

public class Sleepy extends BaseHelper {
    private static final int ENERGY_BASE = 50;
    private int energy;

    public Sleepy(String name) {
        super(name, ENERGY_BASE);
    }

    @Override
    public void work() {
        if (this.energy - 15 < 0) {
            this.energy = 0;
        } else {
            this.energy -= 15;
        }
    }
}
