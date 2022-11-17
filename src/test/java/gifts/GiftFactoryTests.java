package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class GiftFactoryTests {

    private Gift gift;
    private GiftFactory giftFactory;

    @Before
    public void setUp() {
        this.gift = new Gift("Nqma", 2.00);
        this.giftFactory = new GiftFactory();
    }

    @Test
    public void test_getCount(){
        giftFactory.createGift(gift);
        Assert.assertEquals(1,giftFactory.getCount());
    }

    @Test
    public void test_createGift(){
        Gift nqma2 = new Gift("Nqma", 2.00);
        Assert.assertEquals("Successfully added gift Nqma with magic 2.00.",giftFactory.createGift(nqma2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createGift2(){
        Gift nqma2 = new Gift("Nqma", 2.00);
        giftFactory.createGift(gift);
        giftFactory.createGift(nqma2);
    }

    @Test(expected = NullPointerException.class)
    public void test_removeGift_withNull(){
        giftFactory.createGift(gift);
        giftFactory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_removeGift_withNull2(){
        giftFactory.createGift(gift);
        giftFactory.removeGift("    ");
    }

    @Test
    public void test_removeGift(){
        giftFactory.createGift(gift);
        boolean nqma = giftFactory.removeGift("Nqma");
        Assert.assertTrue(nqma);
    }

    @Test
    public void test_removeGift2(){
        giftFactory.createGift(gift);
        boolean nqma = giftFactory.removeGift("Ima");
        Assert.assertFalse(nqma);
    }

    @Test
    public void test_getPresentWithLeastMagic(){
        Gift gift1 = new Gift("Ima", 1.00);
        Gift gift2 = new Gift("Nishto", 3.00);
        giftFactory.createGift(gift);
        giftFactory.createGift(gift1);
        giftFactory.createGift(gift2);

        Gift presentWithLeastMagic = giftFactory.getPresentWithLeastMagic();

        Assert.assertEquals(1,presentWithLeastMagic.getMagic(),0.00);

    }

    @Test
    public void test_getPresent(){
        Gift gift1 = new Gift("Ima", 1.00);
        Gift gift2 = new Gift("Nishto", 3.00);
        giftFactory.createGift(gift);
        giftFactory.createGift(gift1);
        giftFactory.createGift(gift2);

        Gift giftFactoryPresent = giftFactory.getPresent("Ima");

        Assert.assertEquals("Ima",giftFactoryPresent.getType());
    }

    @Test
    public void test_getPresents(){
        Gift gift1 = new Gift("Ima", 1.00);
        Gift gift2 = new Gift("Nishto", 3.00);
        giftFactory.createGift(gift);
        giftFactory.createGift(gift1);
        giftFactory.createGift(gift2);

        Collection<Gift> presents = giftFactory.getPresents();

        Assert.assertEquals(3,presents.size());
    }



}
