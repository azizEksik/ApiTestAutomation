package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C09_Post_JsonPathIleBodyTesti {

    /*
        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
        request gonderdigimizde
                {
                "firstname" : "Ali",
                "lastname" : “Bak",
                "totalprice" : 500,
                "depositpaid" : false,
                "bookingdates" : {
                                    "checkin" : "2021-06-01",
                                    "checkout" : "2021-06-10"
                                 },
                "additionalneeds" : "wi-fi"
                }
        donen Response’un,
        status code’unun 200,
        ve content type’inin application-json,
        ve response body’sindeki
        "firstname“in,"Ahmet",
        ve "lastname“in, "Bulut",
        ve "totalprice“in,500,
        ve "depositpaid“in,false,
        ve "checkin" tarihinin 2021-06-01
        ve "checkout" tarihinin 2021-06-10
        ve "additionalneeds“in,"wi-fi"
        oldugunu test edin
     */

    @Test
    public void post01(){
        // 1- Endpoint, varsa request body olustur

        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin","2021-06-01");
        bookingdates.put("checkout","2021-06-10");

        JSONObject requestBody = new JSONObject();

        requestBody.put("firstname", "Ali");
        requestBody.put("lastname", "Bak");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", bookingdates);
        requestBody.put("additionalneeds", "wi-fi");

        // 2- Expected data hazirla

        // 3- Response'ı kaydet

        Response response = given()
                                .contentType(ContentType.JSON)
                            .when()
                                .body(requestBody.toString())
                            .post(url);

        response.prettyPrint();

        // 4- Assertion islemlerini yap

        response
                .then()
                    .assertThat()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("booking.firstname", Matchers.equalTo("Ali"),
                               "booking.lastname", Matchers.equalTo("Bak"),
                                "booking.totalprice", Matchers.equalTo(500),
                                "booking.depositpaid", Matchers.equalTo(false),
                                "booking.bookingdates.checkin",Matchers.equalTo("2021-06-01"),
                                "booking.bookingdates.checkout",Matchers.equalTo("2021-06-10"),
                                "booking.additionalneeds",Matchers.equalTo("wi-fi"));
    }
}
