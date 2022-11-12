package org.example.clients;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.Order;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {
    @Step("Create new order")
    public Response createNewOrder(Order order ){
        Response response =
                given()
                        .spec(getSpec())
                        .body(order)
                        .when()
                        .post("/api/v1/orders");
        return response;
    }
    @ Step("Delete order")
    public void deleteOrder(Order order, int track){
        Response response =
                given()
                        .spec(getSpec())
                        .body(order)
                        .when()
                        .put("/api/v1/orders/cancel?track={jsonTrack}",track);
        response. then().statusCode(200);

    }
    @ Step("Order list")
    public Response orderList(){
        return given()
                .spec(getSpec())
                .when()
                .get("/api/v1/orders");

    }
}
