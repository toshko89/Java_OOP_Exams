package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    private House house;
    private Cat cat;

    @Before
    public void setUp() {
        this.house = new House("Nqma", 10);
        this.cat = new Cat("Goshko");
    }

    @Test
    public void test_getName() {
        String name = house.getName();
        Assert.assertEquals("Nqma", name);
    }

    @Test(expected = NullPointerException.class)
    public void test_setName_withNull() {
        House house1 = new House(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void test_setName_withNull2() {
        House house1 = new House("    ", 5);
    }

    @Test
    public void test_getCapasity() {
        int capacity = this.house.getCapacity();
        Assert.assertEquals(10, capacity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_setCapacity() {
        House house1 = new House("Nishto", -1);
    }

    @Test
    public void test_setCapacity2() {
        House house1 = new House("OK", 0);
        String name = house1.getName();
        Assert.assertEquals("OK", name);
    }

    @Test
    public void test_getCount() {
        this.house.addCat(new Cat("Peshko"));
        int countCats = house.getCount();
        Assert.assertEquals(1, countCats);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add() {
        House house1 = new House("Tam", 5);
        house1.addCat(new Cat("Tobi"));
        house1.addCat(new Cat("Tam"));
        house1.addCat(new Cat("Mimi"));
        house1.addCat(new Cat("Tim"));
        house1.addCat(new Cat("Aleex"));
        house1.addCat(new Cat("Opa"));
    }

    @Test
    public void test_removeCat() {
        House house1 = new House("Tam", 5);
        house1.addCat(new Cat("Tobi"));
        house1.addCat(new Cat("Timi"));
        house1.addCat(new Cat("Topsho"));
        house1.removeCat("Tobi");
        Assert.assertEquals(2, house1.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeCat2() {
        House house1 = new House("Tam", 5);
        house1.addCat(new Cat("Tobi"));
        house1.addCat(new Cat("Timi"));
        house1.addCat(new Cat("Topsho"));
        house1.removeCat("NQMA GO");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_catForSale() {
        House house1 = new House("Tam", 5);
        house1.addCat(new Cat("Tobi"));
        house1.addCat(new Cat("Timi"));
        Cat toshko = house1.catForSale("Toshko");
    }

    @Test
    public void test_catForSale2() {
        House house1 = new House("Tam", 5);
        house1.addCat(new Cat("Tobi"));
        house1.addCat(new Cat("Timi"));
        Cat timi = house1.catForSale("Timi");
        Assert.assertFalse(timi.isHungry());
    }

    @Test
    public void test_statistics() {
        House house1 = new House("Tam", 5);
        house1.addCat(new Cat("Tobi"));
        house1.addCat(new Cat("Timi"));
        String statistics = house1.statistics();
        Assert.assertEquals("The cat Tobi, Timi is in the house Tam!",statistics);
    }

}
