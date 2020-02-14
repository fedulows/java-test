package com.test.groceries;

import com.test.groceries.model.PercentPromotion;
import com.test.groceries.model.Product;
import com.test.groceries.model.Promotion;
import com.test.groceries.model.SoupAndBreadPromotion;
import com.test.groceries.model.Unit;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class GroceriesMain {
    public static final Product SOUP = new Product("soup", BigDecimal.valueOf(0.65), Unit.Tin);
    public static final Product BREAD = new Product("bread", BigDecimal.valueOf(0.80), Unit.Loaf);
    public static final Product MILK = new Product("milk", BigDecimal.valueOf(1.30), Unit.Bottle);
    public static final Product APPLE = new Product("apples", BigDecimal.valueOf(0.10), Unit.Single);

    private static final LocalDate NEXT_MONTH = LocalDate.now().plusMonths(1);

    public static final SoupAndBreadPromotion SOUP_AND_BREAD_PROMOTION = new SoupAndBreadPromotion(
            LocalDate.now().minusDays(1),
            LocalDate.now().plusDays(7));


    public static final PercentPromotion APPLES_PROMOTION = new PercentPromotion(
            APPLE,
            BigDecimal.TEN,
            LocalDate.now().plusDays(3),
            NEXT_MONTH.withDayOfMonth(NEXT_MONTH.lengthOfMonth()));

    public static final List<Promotion> PROMOTIONS = Arrays.asList(APPLES_PROMOTION, SOUP_AND_BREAD_PROMOTION);
}
