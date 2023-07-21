package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlace;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class C22_Put_DeSerialization extends JsonPlaceHolderBaseUrl {

    /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir
        PUT request yolladigimizda donen response’in response body’sinin asagida
        verilen ile ayni oldugunu test ediniz

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

        // 1- Enpoint, varsa request body hazirla

        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlace testDataJsonPlace = new TestDataJsonPlace();

        HashMap<String,Object> requestBody = testDataJsonPlace.requestBodyOlusturMap();

        // 2-Expected Data hazirla

        HashMap<String, Object> expectedData = testDataJsonPlace.expectedDataOlusturMap();

        // 3- Response Kaydet

        Response response = given()
                                .contentType(ContentType.JSON)
                                .spec(specJsonPlace)
                            .when()
                                .body(requestBody)
                            .put("/{pp1}/{pp2}");

        // 4- Assertion

        HashMap<String,Object> responseMap = response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("title"), responseMap.get("title"));
        Assert.assertEquals(expectedData.get("body"), responseMap.get("body"));
        Assert.assertEquals(expectedData.get("userId"), responseMap.get("userId"));
        Assert.assertEquals(expectedData.get("id"), responseMap.get("id"));

    }
}
