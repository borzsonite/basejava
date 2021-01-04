import java.time.LocalDate;
import java.time.Month;

public class MainDate {
    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }
}
