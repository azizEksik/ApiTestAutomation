package test;

import org.json.JSONObject;
import org.junit.Test;

import java.util.List;

public class C03_JsonObjesiOlusturma {

    /*
        Asagidaki JSON Objesini olusturup
        konsolda yazdirin.
            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":1
            }
     */

    @Test
    public void JsonObject01(){

        JSONObject ilkJsonObject = new JSONObject();

        ilkJsonObject.put("title","Ahmet");
        ilkJsonObject.put("body","Merhaba");
        ilkJsonObject.put("userId",1);

        System.out.println(ilkJsonObject);
    }



    @Test
    public void jsonObjectInner01(){

        /*
        {
            "firstname":"Jim",
            "additionalneeds":"Breakfast",
            "bookingdates":{
                "checkin":"2018-01-01",
                "checkout":"2019-01-01"
            },
            "totalprice":111,
            "depositpaid":true,
            "lastname":"Brown"
        }
     */

        JSONObject jsonObjectInner = new JSONObject();

        jsonObjectInner.put("checkin","2018-01-01");
        jsonObjectInner.put("checkout", "2019-01-01");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("firstname","Jim");
        jsonObject.put("additionalneeds","Breakfast");
        jsonObject.put("bookingdates",jsonObjectInner);
        jsonObject.put("totalprice",111);
        jsonObject.put("depositpaid",true);
        jsonObject.put("lastname","Brown");

        System.out.println(jsonObject);

    }
}
