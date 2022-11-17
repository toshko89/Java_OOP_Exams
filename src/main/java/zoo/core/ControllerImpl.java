package zoo.core;

import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

import static zoo.common.ConstantMessages.*;
import static zoo.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository foodRepository = new FoodRepositoryImpl();
    private Collection<Area> areas = new ArrayList<>();

    @Override
    public String addArea(String areaType, String areaName) {
        switch (areaType) {
            case "WaterArea":
                Area waterArea = new WaterArea(areaName);
                areas.add(waterArea);
                return String.format(SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
            case "LandArea":
                Area landArea = new LandArea(areaName);
                areas.add(landArea);
                return String.format(SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
            default:
                throw new NullPointerException(INVALID_AREA_TYPE);
        }

    }

    @Override
    public String buyFood(String foodType) {
        switch (foodType) {
            case "Vegetable":
                Food vegetable = new Vegetable();
                foodRepository.add(vegetable);
                return String.format(SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
            case "Meat":
                Food meat = new Meat();
                foodRepository.add(meat);
                return String.format(SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
            default:
                throw new IllegalArgumentException(INVALID_FOOD_TYPE);
        }
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Area area = areas.stream()
                .filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);
        Food food = foodRepository.findByType(foodType);
        if (food == null) {
            throw new IllegalArgumentException(String.format(NO_FOOD_FOUND, foodType));
        }
        area.addFood(food);
        foodRepository.remove(food);
        return String.format(SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Area area = areas.stream()
                .filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);
        switch (animalType) {
            case "AquaticAnimal":
                Animal aquatic = new AquaticAnimal(animalName, kind, price);
                if (area != null && area.getClass().getSimpleName().equals("WaterArea")) {
                    area.addAnimal(aquatic);
                    return String.format(SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
                } else {
                    return String.format(AREA_NOT_SUITABLE);
                }
            case "TerrestrialAnimal":
                Animal terrestrial = new TerrestrialAnimal(animalName, kind, price);
                if (area != null && area.getClass().getSimpleName().equals("LandArea")) {
                    area.addAnimal(terrestrial);
                    return String.format(SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
                } else {
                    return String.format(AREA_NOT_SUITABLE);
                }
            default:
                throw new IllegalArgumentException(INVALID_ANIMAL_TYPE);
        }
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = areas.stream()
                .filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);
        area.feed();
        return String.format(ANIMALS_FED, area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Area area = areas.stream()
                .filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);
        double sum = 0;
        for (Animal animal : area.getAnimals()) {
            sum += animal.getKg();
        }
        return String.format(KILOGRAMS_AREA, areaName, sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Area area : areas) {
            stringBuilder.append(area.getInfo()).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
