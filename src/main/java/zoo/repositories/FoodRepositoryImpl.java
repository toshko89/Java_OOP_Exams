package zoo.repositories;

import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;

public class FoodRepositoryImpl implements FoodRepository {

    private Collection<Food> foods = new ArrayList<>();

    public Collection<Food> getFoods() {
        return foods;
    }

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public boolean remove(Food food) {
        Food food1 = foods.stream().filter(f -> f.equals(food)).findFirst().orElse(null);
        if (food1 != null) {
            foods.remove(food1);
            return true;
        }
        return false;
    }

    @Override
    public Food findByType(String type) {
        return foods.stream()
                .filter(food -> food.getClass().getSimpleName().equals(type))
                .findFirst().orElse(null);
    }
}
