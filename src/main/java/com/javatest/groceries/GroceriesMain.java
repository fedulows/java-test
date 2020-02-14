package com.javatest.groceries;

import com.javatest.groceries.cli.CliProcessor;
import com.javatest.groceries.model.PercentPromotion;
import com.javatest.groceries.model.Product;
import com.javatest.groceries.model.Promotion;
import com.javatest.groceries.model.SoupAndBreadPromotion;
import com.javatest.groceries.model.Unit;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GroceriesMain {
    public static final Product SOUP = new Product("soup", BigDecimal.valueOf(0.65), Unit.Tin);
    public static final Product BREAD = new Product("bread", BigDecimal.valueOf(0.80), Unit.Loaf);
    public static final Product MILK = new Product("milk", BigDecimal.valueOf(1.30), Unit.Bottle);
    public static final Product APPLE = new Product("apples", BigDecimal.valueOf(0.10), Unit.Single);

    public static final Map<String, Product> PRODUCT_REGISTRY = new LinkedHashMap<>();
    static {
        PRODUCT_REGISTRY.put(SOUP.getCode(), SOUP);
        PRODUCT_REGISTRY.put(BREAD.getCode(), BREAD);
        PRODUCT_REGISTRY.put(MILK.getCode(), MILK);
        PRODUCT_REGISTRY.put(APPLE.getCode(), APPLE);
    }

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


    public static void main(String[] args) {
        System.out.println("Welcome to Henry’s Grocery shop");

        CliProcessor cliProcessor = new CliProcessor();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(cliProcessor.getBasketContentText());
            System.out.println(CliProcessor.USAGE_TEXT);
            String line = scanner.nextLine();

            String output = cliProcessor.processUserInput(line);
            System.out.println(output);
        }
    }
}
