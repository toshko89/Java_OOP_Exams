package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {


    private ToyRepository toyRepository = new ToyRepository();
    private Collection<House> houses = new ArrayList<>();

    @Override
    public String addHouse(String type, String name) {

        if (!type.equals("ShortHouse") && !type.equals("LongHouse")) {

            throw new NullPointerException(INVALID_HOUSE_TYPE);
        }
        switch (type) {
            case "ShortHouse":
                House shortHouse = new ShortHouse(name);
                houses.add(shortHouse);
                return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
            case "LongHouse":
                House longHouse = new LongHouse(name);
                houses.add(longHouse);
                return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
            default:
                throw new NullPointerException(INVALID_HOUSE_TYPE);
        }
    }

    @Override
    public String buyToy(String type) {

        if (!type.equals("Ball") && !type.equals("Mouse")) {

            throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }

        switch (type) {
            case "Ball":
                Toy ball = new Ball();
                toyRepository.buyToy(ball);
                return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
            case "Mouse":
                Toy mouse = new Mouse();
                toyRepository.buyToy(mouse);
                return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
            default:
                throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        House houseFound = houses.stream()
                .filter(house -> house.getName().equals(houseName)).findFirst().orElse(null);
        Toy toyFound = toyRepository.findFirst(toyType);
        if (toyFound == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }

        houseFound.buyToy(toyFound);
        toyRepository.removeToy(toyFound);
        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {

        if (!catType.equals("ShorthairCat") && !catType.equals("LonghairCat")) {

            throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }

        House houseFound = houses.stream()
                .filter(house -> house.getName().equals(houseName)).findFirst().orElse(null);
        String HouseSimpleName = houseFound.getClass().getSimpleName();
        switch (catType) {
            case "ShorthairCat":
                Cat shorthaircat = new ShorthairCat(catName, catBreed, price);
                if ("ShortHouse".equals(HouseSimpleName)) {
                    houseFound.addCat(shorthaircat);
                    return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
                } else {
                    return String.format(UNSUITABLE_HOUSE);
                }
            case "LonghairCat":
                Cat longhaircat = new LonghairCat(catName, catBreed, price);
                if ("LongHouse".equals(HouseSimpleName)) {
                    houseFound.addCat(longhaircat);
                    return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
                } else {
                    return String.format(UNSUITABLE_HOUSE);
                }

            default:
                throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }
    }

    @Override
    public String feedingCat(String houseName) {
        House houseFound = houses.stream()
                .filter(house -> house.getName().equals(houseName)).findFirst().orElse(null);
        int size = houseFound.getCats().size();
        houseFound.feeding();
        return String.format(FEEDING_CAT, size);
    }

    @Override
    public String sumOfAll(String houseName) {
        House houseFound = houses.stream()
                .filter(house -> house.getName().equals(houseName)).findFirst().orElse(null);
        double sumCars = houseFound.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double sumToys = houseFound.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double total = sumCars + sumToys;
        return String.format(VALUE_HOUSE, houseName, total);
    }

    @Override
    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();

        for (House house : houses) {
            stringBuilder.append(house.getStatistics()).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
