package restaurant.entities.tables.interfaces;

public class InGarden extends BaseTable {
    private static final double PRICEPERPERSON = 4.50;

    public InGarden(int number, int size) {
        super(number, size, PRICEPERPERSON);
    }
}
