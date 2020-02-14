package com.javatest.groceries.cli;

import com.javatest.groceries.GroceriesMain;
import com.javatest.groceries.model.ShoppingBasket;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TotalCommand extends CliCommand {
    public TotalCommand() {
        super("total");
    }

    @Override
    public String process(String[] userInput, ShoppingBasket basket) {
        BigDecimal bigDecimal = basket.calculateTotal(LocalDate.now(), GroceriesMain.PROMOTIONS);
        return String.format("The total is %s", bigDecimal);
    }
}
