package com.test.groceries.model;

import com.test.groceries.GroceriesMain;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PercentPromotionTest {
    private Promotion applesPromotion;

    @Before
    public void setup() {
        applesPromotion = GroceriesMain.APPLES_PROMOTION;
    }

    @Test
    public void testDescription() {
        assertEquals("apples have a 10% discount", applesPromotion.getDescription());
    }

    @Test
    public void testNoDiscount() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(GroceriesMain.MILK, 10);

        double discount = applesPromotion.calculateDiscount(basketContent).doubleValue();
        assertEquals("Expecting no discount", 0, discount, ShoppingBasketTest.DOUBLE_ASSERT_DELTA);
    }

    @Test
    public void testCalculateDiscount() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(GroceriesMain.APPLE, 50);
        basketContent.put(GroceriesMain.MILK, 100);
        basketContent.put(GroceriesMain.BREAD, 7);

        double discount = applesPromotion.calculateDiscount(basketContent).doubleValue();
        assertEquals("Expecting 0.50 discount from 50 apples", 0.50d, discount, ShoppingBasketTest.DOUBLE_ASSERT_DELTA);
    }
}
