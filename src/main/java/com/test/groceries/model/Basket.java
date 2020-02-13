package com.test.groceries.model;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    private final Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        Integer existingQuantity = products.get(product);
        if (existingQuantity == null) {
            existingQuantity = 0;
        }

        existingQuantity += quantity;
        products.put(product, quantity);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
