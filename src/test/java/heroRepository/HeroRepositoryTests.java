package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class HeroRepositoryTests {

    private Hero hero;
    private HeroRepository heroRepository;

    @Before
    public void setUp() {
        this.hero = new Hero("Gosho", 99);
        this.heroRepository = new HeroRepository();
    }

    @Test
    public void test_getCount() {
        heroRepository.create(new Hero("Toshko", 100));
        heroRepository.create(new Hero("Petar", 100));
        heroRepository.create(new Hero("Misho", 100));
        int count = heroRepository.getCount();

        Assert.assertEquals(3, count);
    }

    @Test(expected = NullPointerException.class)
    public void test_createWithNull() {
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createWithSameName() {
        heroRepository.create(new Hero("Toshko", 100));
        heroRepository.create(new Hero("Toshko", 100));
    }

    @Test
    public void test_create() {
        String toshko = heroRepository.create(new Hero("Toshko", 100));

        Assert.assertEquals("Successfully added hero Toshko with level 100", toshko);
    }

    @Test(expected = NullPointerException.class)
    public void test_RemoveWithNull() {
        String toshko = heroRepository.create(new Hero("Toshko", 100));
        heroRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_Remove2() {
        String toshko = heroRepository.create(new Hero("Toshko", 100));
        heroRepository.remove("     ");
    }

    @Test
    public void test_Remove3() {
        String toshko = heroRepository.create(new Hero("Toshko", 100));
        boolean toshko1 = heroRepository.remove("Toshko");
        Assert.assertTrue(toshko1);
    }

    @Test
    public void test_Remove4() {
        String toshko = heroRepository.create(new Hero("Toshko", 100));
        boolean toshko1 = heroRepository.remove("Goshko");
        Assert.assertFalse(toshko1);
    }

    @Test
    public void test_GetLevel() {
        heroRepository.create(new Hero("Toshko", 100));
        heroRepository.create(new Hero("Peshko", 1));
        heroRepository.create(new Hero("Sashko", 99));
        Hero heroWithHighestLevel = heroRepository.getHeroWithHighestLevel();
        Assert.assertEquals("Toshko", heroWithHighestLevel.getName());
    }

    @Test
    public void test_GetLevel2() {
        heroRepository.create(new Hero("Toshko", 100));
        heroRepository.create(new Hero("Peshko", 100));
        heroRepository.create(new Hero("Sashko", 100));
        Hero heroWithHighestLevel = heroRepository.getHeroWithHighestLevel();
        Assert.assertEquals("Toshko", heroWithHighestLevel.getName());
    }

    @Test
    public void test_GetByName() {
        heroRepository.create(new Hero("Toshko", 100));
        heroRepository.create(new Hero("Peshko", 100));
        heroRepository.create(new Hero("Sashko", 100));
        Hero toshko = heroRepository.getHero("Toshko");
        Assert.assertEquals(100, toshko.getLevel());
    }

    @Test
    public void test_GetByName2() {
        heroRepository.create(new Hero("Toshko", 100));
        heroRepository.create(new Hero("Peshko", 100));
        heroRepository.create(new Hero("Sashko", 100));
        Hero toshko = heroRepository.getHero("Nqmasi");
        Assert.assertNull(toshko);
    }

    @Test
    public void test_getAll() {
        heroRepository.create(new Hero("Toshko", 100));
        heroRepository.create(new Hero("Peshko", 100));
        heroRepository.create(new Hero("Sashko", 100));
        Collection<Hero> heroes = heroRepository.getHeroes();
        Assert.assertEquals(3,heroes.size());
    }

}
