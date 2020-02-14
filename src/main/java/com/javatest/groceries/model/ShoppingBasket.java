package com.javatest.groceries.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShoppingBasket {
    private final Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product, int quantityToAdd) {
        Integer quantity = products.get(product);
        if (quantity == null) {
            quantity = 0;
        }

        quantity += quantityToAdd;
        products.put(product, quantity);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void clearBasket() {
        products.clear();
    }

    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public BigDecimal calculateTotal(LocalDate calculationDate, Collection<Promotion> promotions) {
        BigDecimal total = products.entrySet().stream()
                .map(ShoppingBasket::calculateProductPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discount = promotions.stream()
                .filter(promotion -> promotion.isActive(calculationDate))
                .map(promotion -> promotion.calculateDiscount(products))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total.subtract(discount).setScale(2);
    }

    private static BigDecimal calculateProductPrice(Map.Entry<Product, Integer> productAndQuantity) {
        BigDecimal productQuantity = BigDecimal.valueOf(productAndQuantity.getValue());
        BigDecimal price = productAndQuantity.getKey().getPrice();

        return price.multiply(productQuantity);
    }
}
