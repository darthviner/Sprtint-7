import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import models.Courier;
import models.Credentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.CourierClient;
import utils.CourierGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CourierLoginApiTest {

    private String login;
    private Courier courier;
    private CourierClient courierClient;
    private int id;

    @Before
    @Step("Create courier and login")
    public void setup(){
        courier = CourierGenerator.getUniqueCourier();
        courierClient = new CourierClient();
        ValidatableResponse createResponse = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(Credentials.from(courier));
        id = loginResponse.extract().path("id");
    }
    @After
    @Step("Delete courier")
    public void cleanUp(){
        courierClient.delete(id);
    }
    @Test
    @Description("Successful courier login test")
    @DisplayName("Successful courier login test")
    public void successfulCourierLoginTest(){
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        int statusCode = responseLogin.extract().statusCode();
        id = responseLogin.extract().path("id");
        assertEquals(200,statusCode);
        assertNotEquals(null,id);
    }
    @Test
    @Description("Tests error when don't have login field in courier login request")
    @DisplayName("Tests error when don't have login field in courier login request")
    public void errorWhenLoginRequestDontHaveLoginFieldTest(){
        Credentials credentials = Credentials.from(courier);
        credentials.setLogin("");
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int statusCode = responseLogin.extract().statusCode();
        assertEquals(400,statusCode);

    }
    @Test
    @Description("Tests error when don't have password field in courier login request")
    @DisplayName("Tests error when don't have password field in courier login request")
    public void errorWhenLoginRequestDontHavePasswordFieldTest(){
        Credentials credentials = Credentials.from(courier);
        credentials.setPassword("");
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int statusCode = responseLogin.extract().statusCode();
        assertEquals(400,statusCode);
    }
    @Test
    @Description("Test unsuccessfull login with not created login")
    @DisplayName("Test unsuccessfull login with not created login")
    public void errorWhenLoginDoesntExistTest(){
        Credentials credentials = Credentials.from(courier);
        credentials.setLogin("");
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int statusCode = responseLogin.extract().statusCode();
        assertEquals(400,statusCode);
    }
    @Test
    @Description("Error when incorrect password was in login request")
    @DisplayName("Error when incorrect password was in login request")
    public void errorWhenIncorrectPasswordInLoginRequestTest(){
        Courier incorrectCourier = CourierGenerator.getUniqueCourier();
        Credentials credentials = Credentials.from(courier);
        credentials.setPassword("321123");
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int statusCode = responseLogin.extract().statusCode();
        assertEquals(404,statusCode);
    }



}
