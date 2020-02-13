package com.test.groceries.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PercentPromotionTest {
    private Promotion applesPromotion;

    @Before
    public void setup() {
        applesPromotion = new PercentPromotion(
                Product.APPLE,
                BigDecimal.TEN,
                LocalDate.now().minusDays(3),
                LocalDate.now().plusMonths(1));
    }

    @Test
    public void testDescription() {
        assertEquals("apples have a 10% discount", applesPromotion.getDescription());
    }

    @Test
    public void testNoDiscount() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(Product.MILK, 10);

        assertEquals("Expecting no discount", BigDecimal.ZERO.setScale(2), applesPromotion.calculateDiscount(basketContent));
    }

    @Test
    public void testCalculateDiscount() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(Product.APPLE, 50);
        basketContent.put(Product.MILK, 100);
        basketContent.put(Product.BREAD, 7);

        assertEquals("Expecting 0.50 discount from 50 apples", BigDecimal.valueOf(0.50).setScale(2),
                applesPromotion.calculateDiscount(basketContent));
    }
}
