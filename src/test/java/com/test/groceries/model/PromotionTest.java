package com.test.groceries.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PromotionTest {
    private Promotion promotion;

    @Before
    public void setup() {
        promotion = new Promotion("Test promotion", LocalDate.now().minusDays(5), LocalDate.now().plusDays(5)) {
            @Override
            public BigDecimal calculateDiscount(Map<Product, Integer> basketContent) {
                return null;
            }
        };
    }

    @Test
    public void testPromotionActive() {
        assertTrue(promotion.isActive(LocalDate.now()));
        assertTrue(promotion.isActive(LocalDate.now().minusDays(4)));
        assertTrue(promotion.isActive(LocalDate.now().plusDays(5)));

        assertFalse(promotion.isActive(LocalDate.now().minusDays(6)));
        assertFalse(promotion.isActive(LocalDate.now().plusMonths(1)));
    }
}
