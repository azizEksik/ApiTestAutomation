package test;

import baseUrl.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C17_BaseUrlHerokuappQueryParam extends HerokuappBaseUrl {

    /*
            Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin

            1-  https://restful-booker.herokuapp.com/booking endpointine bir GET request
                gonderdigimizde donen response’un status code’unun 200 oldugunu ve Response’ta
                12 booking oldugunu test edin

            2-  https://restful-booker.herokuapp.com/booking endpointine gerekli Query
                parametrelerini yazarak “firstname” degeri “Eric” olan rezervasyon oldugunu test
                edecek bir GET request gonderdigimizde, donen response’un status code’unun 200
                oldugunu ve “Eric” ismine sahip en az bir booking oldugunu test edin

            3-  https://restful-booker.herokuapp.com/booking endpointine gerekli Query
                parametrelerini yazarak “firstname” degeri “Jim” ve “lastname” degeri “Jackson”
                olan rezervasyon oldugunu test edecek bir GET request gonderdigimizde, donen
                response’un status code’unun 200 oldugunu ve “Jim Jackson” ismine sahip en az bir
                booking oldugunu test edin

     */

    @Test
    public void get01(){

        /*
            1-  https://restful-booker.herokuapp.com/booking endpointine bir GET request
                gonderdigimizde donen response’un status code’unun 200 oldugunu ve Response’ta
                12 booking oldugunu test edin
         */

        // 1- Endpoint olustur

        specHerokuapp.pathParam("pp1","booking");

        // 2- expected Data hazirla

        // 3- Response'i kaydet

        Response response = given().spec(specHerokuapp).when().get("/{pp1}");

        // 4- Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", Matchers.hasSize(12));
    }

    @Test
    public void get02(){

        /*
            2-  https://restful-booker.herokuapp.com/booking endpointine gerekli Query
                parametrelerini yazarak “firstname” degeri “Eric” olan rezervasyon oldugunu test
                edecek bir GET request gonderdigimizde, donen response’un status code’unun 200
                oldugunu ve “Eric” ismine sahip en az bir booking oldugunu test edin
         */

        // 1- Endpoint olustur

        specHerokuapp.pathParam("pp1","booking").queryParam("firstname","Eric");

        // 2- expected Data hazirla

        // 3- Response'i kaydet

        Response response = given().spec(specHerokuapp).when().get("/{pp1}");



        // 4- Assertion

        response.then()
                .assertThat()
                .statusCode(200);

        String a = response.body().asString();

        Assert.assertTrue(a.length()>=17);

    }

    @Test
    public void get03(){

        /*
            3-  https://restful-booker.herokuapp.com/booking endpointine gerekli Query
                parametrelerini yazarak “firstname” degeri “Jim” ve “lastname” degeri “Jackson”
                olan rezervasyon oldugunu test edecek bir GET request gonderdigimizde, donen
                response’un status code’unun 200 oldugunu ve “Jim Jackson” ismine sahip en az bir
                booking oldugunu test edin
         */

        // 1- Endpoint olustur

        specHerokuapp.pathParam("pp1", "booking").queryParams("firstname","Jim","lastname","Jackson");

        // 2- expected Data hazirla

        // 3- Response'i kaydet

        Response response = given().spec(specHerokuapp).when().get("/{pp1}");

        // 4- Assertion

        response.then()
                .assertThat()
                .statusCode(200);

        String a = response.asString();

        Assert.assertTrue(a.length()>=3);

    }
}
