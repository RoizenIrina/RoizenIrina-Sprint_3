package org.example.courier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.Courier;
import org.example.Credentials;
import org.example.clients.CourierClient;
import org.example.generators.CourierGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class CreatingACourierWithoutLoginOrPassword {
    private CourierClient courierClient;
    private Courier courier;
    private String message;
    private int statusCode;

    public CreatingACourierWithoutLoginOrPassword(Courier courier, String message, int statusCode) {
        this.courier = courier;
        this.message = message;
        this.statusCode = statusCode;
    }
    @Parameterized.Parameters
    public static Object[][] getCourierData(){
        return new Object[][]{
                {CourierGenerator.getWithoutLogin(),"Недостаточно данных для создания учетной записи",400,},
                {CourierGenerator.getWithoutPassword(),"Недостаточно данных для создания учетной записи",400,},
                {CourierGenerator.getEmptyLogin(),"Недостаточно данных для создания учетной записи",400,},
                {CourierGenerator.getEmptyPassword(),"Недостаточно данных для создания учетной записи",400,},
                {CourierGenerator.getEmptyLoginAndPassword(),"Недостаточно данных для создания учетной записи",400,}
        };
    }

    @Before
    public void setUp() {
        courierClient= new CourierClient();
    }

    @Test
    @DisplayName("check post creating without password or login")
    public void checkPostCreatingWithoutPasswordOrLogin(){
        Response response =courierClient.createNewCourier(courier);
        response.then().assertThat().statusCode(statusCode)
                .and()
                .body("message", equalTo (message));

    }


}
