import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class MainStreams {
    public static void main(String[] args) {
        int[] array = {9,8};
        minValue(array);
    }

    static int minValue(int[] values) {
        AtomicInteger res = new AtomicInteger();
        Arrays.stream(values).distinct().sorted().forEach((s)-> {
            res.set(10 * res.get() + s);
        });

        return res.get();
    }
}
