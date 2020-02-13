package com.test.groceries.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class SoupAndBreadPromotion extends Promotion {
    public static final BigDecimal BREAD_HALF_PRICE = Product.BREAD.getPrice().divide(BigDecimal.valueOf(2)).setScale(2);

    public SoupAndBreadPromotion(LocalDate startDate, LocalDate endDate) {
        super("Buy 2 tins of soup and get a loaf of bread half price", startDate, endDate);
    }

    @Override
    public BigDecimal calculateDiscount(Map<Product, Integer> basketContent) {
        Integer soupQuantity = basketContent.getOrDefault(Product.SOUP, 0);
        Integer breadQuantity = basketContent.getOrDefault(Product.BREAD, 0);
        int discountQuantity = Math.min(soupQuantity / 2, breadQuantity);

        return BREAD_HALF_PRICE.multiply(BigDecimal.valueOf(discountQuantity));
    }
}
