package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.AREA_NAME_NULL_OR_EMPTY;
import static zoo.common.ExceptionMessages.NOT_ENOUGH_CAPACITY;

public abstract class BaseArea implements Area {

    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    protected BaseArea(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return foods;
    }

    @Override
    public int sumCalories() {
        return this.foods.stream().mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (capacity < this.animals.size()) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        this.animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.stream()
                .filter(a -> a.equals(animal))
                .findFirst()
                .ifPresent(value -> this.animals.remove(value));
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
        for (Animal animal : this.animals) {
            animal.eat();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s (%s):", this.name, getClass().getSimpleName()))
                .append(System.lineSeparator());
        if (animals.size() == 0) {
            stringBuilder.append("Animals: none").append(System.lineSeparator());
        } else {
            stringBuilder
                    .append("Animals: ")
                    .append(animals.stream().map(Animal::getName)
                            .collect(Collectors.joining(" ")))
                    .append(System.lineSeparator());
        }
        stringBuilder.append("Foods: ").append(foods.size()).append(System.lineSeparator());
        stringBuilder.append("Calories: ").append(this.sumCalories());

        return stringBuilder.toString().trim();
    }
}
