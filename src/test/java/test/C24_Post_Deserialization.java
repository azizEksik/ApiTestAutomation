package test;

import baseUrl.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataHerokuapp;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C24_Post_Deserialization extends HerokuappBaseUrl {

    /*
        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
        request gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

                Response Body // expected data
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

        HashMap<String,Object> requestBody = testDataHerokuapp.requestBodyOlusturMap();

        // 2- Expected Data hazirla

        HashMap<String,Object> expectedData = testDataHerokuapp.expectedBodyOlusturMap();

        // Response'i kaydet

        Response response = given()
                                .spec(specHerokuapp)
                                .contentType(ContentType.JSON)
                            .when()
                                .body(requestBody)
                            .post("/{pp1}");

        // 4- Assertion

        HashMap<String,Object> responseMap = response.as(HashMap.class);

        Assert.assertEquals(((Map)(expectedData.get("booking"))).get("firstname"),
                            ((Map)(responseMap.get("booking"))).get("firstname"));

        Assert.assertEquals(((Map)(expectedData.get("booking"))).get("lastname"),
                            ((Map)(responseMap.get("booking"))).get("lastname"));

        Assert.assertEquals(((Map)(expectedData.get("booking"))).get("totalprice"),
                            ((Map)(responseMap.get("booking"))).get("totalprice"));

        Assert.assertEquals(((Map)(expectedData.get("booking"))).get("depositpaid"),
                            ((Map)(responseMap.get("booking"))).get("depositpaid"));

        Assert.assertEquals(((Map)(((Map)(expectedData.get("booking"))).get("bookingdates"))).get("checkin"),
                            ((Map)(((Map)(responseMap.get("booking"))).get("bookingdates"))).get("checkin"));

        Assert.assertEquals(((Map)(((Map)(expectedData.get("booking"))).get("bookingdates"))).get("checkout"),
                            ((Map)(((Map)(responseMap.get("booking"))).get("bookingdates"))).get("checkout"));

        Assert.assertEquals(((Map)(expectedData.get("booking"))).get("additionalneeds"),
                            ((Map)(responseMap.get("booking"))).get("additionalneeds"));
    }
}
