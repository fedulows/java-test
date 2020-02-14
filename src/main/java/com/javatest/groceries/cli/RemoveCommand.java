package com.javatest.groceries.cli;

import com.javatest.groceries.model.Product;
import com.javatest.groceries.model.ShoppingBasket;

public class RemoveCommand extends CliCommand {
    public RemoveCommand() {
        super("remove");
    }

    @Override
    public String process(String[] userInput, ShoppingBasket basket) {
        if (userInput.length < 2) {
            return "Usage: remove <productCode>";
        }

        Product product = findProduct(userInput[1]);
        if (product == null) {
            return productNotFoundMessage(userInput[1]);
        }

        basket.removeProduct(product);
        return String.format("Removed %s from basket", product.getCode());
    }
}
