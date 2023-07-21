package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderRequestBodyPOJO;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C25_Put_PojoClass extends JsonPlaceHolderBaseUrl {

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

        // 1- Endpoint, varsa requestBody hazirla

        specJsonPlace.pathParams("pp1","posts","pp2",70);

        JsonPlaceHolderRequestBodyPOJO requestBody = new JsonPlaceHolderRequestBodyPOJO("Ahmet","Merhaba",10,70);

        // 2- expected Data hazirla

        JsonPlaceHolderRequestBodyPOJO expectedData = new JsonPlaceHolderRequestBodyPOJO("Ahmet","Merhaba",10,70);

        // 3- Response'i kaydet

        Response response = given()
                                .spec(specJsonPlace)
                                .contentType(ContentType.JSON)
                            .when()
                                .body(requestBody)
                            .put("/{pp1}/{pp2}");


        // 4- Assertion

        JsonPlaceHolderRequestBodyPOJO responsePojo = response.as(JsonPlaceHolderRequestBodyPOJO.class);

        assertEquals(expectedData.getTitle(), responsePojo.getTitle());
        assertEquals(expectedData.getBody(), responsePojo.getBody());
        assertEquals(expectedData.getId(), responsePojo.getId());
        assertEquals(expectedData.getUserId(), responsePojo.getUserId());

    }
}
