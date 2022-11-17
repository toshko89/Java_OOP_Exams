package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {

    private PetStore petStore;

    @Before
    public void setUp() {
        this.petStore = new PetStore();
        Animal cat = new Animal("cat", 10, 25.00);
        Animal dog = new Animal("dog", 30, 30.00);
        petStore.addAnimal(cat);
        petStore.addAnimal(dog);
    }

    @Test
    public void test_getAnimals() {
        List<Animal> animals = petStore.getAnimals();
        Assert.assertEquals(2, animals.size());
        Assert.assertEquals(2, petStore.getCount());
    }

    @Test
    public void test_findAllAnimalsWithMaxKilograms() {
        List<Animal> allAnimalsWithMaxKilograms = petStore.findAllAnimalsWithMaxKilograms(25);
        Assert.assertEquals(1, allAnimalsWithMaxKilograms.size());
    }

    @Test
    public void test_findAllAnimalsWithMaxKilograms2() {
        List<Animal> allAnimalsWithMaxKilograms = petStore.findAllAnimalsWithMaxKilograms(40);
        Assert.assertEquals(0, allAnimalsWithMaxKilograms.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addWithNull() {
        petStore.addAnimal(null);
    }

    @Test
    public void test_getTheMostExpensiveAnimal() {
        Animal theMostExpensiveAnimal = petStore.getTheMostExpensiveAnimal();
        Assert.assertEquals(30.00, theMostExpensiveAnimal.getPrice(), 0.00);
    }

    @Test
    public void test_findAllAnimalBySpecie() {
        List<Animal> ribka = petStore.findAllAnimalBySpecie("ribka");
        Assert.assertEquals(0, ribka.size());
    }

    @Test
    public void test_findAllAnimalBySpecie2() {
        List<Animal> ribka = petStore.findAllAnimalBySpecie("cat");
        Assert.assertEquals(1, ribka.size());
    }
}

