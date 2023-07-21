package testData;


import org.json.JSONObject;

import java.util.HashMap;

public class TestDataDummy {

    public int expectedStatuesCode = 200;
    public String expectedContentType = "application/json";

    public JSONObject expectedBodyOlusturJSON(){

        JSONObject data = new JSONObject();

        data.put("id",3);
        data.put("employee_name","Ashton Cox");
        data.put("employee_salary",86000);
        data.put("employee_age",66);
        data.put("profile_image","");

        JSONObject expectedData = new JSONObject();

        expectedData.put("status","success");
        expectedData.put("data",data);
        expectedData.put("message","Successfully! Record has been fetched.");

        return expectedData;

    }

    public HashMap dataBodyOlusturMap(){

        HashMap<String,Object> data = new HashMap<>();

        data.put("id",3.0);
        data.put("employee_name","Ashton Cox");
        data.put("employee_salary",86000.0);
        data.put("employee_age",66.0);
        data.put("profile_image","");

        return data;

    }
    public HashMap expectedBodyOlusturMap(){


        HashMap<String,Object> expectedData = new HashMap<>();

        expectedData.put("status","success");
        expectedData.put("data",dataBodyOlusturMap());
        expectedData.put("message","Successfully! Record has been fetched.");

        return expectedData;

    }
}
