package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {

    private Astronaut astronaut;
    private Spaceship spaceship;

    @Before
    public void setUp() {
        this.astronaut = new Astronaut("Todor", 50);
        this.spaceship = new Spaceship("Nqma", 5);
    }

    @Test
    public void test_getCount() {
        spaceship.add(new Astronaut("Niki", 20));
        spaceship.add(new Astronaut("Pepi", 20));
        int count = spaceship.getCount();
        Assert.assertEquals(2, count);
    }

    @Test
    public void test_getName() {
        String name = spaceship.getName();
        Assert.assertEquals("Nqma", name);
    }

    @Test
    public void test_getCapacity() {
        int capacity = spaceship.getCapacity();
        Assert.assertEquals(5, capacity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add1(){
        spaceship.add(new Astronaut("m",10));
        spaceship.add(new Astronaut("a",10));
        spaceship.add(new Astronaut("v",10));
        spaceship.add(new Astronaut("e",10));
        spaceship.add(new Astronaut("q",10));
        spaceship.add(new Astronaut("h",10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add2(){
        spaceship.add(new Astronaut("m",10));
        spaceship.add(new Astronaut("m",10));
    }

    @Test
    public void test_Remove(){
        spaceship.add(new Astronaut("Nasko",10));
        spaceship.add(new Astronaut("Vasko",10));
        boolean nasko = spaceship.remove("Nasko");
        Assert.assertTrue(nasko);
    }

    @Test
    public void test_Remove2(){
        spaceship.add(new Astronaut("Nasko",10));
        spaceship.add(new Astronaut("Vasko",10));
        boolean nasko = spaceship.remove("Nasko12121");
        Assert.assertFalse(nasko);
    }

    @Test
    public void test_setCapacity(){
        Spaceship spaceship1 = new Spaceship("Yolo",0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_setCapacity2(){
        Spaceship spaceship1 = new Spaceship("Yolo",-1);
    }

    @Test(expected = NullPointerException.class)
    public void test_setName(){
        Spaceship spaceship1 = new Spaceship(null,10);
    }

    @Test(expected = NullPointerException.class)
    public void test_setName2(){
        Spaceship spaceship1 = new Spaceship("          ",10);
    }

}
