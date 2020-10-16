import java.util.Arrays;

/**
 * Created by Alpha on 16.10.2020.
 */
public class Test {
    static int[] arr = new int[10];


    public static void main(String[] args) {
        for(int i = 0; i<5; i++) {
            arr[i] = i;
        }
        delElem(2);
    }

    private static void delElem(int index) {
        System.out.println(Arrays.toString(arr));
        System.arraycopy(arr, index + 1, arr, index, arr.length - 1 - index);
        System.out.println(Arrays.toString(arr));
    }
}
