package restaurant.entities.tables.interfaces;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood = new ArrayList<>();
    private Collection<Beverages> beverages = new ArrayList<>();
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable = true;
    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
    }

    private void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        return pricePerPerson * numberOfPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        this.isReservedTable = false;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double sumFood = this.healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double sumDrinks = this.beverages.stream().mapToDouble(Beverages::getPrice).sum();
        return sumFood + sumDrinks;
    }

    @Override
    public void clear() {
        this.beverages.clear();
        this.healthyFood.clear();
        this.isReservedTable = true;
        this.numberOfPeople = 0;
        this.pricePerPerson = 0;
    }

    @Override
    public String tableInformation() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Table - ").append(this.number).append(System.lineSeparator())
                .append("Size - ").append(this.size).append(System.lineSeparator())
                .append("Type - ").append(getClass().getSimpleName()).append(System.lineSeparator())
                .append("All price - ").append(this.pricePerPerson);

        return stringBuilder.toString().trim();
    }
}
