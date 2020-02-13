package com.test.groceries.model;

import java.math.BigDecimal;
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
        BigDecimal result = BigDecimal.ZERO;

        Integer productQuantity = basketContent.get(product);
        if (productQuantity != null) {
            BigDecimal productPrice = product.getPrice().multiply(BigDecimal.valueOf(productQuantity));
            result = productPrice.multiply(percentage).setScale(2);
        }

        return result;
    }
}
