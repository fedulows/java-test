package com.javatest.groceries.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
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
        if (obj == null || Product.class != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        Product other = (Product) obj;
        return this.code.equals(other.code);
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }
}
