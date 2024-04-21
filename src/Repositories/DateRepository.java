package Repositories;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateRepository {
    public static String getDateNow() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentDate);
    }
}