package com.javatest.groceries.cli;

import com.javatest.groceries.GroceriesMain;
import com.javatest.groceries.model.Product;
import com.javatest.groceries.model.ShoppingBasket;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class CliCommand {
    public static final Map<String, CliCommand> COMMAND_REGISTRY = new LinkedHashMap<>();
    private static void addCommand(CliCommand command) {
        COMMAND_REGISTRY.put(command.name, command);
    }
    static {
        addCommand(new AddCommand());
        addCommand(new RemoveCommand());
        addCommand(new TotalCommand());
        addCommand(new ResetCommand());
        addCommand(new ExitCommand());
    }

    private final String name;

    public CliCommand(String name) {
        this.name = name;
    }

    public static CliCommand findCommand(String userInput) {
        return COMMAND_REGISTRY.get(userInput.toLowerCase().trim());
    }

    public abstract String process(String[] userInput, ShoppingBasket basket);

    protected Product findProduct(String productCode) {
        return GroceriesMain.PRODUCT_REGISTRY.get(productCode.toLowerCase());
    }

    static String productNotFoundMessage(String s) {
        return "Product " + s + " not found";
    }
}
