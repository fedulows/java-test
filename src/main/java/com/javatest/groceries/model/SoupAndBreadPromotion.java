package com.javatest.groceries.model;

import com.javatest.groceries.GroceriesMain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class SoupAndBreadPromotion extends Promotion {
    public static final BigDecimal BREAD_HALF_PRICE = GroceriesMain.BREAD.getPrice().divide(BigDecimal.valueOf(2));

    public SoupAndBreadPromotion(LocalDate startDate, LocalDate endDate) {
        super("Buy 2 tins of soup and get a loaf of bread half price", startDate, endDate);
    }

    @Override
    public BigDecimal calculateDiscount(Map<Product, Integer> basketContent) {
        Integer soupQuantity = basketContent.getOrDefault(GroceriesMain.SOUP, 0);
        Integer breadQuantity = basketContent.getOrDefault(GroceriesMain.BREAD, 0);
        int discountQuantity = Math.min(soupQuantity / 2, breadQuantity);

        return BREAD_HALF_PRICE.multiply(BigDecimal.valueOf(discountQuantity));
    }
}
