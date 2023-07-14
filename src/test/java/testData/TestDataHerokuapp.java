package testData;

import org.json.JSONObject;

public class TestDataHerokuapp {

    public JSONObject requestBodyOlusturJSON(){

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

        return requestBody;
    }

    public JSONObject expectedBodyOlusturJSON(){

        JSONObject expectedData = new JSONObject();

        expectedData.put("bookingid",24);
        expectedData.put("booking",requestBodyOlusturJSON());

        return expectedData;

    }
}
