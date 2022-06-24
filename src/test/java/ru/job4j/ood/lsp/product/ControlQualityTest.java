package ru.job4j.ood.lsp.product;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ControlQualityTest {

    private List<Store> storeList;
    private Trash trashStore;
    private Shop shopStore;
    private Warehouse warehouseStore;

    @Before
    public void initData() {
        trashStore = new Trash();
        shopStore = new Shop();
        warehouseStore = new Warehouse();

        storeList = new ArrayList<>();
        storeList.add(trashStore);
        storeList.add(shopStore);
        storeList.add(warehouseStore);
    }

    @Test
    public void whenTrash() {
        LocalDate now = LocalDate.now();
        LocalDate expiryDate = now.minusDays(1);
        LocalDate createDate = now.minusDays(3);
        Food food = new Food("food", expiryDate, createDate, 100, 0);
        List<Food> foodList = List.of(food);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.control(foodList);
        assertThat(trashStore.findAll(), is(List.of(food)));
    }

    @Test
    public void whenWarehouse() {
        LocalDate now = LocalDate.now();
        LocalDate expiryDate = now.plusDays(30);
        Food food = new Food("food", expiryDate, now, 100, 0);
        List<Food> foodList = List.of(food);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.control(foodList);
        assertThat(warehouseStore.findAll(), is(List.of(food)));
    }

    @Test
    public void whenShop() {
        LocalDate now = LocalDate.now();
        LocalDate createDate = now.minusDays(20);
        LocalDate expiryDate = now.plusDays(10);
        Food food = new Food("food", expiryDate, createDate, 100, 0);
        List<Food> foodList = List.of(food);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.control(foodList);
        assertThat(shopStore.findAll(), is(List.of(food)));
    }

    @Test
    public void whenShopAndSetDiscount() {
        LocalDate now = LocalDate.now();
        LocalDate createDate = now.minusDays(20);
        LocalDate expiryDate = now.plusDays(5);
        Food food = new Food("food", expiryDate, createDate, 100, 0);
        List<Food> foodList = List.of(food);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.control(foodList);
        Assert.assertEquals(30.0, shopStore.findAll().get(0).getDiscount(), 0.00);
    }

    @Test
    public void whenSeveralGoods() {
        LocalDate now = LocalDate.now();

        LocalDate createDate = now.minusDays(20);
        LocalDate expiryDate = now.plusDays(5);
        Food milk = new Milk("milk", expiryDate, createDate, 100, 0);

        createDate = now;
        expiryDate = now.plusDays(30);
        Food bread = new Bread("bread", expiryDate, createDate, 100, 0);

        createDate = now.minusDays(20);
        expiryDate = now.minusDays(1);
        Food bread2 = new Bread("bread2", expiryDate, createDate, 100, 0);

        List<Food> foodList = List.of(milk, bread, bread2);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.control(foodList);

        assertThat(trashStore.findAll(), is(List.of(bread2)));
        assertThat(warehouseStore.findAll(), is(List.of(bread)));
        assertThat(shopStore.findAll(), is(List.of(milk)));
    }
}