package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuappBaseUrl {

    protected RequestSpecification specHerokuapp;

    @Before
    public void setUp(){

        specHerokuapp = new RequestSpecBuilder().setBaseUri("http://restful-booker.herokuapp.com").build();
    }

}
