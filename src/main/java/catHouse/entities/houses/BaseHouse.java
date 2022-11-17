package catHouse.entities.houses;

import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT;
import static catHouse.common.ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY;

public abstract class BaseHouse implements House {

    private String name;
    private int capacity;
    private Collection<Toy> toys = new ArrayList<>();
    private Collection<Cat> cats = new ArrayList<>();

    public BaseHouse(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
    }

    @Override
    public int sumSoftness() {
        return toys.stream().mapToInt(Toy::getSoftness).sum();
    }

    @Override
    public void addCat(Cat cat) {
        if (this.cats.size() >= capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
        cats.add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
        this.cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        this.toys.add(toy);
    }

    @Override
    public void feeding() {
        this.cats.forEach(Cat::eating);
    }

    @Override
    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getName()).append(" ").append(this.getClass().getSimpleName())
                .append(":").append(System.lineSeparator());

        String catsName = cats.size() > 0
                ? cats.stream().map(Cat::getName).collect(Collectors.joining(" "))
                : "none";

        stringBuilder.append("Cats: ").append(catsName).append(System.lineSeparator());
        stringBuilder.append("Toys: ").append(toys.size()).append(" Softness: ")
                .append(this.sumSoftness()).append(System.lineSeparator());

        return stringBuilder.toString().trim();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Cat> getCats() {
        return cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return toys;
    }
}
