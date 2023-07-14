package test;

import baseUrl.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataHerokuapp;

import static io.restassured.RestAssured.given;

public class C21_Post_TestDataKullanimi extends HerokuappBaseUrl {
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
                }

     */

    @Test
    public void post01(){

        // 1- Endpoint, varsa request body hazirla

        specHerokuapp.pathParam("pp1","booking");

        TestDataHerokuapp testDataHerokuapp = new TestDataHerokuapp();

        JSONObject requestBody = testDataHerokuapp.requestBodyOlusturJSON();

        // 2- expected Data hazirla

        JSONObject expectedData = testDataHerokuapp.expectedBodyOlusturJSON();

        // 3- Response'i kaydet

        Response response = given()
                                .spec(specHerokuapp)
                                .contentType(ContentType.JSON)
                            .when()
                                .body(requestBody.toString())
                            .post("/{pp1}");


        // 4- Assertion

        JsonPath responseJp = response.jsonPath();

        Assert.assertEquals(expectedData.getJSONObject("booking").get("firstname"), responseJp.get("booking.firstname"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("lastname"), responseJp.get("booking.lastname"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("totalprice"), responseJp.get("booking.totalprice"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("depositpaid"), responseJp.get("booking.depositpaid"));
        Assert.assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                            responseJp.get("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                responseJp.get("booking.bookingdates.checkout"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"), responseJp.get("booking.additionalneeds"));


    }
}
