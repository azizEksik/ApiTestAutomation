package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class C10_Get_ResponseBodyTestiListKullanimi {

    /*
        http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request
        yolladigimizda
        donen Response'in
        status code'unun 200,
        ve content type'inin Aplication.JSON,
        ve response body'sindeki
        employees sayisinin 24
        ve employee'lerden birinin "Ashton Cox"
        ve girilen yaslar icinde 61,21 ve 35 degerinin oldugunu test edin
        test edin.
     */

    @Test
    public void get01(){

        // 1- Endpoint, varsa request body olustur

        String url = "http://dummy.restapiexample.com/api/v1/employees";

        // 2- Expected data olustur

        // 3- Response'i kaydet

        Response response = given().when().get(url);

        // 4- Assertion islemlerini yap

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id", Matchers.hasSize(24), // kaç tane elemanın var oldugunu kontrol eden method
                        "data.employee_name", Matchers.hasItem("Ashton Cox"), //employee_name isimli itemlerin arasında ashton cox degerini arar
                        "data.employee_age", Matchers.hasItems(61,21,35)); // bütün itemlerin arasında istenen itemleri arayan method

    }
}
