package models;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class OrdersApiTest {
    @Before
    public void setup(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @Description("Get orders without parameters test")
    public  void getOrdersWithoutParametersTest(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .when()
                        .get("/api/v1/orders");
        response.then().assertThat()
                .statusCode(200)
                .and().assertThat()
                .body("orders",notNullValue());
    }

    @Test
    @Description("Get orders return message when courierId is incorrect")
    public void getOrdersWithIncorrectCourierIdParametersTest(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .queryParam("courierId",-1)
                        .when()
                        .get("/api/v1/orders");
        response.then().assertThat()
                .statusCode(404)
                .and().assertThat()
                .body("message",equalTo("Курьер с идентификатором "+-1+" не найден"));
    }

}
