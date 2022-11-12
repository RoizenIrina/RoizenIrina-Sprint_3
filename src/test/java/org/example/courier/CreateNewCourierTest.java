package org.example.courier;
import io.qameta.allure.Step;
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
public class CreateNewCourierTest  {
    private Courier courier;
    private CourierClient courierClient;
    private int statusCode;
    private boolean expected;

    public CreateNewCourierTest(Courier courier, int statusCode, boolean expected) {
        this.courier = courier;
        this.statusCode = statusCode;
        this.expected = expected;
    }

       @Parameterized.Parameters
    public static Object[][] getCourierData(){
        return new Object[][]{
                {CourierGenerator.getDefault(),201,true},
                {CourierGenerator.getInUppercase(),201,true}
        };
    }

    @Before
    public void setUp() {
        courierClient = new CourierClient();

    }
    @Test
    @DisplayName("check post - creating courier")
    public void checkPostCreatingCourier() {
        Response response = courierClient.createNewCourier(courier);
        response.then().assertThat().statusCode(statusCode)
                .and().body("ok", equalTo(expected));

    }

    @Test
    @DisplayName("check post- creating identical couriers")
    public void checkPostCreatingIdenticalCouriers (){

        courierClient.createNewCourier(courier);
        Response response1 =courierClient.createNewCourier(courier);
        response1.then().assertThat().statusCode(409)
                .and()
                .assertThat().body("message",equalTo( "Этот логин уже используется. Попробуйте другой."));
    }



    @After
    public void deleteId(){

        Response response = courierClient.loginCourier(Credentials.from(courier));
        Integer id = response.then().extract().path("id");
        if (id!=0){courierClient.deleteCourier(id);}

    }

}
