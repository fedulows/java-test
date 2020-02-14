package com.javatest.groceries.cli;

import com.javatest.groceries.model.Product;
import com.javatest.groceries.model.ShoppingBasket;

class AddCommand extends CliCommand {
    public AddCommand() {
        super("add");
    }

    @Override
    public String process(String[] userInput, ShoppingBasket basket) {
        if (userInput.length < 3) {
            return "Usage: add <productCode> <amount>";
        }

        Product product = findProduct(userInput[1]);
        if (product == null) {
            return productNotFoundMessage(userInput[1]);
        }

        int quantity;
        try {
            quantity = Integer.valueOf(userInput[2]);
        } catch (NumberFormatException e) {
            return "Incorrect quantity " + userInput[2];
        }

        basket.addProduct(product, quantity);
        return String.format("Added %s to basket, quantity: %s", product.getCode(), quantity);
    }
}
