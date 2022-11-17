package restaurant.entities.healthyFoods.interfaces;

public class Salad extends Food {

    private static final double INITIALSALADPORTION = 150;

    public Salad(String name, double price) {
        super(name, INITIALSALADPORTION, price);
    }
}
