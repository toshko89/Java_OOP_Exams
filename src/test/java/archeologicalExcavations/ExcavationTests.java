package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExcavationTests {

    private Excavation excavation;
    private Archaeologist archaeologist;
    private static final String NAME = "Test";
    private static final int CAPACITY = 5;

    @Before
    public void setUp() {
        this.excavation = new Excavation(NAME, CAPACITY);
        this.archaeologist = new Archaeologist("Gosho", 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetWithNullName(){
        Excavation excavation1 = new Excavation(null,5);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetWithWhiteSpace(){
        Excavation excavation1 = new Excavation(" ",5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SetCapacityWith0(){
        Excavation excavation1 = new Excavation("Nqma ime",-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddArchWithNoSpace(){
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
    }

    @Test
    public void test_removeArch(){
        excavation.addArchaeologist(archaeologist);
        boolean removeArchaeologist = excavation.removeArchaeologist("Gosho");
        Assert.assertTrue(removeArchaeologist);
    }




}
