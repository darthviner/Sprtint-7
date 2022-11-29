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


public class CourierCreationApiTest  {
    private int id;
    private CourierClient courierClient;
    private Courier courier;

    @Before
    @Step("Create new courier")
    public void setup(){
        courierClient = new CourierClient();
        courier = CourierGenerator.getUniqueCourier();
    }


    @After
    @Step("Delete created courier")
    public void cleanUp(){
        courierClient.delete(id);
    }
    @Test
    @Description("Successful Courier Creation Test")
    @DisplayName("Successful Courier Creation Test")
    public void successfulCourierCreationApiTest(){
        ValidatableResponse responseCreate = courierClient.create(courier);
        int statusCode = responseCreate.extract().statusCode();
        assertEquals(201,statusCode);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        assertNotEquals(id,null);

    }

    @Test
    @Description("Tests error when trying to create courier which have been already created")
    @DisplayName("Tests error when trying to create courier which have been already created")
    public void errorWhenCourierIsAlreadyCreatedTest(){
        ValidatableResponse responseCreate = courierClient.create(courier);
        int statusCode= responseCreate.extract().statusCode();
        assertEquals(201,statusCode);
        courier.setFirstName("123");
        courier.setPassword("123");
        responseCreate = courierClient.create(courier);
        statusCode = responseCreate.extract().statusCode();
        String message = responseCreate.extract().path("message");
        assertEquals(409,statusCode);
        assertEquals(message,"Этот логин уже используется. Попробуйте другой.");
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        assertNotEquals(id,null);
    }

    @Test
    @Description("Tests error when don't have field Login in courier creation request")
    @DisplayName("Tests error when don't have field Login in courier creation request")
    public void errorWhenDontHaveLoginFieldCourierCreationTest(){
        Courier incorrectCourier = CourierGenerator.getCourierWithEmptyLogin();
        ValidatableResponse responseCreate = courierClient.create(incorrectCourier);
        String message = responseCreate.extract().path("message");
        int statusCode = responseCreate.extract().statusCode();
        assertEquals("Недостаточно данных для создания учетной записи",message);
        assertEquals(statusCode,400);
    }
    @Test
    @Description("Tests error when don't have field Password in courier creation request")
    @DisplayName("Tests error when don't have field Password in courier creation request")
    public void errorWhenDontHavePasswordFieldCourierCreationTest(){
        Courier incorrectCourier = CourierGenerator.getCourierWithEmptyPassword();
        ValidatableResponse responseCreate = courierClient.create(incorrectCourier);
        String message = responseCreate.extract().path("message");
        int statusCode = responseCreate.extract().statusCode();
        assertEquals("Недостаточно данных для создания учетной записи",message);
        assertEquals(400,statusCode);
    }

    @Test
    @Description("Tests error when don't have field FirstName in courier creation request")
    @DisplayName("Tests error when don't have field FirstName in courier creation request")
    public void errorWhenDontHaveFirstNameFieldCourierCreationTest(){
        Courier incorrectCourier = CourierGenerator.getCourierWithEmptyFirstName();
        incorrectCourier.setLogin(null);
        ValidatableResponse responseCreate = courierClient.create(incorrectCourier);
        String message = responseCreate.extract().path("message");
        int statusCode = responseCreate.extract().statusCode();
        assertEquals("Недостаточно данных для создания учетной записи",message);
        assertEquals(400,statusCode);
    }


}
