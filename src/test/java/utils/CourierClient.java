package utils;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.Courier;
import models.Credentials;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client{
    private static String CREATE_PATH = "/api/v1/courier";
    private static String LOGIN_PATH = "/api/v1/courier/login";
    private static String DELETE_PATH = "/api/v1/courier/";
    @Step("Create courier")
    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(CREATE_PATH).then();
    }
    @Step("Delete courier")
    public ValidatableResponse delete(int id){
        return  given()
                .spec(getSpec())
                .when()
                .delete(DELETE_PATH+id)
                .then();

    }
    @Step("Login courier with credentials")
    public ValidatableResponse login(Credentials credentials){
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(LOGIN_PATH)
                .then();
    }
}
