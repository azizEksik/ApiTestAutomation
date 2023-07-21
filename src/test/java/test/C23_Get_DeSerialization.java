package test;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataDummy;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C23_Get_DeSerialization extends DummyBaseUrl {

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

        // 1- Enpoint, varsa request body hazirla

        specDummy.pathParams("pp1","api","pp2","v1","pp3","employee","pp4",3);

        // 2-ExpectedData hazirla

        TestDataDummy testDataDummy = new TestDataDummy();

        HashMap<String,Object> expectedData = testDataDummy.expectedBodyOlusturMap();

        // 3- Response'i Kaydet

        Response response = given()
                .spec(specDummy)
                .contentType(ContentType.JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}/{pp4}");

        // 4- Assertion

        HashMap<String,Object> responseMap = response.as(HashMap.class);

        Assert.assertEquals(testDataDummy.expectedStatuesCode,response.getStatusCode());
        Assert.assertEquals(testDataDummy.expectedContentType,response.getContentType());

        Assert.assertEquals(expectedData.get("status"),responseMap.get("status"));
        Assert.assertEquals(((Map)(expectedData.get("data"))).get("id"),((Map)(responseMap.get("data"))).get("id"));
        Assert.assertEquals(((Map)(expectedData.get("data"))).get("employee_name"),((Map)(responseMap.get("data"))).get("employee_name"));
        Assert.assertEquals(((Map)(expectedData.get("data"))).get("employee_salary"),((Map)(responseMap.get("data"))).get("employee_salary"));
        Assert.assertEquals(((Map)(expectedData.get("data"))).get("employee_age"),((Map)(responseMap.get("data"))).get("employee_age"));
        Assert.assertEquals(((Map)(expectedData.get("data"))).get("profile_image"),((Map)(responseMap.get("data"))).get("profile_image"));
        Assert.assertEquals(expectedData.get("message"),responseMap.get("message"));


    }
}
