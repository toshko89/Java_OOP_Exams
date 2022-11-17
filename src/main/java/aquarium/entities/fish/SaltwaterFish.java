package aquarium.entities.fish;

public class SaltwaterFish extends BaseFish {
    private int size = 5;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
    }

    @Override
    public void eat() {
        size += 2;
    }
}
