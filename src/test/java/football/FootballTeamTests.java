package football;

import org.junit.Assert;
import org.junit.Test;

public class FootballTeamTests {

    private static final Footballer foodballer = new Footballer("Kiro");

    private static FootballTeam footballTeam = new FootballTeam("Levski", 10);

    @Test(expected = IllegalArgumentException.class)
    public void test_setVacantPositions_withLessThan0() {
        FootballTeam footballTeam = new FootballTeam("Levski", -1);
    }

    @Test(expected = NullPointerException.class)
    public void test_setname() {
        FootballTeam footballTeam = new FootballTeam("   ", 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_setname2() {
        FootballTeam footballTeam = new FootballTeam(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addFootballerWith0space() {
        FootballTeam footballTeam = new FootballTeam("Levski", 0);
        footballTeam.addFootballer(foodballer);
    }

    @Test
    public void test_setWith0() {
        FootballTeam footballTeam = new FootballTeam("Levski", 0);
        Assert.assertEquals(0, footballTeam.getCount());
    }

    @Test
    public void test_getVacantPosition() {
        Assert.assertEquals(10, footballTeam.getVacantPositions());
    }

    @Test
    public void test_getName() {
        Assert.assertEquals("Levski", footballTeam.getName());
    }

    @Test
    public void test_getCount() {
        FootballTeam footballTeam1 = new FootballTeam("CSKA", 10);
        footballTeam1.addFootballer(foodballer);
        footballTeam1.addFootballer(foodballer);
        footballTeam1.addFootballer(foodballer);
        footballTeam1.addFootballer(foodballer);
        footballTeam1.addFootballer(foodballer);
        Assert.assertEquals(5, footballTeam1.getCount());
    }

    @Test
    public void test_removeFoodballer() {
        FootballTeam footballTeam1 = new FootballTeam("Levski",10);
        Footballer foodballer1 = new Footballer("Gosho");
        Footballer foodballer2 = new Footballer("Pesho");
        Footballer foodballer3 = new Footballer("Tosho");
        footballTeam1.addFootballer(foodballer1);
        footballTeam1.addFootballer(foodballer2);
        footballTeam1.addFootballer(foodballer3);
        footballTeam1.removeFootballer("Pesho");
        Assert.assertEquals(2, footballTeam1.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeFoodballer_wrongName() {
        Footballer foodballer1 = new Footballer("Gosho");
        Footballer foodballer2 = new Footballer("Pesho");
        Footballer foodballer3 = new Footballer("Tosho");
        footballTeam.addFootballer(foodballer1);
        footballTeam.addFootballer(foodballer2);
        footballTeam.addFootballer(foodballer3);
        footballTeam.removeFootballer("Kiro");
    }

    @Test
    public void test_footballerForSale() {
        FootballTeam footballTeam1 = new FootballTeam("Levski",10);
        Footballer foodballer1 = new Footballer("Gosho");
        Footballer foodballer2 = new Footballer("Pesho");
        footballTeam1.addFootballer(foodballer1);
        footballTeam1.addFootballer(foodballer2);

        footballTeam1.footballerForSale("Pesho");

        Assert.assertFalse(foodballer2.isActive());
        Assert.assertEquals(foodballer2, foodballer2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_footballerForSale_withNull() {
        Footballer foodballer1 = new Footballer("Gosho");
        Footballer foodballer2 = new Footballer("Pesho");
        footballTeam.addFootballer(foodballer1);
        footballTeam.addFootballer(foodballer2);

        footballTeam.footballerForSale("Kiro");

        Assert.assertFalse(foodballer2.isActive());
    }

    @Test
    public void test_getStatistics() {
        FootballTeam footballTeam2 = new FootballTeam("Nqkoi",10);
        Footballer footballer = new Footballer("Kiril");
        Footballer footballer1 = new Footballer("Nikola");
        footballTeam2.addFootballer(footballer);
        footballTeam2.addFootballer(footballer1);
        Assert.assertEquals("The footballer Kiril, Nikola is in the team Nqkoi.", footballTeam2.getStatistics());
    }

}
