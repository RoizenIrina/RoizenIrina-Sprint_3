package org.example.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.clients.OrderClient;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

public class OrderListTest {
    private OrderClient orderClient;


    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }
    @Test
    @DisplayName("Check order list should be thirty")
    public void checkOrderList_shouldBeThirty() {
        Response response = orderClient.orderList();
        response.then().statusCode(200).and().body("orders", notNullValue());
        response.then().body("orders.id", hasSize(30));
    }
}
