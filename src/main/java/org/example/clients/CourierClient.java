package org.example.clients;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.Courier;
import org.example.Credentials;


import static io.restassured.RestAssured.given;

public class CourierClient extends Client {
    @Step("Create new courier")
    public Response createNewCourier(Courier courier) {
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }
    @Step("Login courier")
    public Response loginCourier(Credentials courier) {
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
    }
    @Step("delete courier")
    public void deleteCourier(int id){
        given()
                .spec(getSpec())
                .when()
                .delete("api/v1/courier/{jsonID}", id)
                .then().statusCode(200);
    }
}
