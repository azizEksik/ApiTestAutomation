package testData;


import org.json.JSONObject;

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
}
