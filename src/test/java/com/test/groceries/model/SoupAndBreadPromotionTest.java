package com.test.groceries.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SoupAndBreadPromotionTest {
    private static final BigDecimal BREAD_HALF_PRICE = Product.BREAD.getPrice().divide(BigDecimal.valueOf(2)).setScale(2);
    private Promotion soupAndBreadPromotion;

    @Before
    public void setup() {
        soupAndBreadPromotion = new SoupAndBreadPromotion(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusMonths(7));
    }

    @Test
    public void testNoSoupAdded() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(Product.APPLE, 10);
        basketContent.put(Product.BREAD, 1);

        assertEquals("Expecting no discount", BigDecimal.ZERO.setScale(2), soupAndBreadPromotion.calculateDiscount(basketContent));
    }

    @Test
    public void testNoBreadAdded() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(Product.MILK, 1);
        basketContent.put(Product.SOUP, 2);

        assertEquals("Expecting no discount", BigDecimal.ZERO.setScale(2), soupAndBreadPromotion.calculateDiscount(basketContent));
    }

    @Test
    public void testNotEnoughSoups() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(Product.BREAD, 1);
        basketContent.put(Product.SOUP, 1);

        assertEquals("Expecting no discount", BigDecimal.ZERO.setScale(2), soupAndBreadPromotion.calculateDiscount(basketContent));
    }

    @Test
    public void testSingleDiscountApplied() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(Product.SOUP, 2);
        basketContent.put(Product.BREAD, 1);


        assertEquals("Expecting bread half price discount", BREAD_HALF_PRICE,
                soupAndBreadPromotion.calculateDiscount(basketContent));
    }

    @Test
    public void testDiscountAppliedThreeTimes() {
        Map<Product, Integer> basketContent = new HashMap<>();
        basketContent.put(Product.SOUP, 6);
        basketContent.put(Product.BREAD, 5);


        assertEquals("Expecting discount applied 3 times", BREAD_HALF_PRICE.multiply(BigDecimal.valueOf(3)),
                soupAndBreadPromotion.calculateDiscount(basketContent));
    }
}
