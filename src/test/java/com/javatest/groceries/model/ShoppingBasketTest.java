package com.javatest.groceries.model;

import com.javatest.groceries.GroceriesMain;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import static com.javatest.groceries.GroceriesMain.APPLE;
import static com.javatest.groceries.GroceriesMain.BREAD;
import static com.javatest.groceries.GroceriesMain.MILK;
import static com.javatest.groceries.GroceriesMain.SOUP;
import static org.junit.Assert.assertEquals;

public class ShoppingBasketTest {
    public static final double DOUBLE_ASSERT_DELTA = 0.0000001d;

    private ShoppingBasket shoppingBasket;

    @Before
    public void setup() {
        shoppingBasket = new ShoppingBasket();
    }

    @Test
    public void testAddingAndRemoving() {
        shoppingBasket.addProduct(MILK, 10);
        shoppingBasket.addProduct(MILK, 10);

        shoppingBasket.addProduct(APPLE, 5);
        shoppingBasket.addProduct(APPLE, 2);
        shoppingBasket.addProduct(APPLE, 3);

        shoppingBasket.addProduct(BREAD, 2);
        shoppingBasket.addProduct(BREAD, 3);

        shoppingBasket.removeProduct(MILK);

        Map<Product, Integer> products = shoppingBasket.getProducts();
        assertEquals(2, products.size());
        assertEquals(10, products.get(APPLE).intValue());
        assertEquals(5, products.get(BREAD).intValue());
    }

    @Test
    public void testSimpleTotalCalculation() {
        shoppingBasket.addProduct(MILK, 5);
        shoppingBasket.addProduct(MILK, 5);

        shoppingBasket.addProduct(APPLE, 10);
        shoppingBasket.addProduct(APPLE, 10);

        BigDecimal total = shoppingBasket.calculateTotal(LocalDate.now(), Collections.EMPTY_LIST);
        assertEquals(15, total.doubleValue(), DOUBLE_ASSERT_DELTA);
    }

    @Test
    public void testSoupsAndBreadNow() {
        shoppingBasket.addProduct(SOUP, 3);
        shoppingBasket.addProduct(BREAD, 2);

        verifyTotalWithPromotions(LocalDate.now(), 3.15d);
    }

    @Test
    public void testApplesAndMilkNow() {
        shoppingBasket.addProduct(APPLE, 6);
        shoppingBasket.addProduct(MILK, 1);

        verifyTotalWithPromotions(LocalDate.now(), 1.90d);
    }

    @Test
    public void testApplesAndMilk5Days() {
        shoppingBasket.addProduct(APPLE, 6);
        shoppingBasket.addProduct(MILK, 1);

        verifyTotalWithPromotions(LocalDate.now().plusDays(5), 1.84d);
    }

    @Test
    public void testApplesSoupBread5Days() {
        shoppingBasket.addProduct(APPLE, 3);
        shoppingBasket.addProduct(SOUP, 2);
        shoppingBasket.addProduct(BREAD, 1);

        verifyTotalWithPromotions(LocalDate.now().plusDays(5), 1.97d);
    }

    private void verifyTotalWithPromotions(LocalDate date, double expectedTotal) {
        BigDecimal total = shoppingBasket.calculateTotal(date, GroceriesMain.PROMOTIONS);
        assertEquals(expectedTotal, total.doubleValue(), DOUBLE_ASSERT_DELTA);
    }
}
