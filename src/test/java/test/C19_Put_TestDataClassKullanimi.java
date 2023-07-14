package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlace;

import static io.restassured.RestAssured.given;

public class C19_Put_TestDataClassKullanimi extends JsonPlaceHolderBaseUrl {

    /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir
        PUT request yolladigimizda donen response’in
        status kodunun 200, content type’inin “application/json; charset=utf-8”,
        Connection header degerinin “keep-alive”
        ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

            Expected Data :
                {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 10,
                "id": 70
                }

            Request Body
                {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 10,
                "id": 70
                }
     */

    @Test
    public void put01(){

        // 1- Endpoint, varsa request body hazirla

        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlace testDataJsonPlace = new TestDataJsonPlace();

        JSONObject requestBody =  testDataJsonPlace.requestBodyOlusturJSON();

        // 2- expected Data hazirla

        JSONObject expectedData =  testDataJsonPlace.requestBodyOlusturJSON(); // verilin expectedData ayni oldugu icin requestBody kullandik tekrar

        // 3- Response'i kaydet

        Response response = given()
                                .contentType(ContentType.JSON)
                                .spec(specJsonPlace)
                            .when()
                                .body(requestBody.toString())
                            .put("/{pp1}/{pp2}");

        // 4- Assertion

        JsonPath responseJp = response.jsonPath();

        Assert.assertEquals(testDataJsonPlace.basariliStatusCode, response.getStatusCode());
        Assert.assertEquals(testDataJsonPlace.contentType, response.getContentType());
        Assert.assertEquals(testDataJsonPlace.connectionHeaderDegeri, response.getHeader("Connection"));
        Assert.assertEquals(responseJp.get("userId"),expectedData.get("userId"));
        Assert.assertEquals(responseJp.get("id"),expectedData.get("id"));
        Assert.assertEquals(responseJp.get("title"),expectedData.get("title"));
        Assert.assertEquals(responseJp.get("body"),expectedData.get("body"));


    }
}
