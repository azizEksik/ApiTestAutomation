package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C07_Get_BodyTekrarlardanKurtulma {

    /*
        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde
        donen Response’un,
            status code’unun 200,
            ve content type’inin application-json,
            ve response body’sindeki
                "firstname“in, "Eric",
                ve "lastname“in, "Smith",
                ve "totalprice“in, 701,
                ve "depositpaid“in, false,
                ve "additionalneeds“in, ""Breakfast""
        oldugunu test edin

     */

    @Test
    public void get01(){

        // 1- Endpoint, varsa request body olustur

        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2- Expected data olustur

        // 3- Response'i kaydet

        Response response = given().when().get(url);

        response.prettyPrint();

        // 4- Assertion islemlerini gerçekleştir

        response
                .then()
                    .assertThat()
                        .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", Matchers.equalTo("Eric"),
                        "lastname", Matchers.equalTo("Smith"),
                        "totalprice", Matchers.equalTo(701),
                        "depositpaid", Matchers.equalTo(false),
                        "additionalneeds", Matchers.equalTo("Breakfast"));
    }
}
