package test;

import baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataDummy;

import static io.restassured.RestAssured.given;

public class C20_Get_TestDataKullanimi extends DummyBaseUrl {

    /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
        gonderdigimizde donen response’un status code’unun 200, content Type’inin
        application/json ve body’sinin asagidaki gibi oldugunu test edin.

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

        // 1- Endpoint, varsa request body hazirla

        specDummy.pathParams("pp1","api","pp2","v1","pp3","employee","pp4",3);

        // 2- expected Data hazirla

        TestDataDummy testDataDummy = new TestDataDummy();

        JSONObject expectedData = testDataDummy.expectedBodyOlusturJSON();

        // 3- Response'i kaydet

        Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}/{pp3}/{pp4}");

        response.prettyPrint();

        // 4- Assertion

        JsonPath responseJp = response.jsonPath();

        Assert.assertEquals(expectedData.get("status"), responseJp.get("status"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("id"), responseJp.get("data.id"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("employee_name"), responseJp.get("data.employee_name"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("employee_salary"), responseJp.get("data.employee_salary"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("employee_age"), responseJp.get("data.employee_age"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("profile_image"), responseJp.get("data.profile_image"));
        Assert.assertEquals(expectedData.get("message"), responseJp.get("message"));
        Assert.assertEquals(testDataDummy.expectedStatuesCode, response.getStatusCode());
        Assert.assertEquals(testDataDummy.expectedContentType, response.getContentType());

    }
}
