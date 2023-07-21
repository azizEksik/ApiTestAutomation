package testData;

import org.json.JSONObject;

import java.util.HashMap;

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

    public HashMap requestBodyOlusturMap(){

        HashMap<String, Object> bookingdates = new HashMap<>();

        bookingdates.put("checkin","2021-06-01");
        bookingdates.put("checkout","2021-06-10");

        HashMap<String,Object> requestBody = new HashMap<>();

        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname","Bulut");
        requestBody.put("totalprice",500.0);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",bookingdates);
        requestBody.put("additionalneeds","wi-fi");

        return requestBody;
    }

    public HashMap expectedBodyOlusturMap(){

        HashMap<String,Object> expectedData = new HashMap<>();

        expectedData.put("bookingid",24.0);
        expectedData.put("booking",requestBodyOlusturMap());

        return expectedData;
    }
}
