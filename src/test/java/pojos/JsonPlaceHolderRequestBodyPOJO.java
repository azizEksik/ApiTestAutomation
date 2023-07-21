package pojos;

public class JsonPlaceHolderRequestBodyPOJO {

    // 1- obje içerisindeki tüm key degerlerini class level'da private variable olarak hazirla

    private String title;
    private String body;
    private int userId;
    private int id;

    // 2- Getter - Setter'lari hazirla


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 3- Tüm parametreleri iceren constructor olustur

    public JsonPlaceHolderRequestBodyPOJO(String title, String body, int userId, int id ){

        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
    }

    // 4- parametresiz constructor olustur, ihtiyacımız olabilir diye

    public JsonPlaceHolderRequestBodyPOJO(){
        // default constructor
    }

    // 5- toString() methodu olusutur


    @Override
    public String toString() {
        return "JsonPlaceHolderRequestBodyPOJO{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}
