package restaurant.entities.drinks.interfaces;

public class Fresh extends BaseBeverage {

    private static final double FRESHPRICE = 3.50;

    public Fresh(String name, int counter, String brand) {
        super(name, counter, FRESHPRICE, brand);
    }
}
