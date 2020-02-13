package com.test.groceries.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class SoupAndBreadPromotion extends Promotion {
    public SoupAndBreadPromotion(LocalDate startDate, LocalDate endDate) {
        super("Buy 2 tins of soup and get a loaf of bread half price", startDate, endDate);
    }

    @Override
    public BigDecimal calculateDiscount(Map<Product, Integer> basketContent) {
        return null;
    }
}
