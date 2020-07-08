package application.library;

import java.time.LocalDate;

public class MyUtil {
    public static boolean isSameDate(LocalDate date1, LocalDate date2) {
        if (date2 == null){
            return true;
        }
        return (date1.getYear() == date2.getYear()
                && date1.getMonth() == date2.getMonth()
                && date1.getDayOfMonth() == date2.getDayOfMonth());
    }
}
