import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainStreams {
    public static void main(String[] args) {
        int[] array = {1,2,3,3,2,3};
        System.out.println(minValue(array));
    }

    static int minValue(int[] values) {
       return Arrays.stream(values).distinct().sorted().reduce(0, (acc, x) -> (10 * acc + x));
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        integers.stream()
        return new ArrayList<>();
    }
}
