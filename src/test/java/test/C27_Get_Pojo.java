package test;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.DummyDataPOJO;
import pojos.DummyPOJO;

import static io.restassured.RestAssured.given;

public class C27_Get_Pojo  extends DummyBaseUrl {

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

        // 1- Endpoint, varsa request body hazirla

        specDummy.pathParams("pp1","api","pp2","v1","pp3","employee","pp4",3);

        // 2- Expected Data hazirla

        DummyDataPOJO data = new DummyDataPOJO(3,"Ashton Cox",86000,66,"");

        DummyPOJO expectedData = new DummyPOJO("success",data,"Successfully! Record has been fetched.");

        // 3- Response'i kaydet

        Response response = given()
                .spec(specDummy)
                .when()
                .get("/{pp1}/{pp2}/{pp3}/{pp4}");

        // 4- Assertion

        DummyPOJO responsePojo = response.as(DummyPOJO.class);

        Assert.assertEquals(expectedData.getStatus(), responsePojo.getStatus());
        Assert.assertEquals(expectedData.getMessage(), responsePojo.getMessage());
        Assert.assertEquals(expectedData.getData().getId(), responsePojo.getData().getId());
        Assert.assertEquals(expectedData.getData().getEmployee_name(), responsePojo.getData().getEmployee_name());
        Assert.assertEquals(expectedData.getData().getEmployee_age(), responsePojo.getData().getEmployee_age());
        Assert.assertEquals(expectedData.getData().getEmployee_salary(), responsePojo.getData().getEmployee_salary());
        Assert.assertEquals(expectedData.getData().getProfile_image(), responsePojo.getData().getProfile_image());

    }
}
