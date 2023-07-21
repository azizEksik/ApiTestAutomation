package test;

import baseUrl.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPOJO;
import pojos.BookingPOJO;
import pojos.HerokuappExpectedBodyPOJO;

import static io.restassured.RestAssured.given;

public class C26_Post_Pojo extends HerokuappBaseUrl {

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

        // 1 - Endpoint, varsa requestbody olustur

        specHerokuapp.pathParam("pp1","booking");

        BookingDatesPOJO bookingdates = new BookingDatesPOJO("2021-06-01","2021-06-10");

        BookingPOJO requestBody = new BookingPOJO("Ahmet","Bulut",500,false, bookingdates,"wi-fi");


        // 2- expected data hazirla

        HerokuappExpectedBodyPOJO expectedData = new HerokuappExpectedBodyPOJO(24,requestBody);

        // 3- response'i kaydet

        Response response = given()
                                .spec(specHerokuapp)
                                .contentType(ContentType.JSON)
                            .when()
                                .body(requestBody)
                            .post("/{pp1}");

        // 4- Assertion

        HerokuappExpectedBodyPOJO responsePojo = response.as(HerokuappExpectedBodyPOJO.class);

        Assert.assertEquals(expectedData.getBooking().getFirstname(), responsePojo.getBooking().getFirstname());
        Assert.assertEquals(expectedData.getBooking().getLastname(), responsePojo.getBooking().getLastname());
        Assert.assertEquals(expectedData.getBooking().getTotalprice(), responsePojo.getBooking().getTotalprice());
        Assert.assertEquals(expectedData.getBooking().isDepositpaid(), responsePojo.getBooking().isDepositpaid());

        Assert.assertEquals(expectedData.getBooking().getBookingdates().getCheckin(),
                            responsePojo.getBooking().getBookingdates().getCheckin());

        Assert.assertEquals(expectedData.getBooking().getBookingdates().getCheckout(),
                            responsePojo.getBooking().getBookingdates().getCheckout());

        Assert.assertEquals(expectedData.getBooking().getAdditionalneeds(),responsePojo.getBooking().getAdditionalneeds());


    }
}
