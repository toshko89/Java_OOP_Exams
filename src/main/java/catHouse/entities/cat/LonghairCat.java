package catHouse.entities.cat;

public class LonghairCat extends BaseCat {

    private int kilograms = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public void eating() {
        kilograms += 3;
    }

    @Override
    public int getKilograms() {
        return kilograms;
    }
}
