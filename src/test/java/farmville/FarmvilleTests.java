package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {

    private Animal animal;
    private Farm farm;

    @Before
    public void setUp() {
        this.animal = new Animal("Dog", 10);
        this.farm = new Farm("My-farm", 5);
    }

    @Test
    public void test_getCount() {
        farm.add(animal);
        int count = farm.getCount();
        Assert.assertEquals(1, count);
    }

    @Test
    public void test_getName() {
        String name = farm.getName();
        Assert.assertEquals("My-farm", name);
    }

    @Test
    public void test_getCapacity() {
        int capacity = farm.getCapacity();
        Assert.assertEquals(5, capacity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add_whenNoCapacity() {
        Animal animal1 = new Animal("Cat", 2);
        Animal animal2 = new Animal("Cow", 2);
        Animal animal3 = new Animal("Meow", 2);
        Animal animal4 = new Animal("Snake", 2);
        Animal animal5 = new Animal("Dog", 2);
        Animal animal6 = new Animal("Rat", 2);

        farm.add(animal1);
        farm.add(animal2);
        farm.add(animal3);
        farm.add(animal4);
        farm.add(animal5);
        farm.add(animal6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add_whenSameType() {
        Animal animal1 = new Animal("Cat", 2);
        Animal animal2 = new Animal("Cat", 2);
        Animal animal3 = new Animal("Cat", 2);
        Animal animal4 = new Animal("Cat", 2);

        farm.add(animal1);
        farm.add(animal2);
        farm.add(animal3);
        farm.add(animal4);
    }

    @Test
    public void test_addAnimal() {
        farm.add(animal);
        int count = farm.getCount();
        Assert.assertEquals(1, count);
    }

    @Test
    public void test_remove() {
        Animal animal1 = new Animal("Snake", 2);
        Animal animal2 = new Animal("Dog", 2);
        Animal animal3 = new Animal("Rat", 2);

        farm.add(animal1);
        farm.add(animal2);
        farm.add(animal3);

        boolean dog = farm.remove("Dog");
        Assert.assertTrue(dog);
    }
    @Test
    public void test_remove2() {
        Animal animal1 = new Animal("Snake", 2);
        Animal animal2 = new Animal("Dog", 2);
        Animal animal3 = new Animal("Rat", 2);

        farm.add(animal1);
        farm.add(animal2);
        farm.add(animal3);

        boolean dog = farm.remove("Men");
        Assert.assertFalse(dog);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_setCapacity(){
        Farm farm1 = new Farm("Neshto",-1);
    }

    @Test(expected = NullPointerException.class)
    public void test_setName(){
        Farm farm1 = new Farm(null,10);
    }
    @Test(expected = NullPointerException.class)
    public void test_setName2(){
        Farm farm1 = new Farm("        ",10);
    }



}
