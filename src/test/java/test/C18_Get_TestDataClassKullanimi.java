package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlace;

import static io.restassured.RestAssured.given;

public class C18_Get_TestDataClassKullanimi extends JsonPlaceHolderBaseUrl {

    /*
        https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request
        yolladigimizda donen response’in status kodunun 200 ve response body’sinin
        asagida verilen ile ayni oldugunutest ediniz

        Response body :
            {
            "userId": 3,
            "id": 22,
            "title": "dolor sint quo a velit explicabo quia nam",
            "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
                    um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
            }
     */

    @Test
    public void get01(){

        // 1- Endpoint, varsa request body hazirla

        specJsonPlace.pathParams("pp1","posts","pp2",22);

        // 2- expected Data hazirla

        TestDataJsonPlace testDataJsonPlace = new TestDataJsonPlace(); // oop ile farkli calsstan obje olsuturduk;

        JSONObject expectedData = testDataJsonPlace.expectedBodyOlusturJSON(); // önceden olusutrdugumuz  body'i cagirip kaydettik

        // 3- Response'i kaydet

        Response response = given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");

        // 4- Assertion

        JsonPath responseJp = response.jsonPath();

        Assert.assertEquals(testDataJsonPlace.basariliStatusCode, response.getStatusCode());

        Assert.assertEquals(responseJp.get("userId"),expectedData.get("userId"));
        Assert.assertEquals(responseJp.get("id"),expectedData.get("id"));
        Assert.assertEquals(responseJp.get("title"),expectedData.get("title"));
        Assert.assertEquals(responseJp.get("body"),expectedData.get("body"));



    }

}
