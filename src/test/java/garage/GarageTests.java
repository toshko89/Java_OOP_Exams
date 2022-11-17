package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {

    Car car;
    Garage garage;

    @Before
    public void setUp() {
        this.car = new Car("BMW", 220, 20000.00);
        this.garage = new Garage();
    }

    @Test
    public void test_getCars() {
        garage.addCar(car);
        List<Car> cars = garage.getCars();

        Assert.assertEquals(1, cars.size());

    }

    @Test
    public void test_getCount() {
        garage.addCar(car);
        int cars = garage.getCount();

        Assert.assertEquals(1, cars);

    }

    @Test
    public void test_findAllCarsWithMaxSpeedAbove() {
        Car car1 = new Car("Tesla", 300, 30000.00);
        garage.addCar(car);
        garage.addCar(car1);

        List<Car> allCarsWithMaxSpeedAbove = garage.findAllCarsWithMaxSpeedAbove(200);
        Assert.assertEquals(2, allCarsWithMaxSpeedAbove.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addCar_withNull() {
        Car car1 = new Car("Tesla", 300, 30000.00);
        garage.addCar(null);
    }

    @Test
    public void test_getTheMostExpensiveCar() {
        Car car1 = new Car("Tesla", 300, 30000.00);
        Car car2 = new Car("Mercedes", 300, 4000.00);
        garage.addCar(car1);
        garage.addCar(car2);

        Car theMostExpensiveCar = garage.getTheMostExpensiveCar();

        Assert.assertEquals("Tesla", theMostExpensiveCar.getBrand());
    }

    public void test_getTheMostExpensiveCar2() {
        Car car1 = new Car("Tesla", 300, 30000.00);
        Car car2 = new Car("Mercedes", 300, 30000.00);
        garage.addCar(car1);
        garage.addCar(car2);

        Car theMostExpensiveCar = garage.getTheMostExpensiveCar();

        Assert.assertEquals("Tesla", theMostExpensiveCar.getBrand());
    }

    @Test
    public void test_findAllCarsByBrand() {
        Car car1 = new Car("Tesla", 300, 30000.00);
        Car car2 = new Car("Tesla", 300, 4000.00);
        garage.addCar(car1);
        garage.addCar(car2);

        List<Car> tesla = garage.findAllCarsByBrand("Tesla");
        Assert.assertEquals(2, tesla.size());
    }

}