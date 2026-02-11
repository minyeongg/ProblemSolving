import java.io.*;
import java.util.*;

class 배열제어하기 {
    public static int[] solution(int[] arr) {
        Set<Integer> set = new TreeSet<>(Collections.reverseOrder());
        for (int num : arr) {
            set.add(num);
        }

        int[] result = new int[set.size()];
        int index = 0;
        for (int num : set) {
            result[index++] = num;
        }
        return result;
    }
}
