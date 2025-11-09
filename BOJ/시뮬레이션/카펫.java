import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int totalSize = brown + yellow;
        int sqrt = (int) Math.sqrt(totalSize);
        for (int vertical = 3; vertical <= sqrt; vertical++) {
            if (totalSize % vertical == 0) {
                int horizontal = totalSize / vertical;
                if (brown == (horizontal + vertical - 2) * 2) {
                    return new int[]{horizontal, vertical};
                }
            }
        }
        return new int[]{};
    }
}
