import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStreams {
    public static void main(String[] args) {

        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 3, 2, 3, 4, 5, 6, 1)));
    }

    static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (acc, x) -> (10 * acc + x));
    }


    static List<Integer> oddOrEven(List<Integer> integers) {

        Integer sum = integers.stream().reduce(0, Integer::sum);
        return sum % 2 == 0 ? integers.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList()) : integers.stream()
                .filter(x -> x % 2 != 0)
                .collect(Collectors.toList());

    }
}
