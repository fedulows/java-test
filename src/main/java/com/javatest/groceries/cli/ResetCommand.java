package com.javatest.groceries.cli;

import com.javatest.groceries.model.ShoppingBasket;

public class ResetCommand extends CliCommand {
    public ResetCommand() {
        super("reset");
    }

    @Override
    public String process(String[] userInput, ShoppingBasket basket) {
        basket.clearBasket();
        return "Cleared shopping basket";
    }
}
