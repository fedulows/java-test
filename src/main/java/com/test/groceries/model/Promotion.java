package com.test.groceries.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public abstract class Promotion {
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String description, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public abstract BigDecimal calculateDiscount(Map<Product, Integer> basketContent);

    public String getDescription() {
        return description;
    }

    public boolean isActive(LocalDate givenDate) {
        boolean isAfterStartDate = givenDate.isAfter(startDate) || givenDate.isEqual(startDate);
        boolean isBeforeEndDate = givenDate.isBefore(endDate) || givenDate.isEqual(endDate);

        return isAfterStartDate && isBeforeEndDate;
    }
}
