package org.example.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Courier;
import org.example.Credentials;
import org.example.clients.CourierClient;
import org.example.generators.CourierGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {
    private Courier courier;
    private Credentials credentials;
    private CourierClient clientCourier;
    @Before
    public void setUp() {
        clientCourier= new CourierClient();
        courier=CourierGenerator.getDefault();
        clientCourier.createNewCourier(courier);

    }
    @Test
    @DisplayName("login courier")
    public void loginCourier() {
        Response response = clientCourier.loginCourier(Credentials.from(courier));
        response.then().statusCode(200).and().body("id", notNullValue());
    }
    @Test
    @DisplayName("authorization with  invalid password")
    public  void authorizationWithInvalidPassword(){
        Courier courier =  CourierGenerator.authorization();
        courier.setPassword("123456dfasd");
        Response response = clientCourier.loginCourier(Credentials.from(courier));
        response.then().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }
    @Test
    @DisplayName("authorization with  invalid login")
    public void  authorizationWithInvalidLogin(){
        Courier courier =  CourierGenerator.authorization();
        courier.setLogin("Larisa106");
        Response response = clientCourier.loginCourier(Credentials.from(courier));
        response.then().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }

    @ Test
    @DisplayName("authorization without login")
    public void authorizationWithoutLogin(){
        Courier courier =  CourierGenerator.authorization();
        courier.setLogin(null);
        Response response = clientCourier.loginCourier(Credentials.from(courier));
        response.then().statusCode(400).and().body("message",equalTo("Недостаточно данных для входа"));
    }
    @ Test
    @DisplayName("authorization with empty password")
    public void authorizationWithEmptyPassword(){
        Courier courier =  CourierGenerator.authorization();
        courier.setPassword("");
        Response response = clientCourier.loginCourier(Credentials.from(courier));
        response.then().statusCode(400).and().body("message",equalTo("Недостаточно данных для входа"));
    }
    @ Test
    @DisplayName("authorization with empty login")
    public void authorizationEmptyLogin(){
        Courier courier =  CourierGenerator.authorization();
        courier.setLogin("");
        Response response = clientCourier.loginCourier(Credentials.from(courier));
        response.then().statusCode(400).and().body("message",equalTo("Недостаточно данных для входа"));
    }

    @ Test
    @DisplayName("authorization without password")
    public void authorizationWithoutPassword(){
        Courier courier =  CourierGenerator.authorization();
        courier.setPassword(null);
        Response response = clientCourier.loginCourier(Credentials.from(courier));
        response.then().statusCode(400).and().body("message",equalTo("Недостаточно данных для входа"));
    }

    @After
    public void endCourier(){
        Response response = clientCourier.loginCourier(Credentials.from(courier));
        Integer id = response.then().extract().path("id");
        if (id!=0){clientCourier.deleteCourier(id);}

    }

}
