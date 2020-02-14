package com.javatest.groceries.cli;

import com.javatest.groceries.model.ShoppingBasket;

public class ExitCommand extends CliCommand {
    public ExitCommand() {
        super("exit");
    }

    @Override
    public String process(String[] userInput, ShoppingBasket basket) {
        System.exit(0);
        return null;
    }
}
