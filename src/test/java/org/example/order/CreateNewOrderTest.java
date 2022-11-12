package org.example.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.Order;
import org.example.clients.OrderClient;
import org.example.generators.OrderGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateNewOrderTest {
    private OrderClient orderClient;
    private Order order;
    private int expectedStatusCode;


    public CreateNewOrderTest(Order order, int expectedStatusCode) {
        this.order = order;
        this.expectedStatusCode = expectedStatusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {

        return new Object[][]{
                {OrderGenerator.colourBlackAndGray(),201},
                {OrderGenerator.colourBlack(),201},
                {OrderGenerator.colourGray(),201},
                {OrderGenerator.colourNull(),201},
                {OrderGenerator.colourEmpty(),201}
        };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName(" Creation order")
    public void orderCreation() {
        Response response = orderClient.createNewOrder(order);
        response.then().statusCode(expectedStatusCode).and().body("track", notNullValue());
    }
    @After
    public void end (){
        Response response = orderClient.createNewOrder(order);
        Integer track = response.then().extract().path("track");
        orderClient.deleteOrder(order,track);
    }
}