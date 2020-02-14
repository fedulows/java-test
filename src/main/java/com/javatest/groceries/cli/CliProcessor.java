package com.javatest.groceries.cli;

import com.javatest.groceries.GroceriesMain;
import com.javatest.groceries.model.Product;
import com.javatest.groceries.model.ShoppingBasket;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class CliProcessor {
    public static final String USER_INPUT_SEPARATOR = " ";
    public static final String USAGE_TEXT =
            "Please enter one of the commands: " + CliCommand.COMMAND_REGISTRY.keySet() +
            ", available products: " + GroceriesMain.PRODUCT_REGISTRY.keySet();

    private final ShoppingBasket shoppingBasket = new ShoppingBasket();

    public String getBasketContentText() {
        Map<Product, Integer> products = shoppingBasket.getProducts();

        StringBuilder result = new StringBuilder();
        result.append("Shopping basket contains ").append(products.size()).append(" items:");
        products.entrySet().forEach(
                entry -> result.append("\n").append(entry.getKey().getCode()).append(" - ").append(entry.getValue()));

        return result.toString();
    }

    public String processUserInput(String input) {
        if (StringUtils.isBlank(input)) {
            return "";
        }

        String[] userInputParams = input.split(USER_INPUT_SEPARATOR);
        CliCommand command = CliCommand.findCommand(userInputParams[0]);
        if (command == null) {
            return "Unknown command " + userInputParams[0];
        }

        String commandOutput = command.process(userInputParams, shoppingBasket);
        return commandOutput;
    }

    public ShoppingBasket getShoppingBasket() {
        return shoppingBasket;
    }
}
