package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // bu notasyon getter, setter ve toString() method'larini olusturuyor
@NoArgsConstructor // parametresiz construcktor olusturuyor
@AllArgsConstructor // tüm argümanları içeren constructor olusturuyor
public class BookingDatesPOJO {

    /*
                                        {
                                        "checkin": "2021-06-01",
                                        "checkout": "2021-06-10"
                                        },
     */

    private String checkin;
    private String checkout;

}
