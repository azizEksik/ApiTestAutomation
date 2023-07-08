package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Arrays;

public class C08_JSONObjectVerileriCagirma {

    /*
    {
    "firstName": "John",
    "lastName": "doe",
    "age": 26,
    "address": {
        "streetAddress": "naist street",
        "city": "Nara",
        "postalCode": "630-0192"
                },
    "phoneNumbers": [
                    {
                        "type": "iPhone",
                        "number": "0123-4567-8888"
                    },
                    {
                        "type": "home",
                        "number": "0123-4567-8910"
                    }
                    ]
    }
     */

    @Test
    public void jsonPath01(){

        JSONObject isTel = new JSONObject();

        isTel.put("type","iPhone");
        isTel.put("number","0123-4567-8888");

        JSONObject evTel = new JSONObject();

        evTel.put("type","home");
        evTel.put("number","0123-4567-8910");

        JSONArray tel = new JSONArray();

        tel.put(0, isTel);
        tel.put(1, evTel);

        JSONObject adressBilgileri = new JSONObject();

        adressBilgileri.put("streetAddress","naist street");
        adressBilgileri.put("city","Nara");
        adressBilgileri.put("postalCode","630-0192");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("firstName","John");
        jsonObject.put("lastName","doe");
        jsonObject.put("age",26);
        jsonObject.put("address",adressBilgileri);
        jsonObject.put("phoneNumbers",tel);


        System.out.println("isim : " + jsonObject.get("firstName")); // isim : John
        System.out.println("soy isim : " + jsonObject.get("lastName")); // soy isim : doe
        System.out.println("yas  : " + jsonObject.get("age")); // yas  : 26
        System.out.println("sokak adi  : " + jsonObject.getJSONObject("address").get("streetAddress")); // sokak adi  : naist street
        System.out.println("sehir : " + jsonObject.getJSONObject("address").get("city")); // sehir : Nara
        System.out.println("posta kodu : " + jsonObject.getJSONObject("address").get("postalCode")); // posta kodu : 630-0192
        System.out.println("tel no :" + jsonObject
                                                    .getJSONArray("phoneNumbers")
                                                    .getJSONObject(0)
                                                    .get("number")); // tel no :0123-4567-8888
        System.out.println("Tipi : " + jsonObject
                                                    .getJSONArray("phoneNumbers")
                                                    .getJSONObject(0)
                                                    .get("type")); // Tipi : iPhone
        System.out.println("tel no :" + jsonObject
                                                    .getJSONArray("phoneNumbers")
                                                    .getJSONObject(1)
                                                    .get("number")); // tel no :0123-4567-8910
        System.out.println("Tipi : " + jsonObject
                                                    .getJSONArray("phoneNumbers")
                                                    .getJSONObject(1)
                                                    .get("type")); // Tipi : home
    }
}
