package com.test.groceries.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    public static final Product SOUP = new Product("soup", BigDecimal.valueOf(0.65), Unit.Tin);
    public static final Product BREAD = new Product("bread", BigDecimal.valueOf(0.80), Unit.Loaf);
    public static final Product MILK = new Product("milk", BigDecimal.valueOf(1.30), Unit.Bottle);
    public static final Product APPLE = new Product("apples", BigDecimal.valueOf(0.10), Unit.Single);

    private final String code;
    private final BigDecimal price;
    private final Unit unit;

    public Product(String code, BigDecimal price, Unit unit) {
        this.code = code;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.unit = unit;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Product.class != obj.getClass()) {
            return false;
        }

        Product other = (Product) obj;
        return this.code.equals(other.code);
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }
}
