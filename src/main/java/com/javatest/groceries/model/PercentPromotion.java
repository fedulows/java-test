package com.javatest.groceries.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class PercentPromotion extends Promotion {
    private final Product product;
    private final BigDecimal percentage;

    public PercentPromotion(Product product, BigDecimal percetage, LocalDate startDate, LocalDate endDate) {
        super(
                String.format("%s have a %s%% discount", product.getCode(), percetage),
                startDate,
                endDate);

        this.product = product;
        this.percentage = percetage.divide(BigDecimal.valueOf(100));
    }

    @Override
    public BigDecimal calculateDiscount(Map<Product, Integer> basketContent) {
        Integer productQuantity = basketContent.getOrDefault(product, 0);
        BigDecimal productPrice = product.getPrice().multiply(BigDecimal.valueOf(productQuantity));
        return productPrice.multiply(percentage).setScale(2);
    }
}
