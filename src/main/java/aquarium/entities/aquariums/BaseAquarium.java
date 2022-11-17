package aquarium.entities.aquariums;

import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.NOT_ENOUGH_CAPACITY;
import static aquarium.common.ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY;

public abstract class BaseAquarium implements Aquarium {

    private String name;
    private int capacity;
    private Collection<Decoration> decorations = new ArrayList<>();
    private Collection<Fish> fish = new ArrayList<>();

    public BaseAquarium(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        return decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public void addFish(Fish fish) {
        if (capacity < fish.getSize()) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.stream().forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s (%s):", getName(), getClass().getSimpleName()))
                .append(System.lineSeparator()).append("Fish: ");

        List<String> collect = fish.stream().map(Fish::getName).collect(Collectors.toList());

        String result = collect.size() > 0
                ? collect.stream().collect(Collectors.joining(" "))
                : "None";

        stringBuilder.append(result).append(System.lineSeparator())
                .append("Decorations: ").append(decorations.size())
                .append(System.lineSeparator())
                .append("Comfort: ").append(this.calculateComfort())
                .append(System.lineSeparator());
        return stringBuilder.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }
}
