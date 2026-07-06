import java.util.*;

class 두 개의 수로 특정값 만들기 {
    public boolean solution(int[] arr, int target) {
        HashSet<Integer> hs = new HashSet<>();
        for (int num : arr) {
            int complement = target - num;
            if (complement != num && hs.contains(complement)) {
                return true;
            }
            hs.add(num);
        }
        return false;
    }
}
