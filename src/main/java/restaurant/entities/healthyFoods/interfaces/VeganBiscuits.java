package restaurant.entities.healthyFoods.interfaces;

public class VeganBiscuits extends Food {

    private static final double INITIALVEGANBISCUITSPORTION = 205;

    public VeganBiscuits(String name, double price) {
        super(name, INITIALVEGANBISCUITSPORTION, price);
    }
}
