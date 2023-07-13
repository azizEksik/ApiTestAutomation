package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {

    /*

        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
        request gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

            Response Body
                    {
                    "bookingid": 24,
                    "booking": {
                                "firstname": "Ahmet",
                                "lastname": "Bulut",
                                "totalprice": 500,
                                "depositpaid": false,
                                "bookingdates": {
                                                "checkin": "2021-06-01",
                                                "checkout": "2021-06-10"
                                                },
                    "additionalneeds": "wi-fi"
                    }
                    }
            Request body
                    {
                    "firstname" : "Ahmet",
                    "lastname" : “Bulut",
                    "totalprice" : 500,
                    "depositpaid" : false,
                    "bookingdates" : {
                                    "checkin" : "2021-06-01",
                                    "checkout" : "2021-06-10"
                                    },
                    "additionalneeds" : "wi-fi"

     */

    @Test
    public void post01(){

        // 1- Endpoint, varsa request body hazirla

        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin","2021-06-01");
        bookingdates.put("checkout","2021-06-10");

        JSONObject requestBody = new JSONObject();

        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname","Bulut");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",bookingdates);
        requestBody.put("additionalneeds","wi-fi");

        // 2- Expected Data hazirla

        JSONObject exceptedBookingdates = new JSONObject();

        exceptedBookingdates.put("checkin","2021-06-01");
        exceptedBookingdates.put("checkout","2021-06-10");

        JSONObject exceptedBooking = new JSONObject();

        exceptedBooking.put("firstname","Ahmet");
        exceptedBooking.put("lastname","Bulut");
        exceptedBooking.put("totalprice",500);
        exceptedBooking.put("depositpaid",false);
        exceptedBooking.put("bookingdates",exceptedBookingdates);
        exceptedBooking.put("additionalneeds","wi-fi");

        JSONObject expectedData = new JSONObject();

        expectedData.put("bookingid",24);
        expectedData.put("booking",exceptedBooking);


        // 3- Respons'i kaydet

        Response response = given()
                                    .contentType(ContentType.JSON)
                            .when()
                                    .body(requestBody.toString())
                            .post(url);

        response.prettyPrint();

        JsonPath responseJP = response.jsonPath();

        // 4- Assertion islemlerini yap

        Assert.assertEquals(expectedData.getJSONObject("booking").get("firstname"), responseJP.get("booking.firstname"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("lastname"), responseJP.get("booking.lastname"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("totalprice"), responseJP.get("booking.totalprice"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("depositpaid"), responseJP.get("booking.depositpaid"));
        Assert.assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates")
                                                        .get("checkin"), responseJP.get("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates")
                                                        .get("checkout"), responseJP.get("booking.bookingdates.checkout"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"), responseJP.get("booking.additionalneeds"));

    }
}
