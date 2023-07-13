package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C13_Get_SoftAssertIleExpectedDataTesti {


    /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
        gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

                Response Body
            {
            "status": "success",
            "data": {
                    "id": 3,
                    "employee_name": "Ashton Cox",
                    "employee_salary": 86000,
                    "employee_age": 66,
                    "profile_image": ""
                    },
            "message": "Successfully! Record has been fetched."
            }
     */

    @Test
    public void get01(){

        // 1- Endpoint, varsa request boyd hazirla

        String url = "http://dummy.restapiexample.com/api/v1/employee/3";

        // 2- Expected Data hazirla

        JSONObject data = new JSONObject();

        data.put("id", 3);
        data.put("employee_name", "Ashton Cox");
        data.put("employee_salary", 86000);
        data.put("employee_age", 66);
        data.put("profile_image", "");

        JSONObject expectedData = new JSONObject();

        expectedData.put("status","success");
        expectedData.put("data",data);
        expectedData.put("message","Successfully! Record has been fetched.");

        // 3- Response'i kaydet

        Response response = given().when().get(url);

        JsonPath responseJP = response.jsonPath();

        response.prettyPrint();

        // 4- Assertion islemlerini yap

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseJP.get("status"), expectedData.get("status"));
        softAssert.assertEquals(responseJP.get("data.id"), expectedData.getJSONObject("data").get("id"));
        softAssert.assertEquals(responseJP.get("data.employee_name"), expectedData.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(responseJP.get("data.employee_salary"), expectedData.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(responseJP.get("data.employee_age"), expectedData.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(responseJP.get("data.profile_image"), expectedData.getJSONObject("data").get("profile_image"));
        softAssert.assertEquals(responseJP.get("message"), expectedData.get("message"));

        softAssert.assertAll();
    }

}
