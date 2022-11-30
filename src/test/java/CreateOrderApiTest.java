import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import models.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderApiTest {
    private String address;
    private String phone;
    private String firstName;
    private String lastName;
    private String comment;
    private String deliveryDate;
    private String[] color;
    private Integer rentTime;
    @Before
    public void setup(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";}

    @Parameterized.Parameters // Пометь метод аннотацией для параметров
    public static Object[][] getTextData() {
        return new Object[][] {
                {"Address","82283591488","firstName","lastName","comment","2020-06-06",new String[2],1},
                {"Владивосток, Юмашева 8б, кв 12","89993334411","Серёжа","Шарапов","Комментарий","2222-06-06",new String[] {"BLACK","GREY"},2},
                {"Kazakhstan, Abai 120, 12","+78889998888","Mambet","Mambetov","Comment !@#","1999-06-06",new String[] {"BLACK"},100},
                {"1","2","3","4","5","3222-06-06",new String[] {"GREY"},999},

        };
    }
    public CreateOrderApiTest(String address,String phone, String firstName,String lastName,String comment,String deliveryDate, String[] color, Integer rentTime){
        this.address = address;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.comment = comment;
        this.deliveryDate = deliveryDate;
        this.color = color;
        this.rentTime = rentTime;

    }

    @Test
    @Description("Successful order creation test")
    @DisplayName("Successful order creation test")
    public void successfulOrderCreationTest(){
        Order order = new Order();
        order.setAddress(address);
        order.setComment(comment);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setDeliveryDate(deliveryDate);
        order.setPhone(phone);
        order.setRentTime(rentTime);
        order.setColor(color);

        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(order)
                        .when()
                        .post("/api/v1/orders");
        response.then().assertThat()
                .statusCode(201)
                .and().assertThat()
                .body("track",notNullValue());
    }
}
