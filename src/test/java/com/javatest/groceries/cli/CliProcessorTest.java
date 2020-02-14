package com.javatest.groceries.cli;

import com.javatest.groceries.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.javatest.groceries.GroceriesMain.APPLE;
import static com.javatest.groceries.GroceriesMain.BREAD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CliProcessorTest {
    private CliProcessor cliProcessor;

    @Before
    public void setup() {
        cliProcessor = new CliProcessor();
    }

    @Test
    public void testAddCommand() {
        verifyCommandOutput("add aaa", "Usage: add <productCode> <amount>");
        verifyCommandOutput("add aaa bbb", "Product aaa not found");
        verifyCommandOutput("add apples bbb", "Incorrect quantity bbb");

        verifyCommandOutput("add apples 10", "Added apples to basket, quantity: 10");
        verifyCommandOutput("add apples 20", "Added apples to basket, quantity: 20");
        verifyCommandOutput("add bread 10", "Added bread to basket, quantity: 10");

        Map<Product, Integer> products = cliProcessor.getShoppingBasket().getProducts();
        assertEquals(2, products.size());
        assertEquals(30, products.get(APPLE).intValue());
        assertEquals(10, products.get(BREAD).intValue());
    }

    @Test
    public void testTotalCommand() {
        verifyCommandOutput("add apples 10", "Added apples to basket, quantity: 10");
        verifyCommandOutput("add milk 1", "Added milk to basket, quantity: 1");
        verifyCommandOutput("total", "The total is 2.30");
    }

    @Test
    public void testRemoveCommand() {
        verifyCommandOutput("add apples 20", "Added apples to basket, quantity: 20");
        verifyCommandOutput("add bread 10", "Added bread to basket, quantity: 10");
        assertEquals(2, cliProcessor.getShoppingBasket().getProducts().size());

        verifyCommandOutput("remove apples", "Removed apples from basket");
        assertEquals(1, cliProcessor.getShoppingBasket().getProducts().size());
    }

    @Test
    public void testResetCommand() {
        verifyCommandOutput("add apples 20", "Added apples to basket, quantity: 20");
        verifyCommandOutput("add bread 10", "Added bread to basket, quantity: 10");
        assertEquals(2, cliProcessor.getShoppingBasket().getProducts().size());

        verifyCommandOutput("reset", "Cleared shopping basket");
        assertTrue(cliProcessor.getShoppingBasket().getProducts().isEmpty());
    }

    private void verifyCommandOutput(String userInput, String expectedOutput) {
        String commandOutput = cliProcessor.processUserInput(userInput);
        assertEquals(expectedOutput, commandOutput);
    }
}
