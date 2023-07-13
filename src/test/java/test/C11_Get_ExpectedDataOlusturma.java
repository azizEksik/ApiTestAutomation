package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C11_Get_ExpectedDataOlusturma {

    /*
        https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request
        yolladigimizda donen response body’sinin asagida verilen ile ayni oldugunu test
        ediniz
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

        String url = "https://jsonplaceholder.typicode.com/posts/22";

        // 2- Expected data hazirla

        JSONObject expectedDataJson = new JSONObject();

        expectedDataJson.put("userId",3);
        expectedDataJson.put("id",22);
        expectedDataJson.put("title","dolor sint quo a velit explicabo quia nam");
        expectedDataJson.put("body","eos qui et ipsum ipsam suscipit aut\\sed omnis non odio\\nexpedita earum mollitia molestiae aut atque rem suscipit\\nnam impedit esse");

        // 3- Response'i kaydet

        Response response = given().when().get(url);

        JsonPath responseJP = response.jsonPath(); // response'i jsonpath formatina dönüstürdük ki body'den deger cagirabilelim

        // response.prettyPeek(); // response ile ilgili tum degerleri donduren method (header vb.)

        // 4- Assertion

        Assert.assertEquals(expectedDataJson.get("userId"),responseJP.get("userId"));
        Assert.assertEquals(expectedDataJson.get("id"),responseJP.get("id"));
        Assert.assertEquals(expectedDataJson.get("title"),responseJP.get("title"));
        Assert.assertEquals(expectedDataJson.get("body"),responseJP.get("body"));
    }
}
