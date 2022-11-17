package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

public class ShopTest {

    Shop shop;
    Goods goods;

    @Before
    public void setUp() {
        this.shop = new Shop();
        this.goods = new Goods("Qke", "wtf");
    }

    @Test
    public void test_getShelves(){
        Map<String, Goods> shelves = shop.getShelves();
        Assert.assertEquals(12,shelves.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addGoods_shelf_doesnt_exist() throws OperationNotSupportedException {
        shop.addGoods("Alabala",this.goods);
    }
    @Test(expected = IllegalArgumentException.class)
    public void test_addGoods_shelfTaken() throws OperationNotSupportedException,IllegalArgumentException {
        Goods goods1 = new Goods("Qke", "wtf");
        Goods goods2 = new Goods("Dunki", "wtf");
        shop.addGoods("Shelves1",goods1);
        shop.addGoods("Shelves1",goods2);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_addGoods_itemExist() throws OperationNotSupportedException ,IllegalArgumentException{
        Goods goods1 = new Goods("Qke", "wtf");
        Goods goods2 = new Goods("Qke", "wtf");
        shop.addGoods("Shelves1",goods1);
        shop.addGoods("Shelves2",goods1);
    }

    @Test
    public void test_addGoods() throws OperationNotSupportedException ,IllegalArgumentException{
        Goods goods1 = new Goods("Qke", "wtf");
        Goods goods2 = new Goods("Qke", "wtf");
        shop.addGoods("Shelves1",goods1);
        String shelves2 = shop.addGoods("Shelves2", goods2);
        Assert.assertEquals("Goods: wtf is placed successfully!",shelves2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeGoodsShelfDoesNotExist(){
        shop.removeGoods("leleee",this.goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeGoodsGoodsDoesNotExist() throws OperationNotSupportedException {
        Goods goods1 = new Goods("Qke", "wtf");
        Goods goods2 = new Goods("Qke", "wtf");
        shop.addGoods("Shelves1",goods1);
        shop.addGoods("Shelves2",goods2);
        shop.removeGoods("Shelves3",goods1);
    }

    @Test
    public void test_removeGoods() throws OperationNotSupportedException {
        Goods goods1 = new Goods("Qke", "wtf");
        Goods goods2 = new Goods("Qke", "wtf");
        shop.addGoods("Shelves1",goods1);
        shop.addGoods("Shelves2",goods2);
        String shelves1 = shop.removeGoods("Shelves1", goods1);
        Assert.assertEquals("Goods: wtf is removed successfully!",shelves1);
    }
}