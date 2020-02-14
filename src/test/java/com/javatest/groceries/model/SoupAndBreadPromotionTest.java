package com.javatest.groceries.model;

import com.javatest.groceries.GroceriesMain;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SoupAndBreadPromotionTest {
    private Promotion promotion;

    @Before
    public void setup() {
        promotion = GroceriesMain.SOUP_AND_BREAD_PROMOTION;
    }

    @Test
    public void testNoSoupAdded() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(GroceriesMain.APPLE, 10);
        basketContent.put(GroceriesMain.BREAD, 1);

        verifyDiscount(basketContent, 0d, "Expecting no discount");
    }

    @Test
    public void testNoBreadAdded() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(GroceriesMain.MILK, 1);
        basketContent.put(GroceriesMain.SOUP, 2);

        verifyDiscount(basketContent, 0d, "Expecting no discount");
    }

    @Test
    public void testNotEnoughSoups() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(GroceriesMain.BREAD, 1);
        basketContent.put(GroceriesMain.SOUP, 1);

        verifyDiscount(basketContent, 0d, "Expecting no discount");
    }

    @Test
    public void testSingleDiscountApplied() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(GroceriesMain.SOUP, 2);
        basketContent.put(GroceriesMain.BREAD, 1);

        verifyDiscount(basketContent, 0.40d, "Expecting bread half price discount");
    }

    @Test
    public void testDiscountAppliedThreeTimes() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(GroceriesMain.SOUP, 6);
        basketContent.put(GroceriesMain.BREAD, 5);

        verifyDiscount(basketContent, 1.20d, "Expecting discount applied 3 times");
    }

    private void verifyDiscount(Map<Product, Integer> basketContent, double expected, String message) {
        BigDecimal discount = promotion.calculateDiscount(basketContent);
        assertEquals(message, expected, discount.doubleValue(), ShoppingBasketTest.DOUBLE_ASSERT_DELTA);
    }
}
