package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.drinks.interfaces.Fresh;
import restaurant.entities.drinks.interfaces.Smoothie;
import restaurant.entities.healthyFoods.interfaces.Food;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.healthyFoods.interfaces.Salad;
import restaurant.entities.healthyFoods.interfaces.VeganBiscuits;
import restaurant.entities.tables.interfaces.InGarden;
import restaurant.entities.tables.interfaces.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.BeverageRepository;
import restaurant.repositories.interfaces.HealthFoodRepository;
import restaurant.repositories.interfaces.TableRepository;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        switch (type) {
            case "Salad":
                Food salad = new Salad(name, price);
                HealthyFood foodByName = healthFoodRepository.foodByName(name);
                if (foodByName == null) {
                    healthFoodRepository.add(salad);
                    return String.format(FOOD_ADDED, name);
                }
                throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
            case "VeganBiscuits":
                Food biscuits = new VeganBiscuits(name, price);
                HealthyFood foodByName2 = healthFoodRepository.foodByName(name);
                if (foodByName2 == null) {
                    healthFoodRepository.add(biscuits);
                    return String.format(FOOD_ADDED, name);
                }
                throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
        }
        return null;
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        switch (type) {
            case "Smoothie":
                Beverages smoothie = new Smoothie(name, counter, brand);
                Beverages beverageByName = beverageRepository.beverageByName(name, brand);
                if (beverageByName == null) {
                    beverageRepository.add(smoothie);
                    return String.format(BEVERAGE_ADDED, type, brand);
                }
                throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
            case "Fresh":
                Beverages fresh = new Fresh(name, counter, brand);
                Beverages beverageByName2 = beverageRepository.beverageByName(name, brand);
                if (beverageByName2 == null) {
                    beverageRepository.add(fresh);
                    return String.format(BEVERAGE_ADDED, type, brand);
                }
                throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
        }
        return null;
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        switch (type) {
            case "Indoors":
                Table indoors = new Indoors(tableNumber, capacity);
                Table table = tableRepository.byNumber(tableNumber);
                if (table == null) {
                    tableRepository.add(indoors);
                    return String.format(TABLE_EXIST, tableNumber);
                }
                throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
            case "InGarden":
                Table garden = new InGarden(tableNumber, capacity);
                Table table2 = tableRepository.byNumber(tableNumber);
                if (table2 == null) {
                    tableRepository.add(garden);
                    return String.format(TABLE_EXIST, tableNumber);
                }
                throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
        }
        return null;
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table ableToReserve = tableRepository.getAllEntities().stream()
                .filter(table -> table.getSize() >= numberOfPeople)
                .findFirst().orElse(null);
        if (ableToReserve != null) {
            if (!ableToReserve.isReservedTable()) {
                return String.format(TABLE_RESERVED, ableToReserve.getTableNumber(), numberOfPeople);
            } else {
                ableToReserve.reserve(numberOfPeople);
                return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
            }
        }
        return "";
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table tableFound = tableRepository.getAllEntities().stream()
                .filter(table -> table.getTableNumber() == tableNumber)
                .findFirst().orElse(null);
        if (tableFound == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        HealthyFood healthyFoodFound = healthFoodRepository.getAllEntities().stream()
                .filter(healthyFood -> healthyFood.getName().equals(healthyFoodName))
                .findFirst().orElse(null);
        if (healthyFoodFound == null) {
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }

        tableFound.orderHealthy(healthyFoodFound);
        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table tableFound = tableRepository.getAllEntities().stream()
                .filter(table -> table.getTableNumber() == tableNumber)
                .findFirst().orElse(null);
        if (tableFound == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        Beverages beverage = beverageRepository.beverageByName(name, brand);
        if (beverage == null) {
            return String.format(NON_EXISTENT_DRINK, name, brand);
        }
        tableFound.orderBeverages(beverage);
        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    private double allBills = 0;

    @Override
    public String closedBill(int tableNumber) {
        Table table = tableRepository.byNumber(tableNumber);
        double bill = table.bill();
        double allPeoplePrice = table.allPeople();
        allBills += allPeoplePrice;
        table.clear();
        return String.format(BILL, tableNumber, allPeoplePrice);
    }


    @Override
    public String totalMoney() {
        return String.format(TOTAL_MONEY, allBills);
    }
}
