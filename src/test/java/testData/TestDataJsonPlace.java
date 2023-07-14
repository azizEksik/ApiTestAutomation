package testData;

import org.json.JSONObject;

public class TestDataJsonPlace {


    public int basariliStatusCode = 200;
    public String contentType = "application/json; charset=utf-8";
    public String connectionHeaderDegeri = "keep-alive";

    public JSONObject expectedBodyOlusturJSON(){

        JSONObject expectedData = new JSONObject();

        expectedData.put("userId",3);
        expectedData.put("id",22);
        expectedData.put("title","dolor sint quo a velit explicabo quia nam");
        expectedData.put("body","eos qui et ipsum ipsam suscipit aut\\nsed omnis non odio\\nexpedita earum mollitia molestiae aut atque rem suscipit\\nnam impedit esse");

        return expectedData;
    }

    public JSONObject requestBodyOlusturJSON(){

        JSONObject requestBody = new JSONObject();

        requestBody.put("title","Ahmet");
        requestBody.put("body","Merhaba");
        requestBody.put("userId",10);
        requestBody.put("id",70);

        return requestBody;
    }

}
