import java.util.*;
class Solution {
    static int max;
    static int[] apeach;
    static int[] answer;
    static int getScore(int[] ryan) {
        int score = 0;
        for (int i = 0; i <= 10; i++) {
            if (ryan[i] + apeach[i] > 0) {
                score += apeach[i] < ryan[i] ? (10 - i) : -(10 - i);
            }
        }
        return score;
    }
    static void calculateDiff(int[] ryan) {
        int score = getScore(ryan);
        if (max < score) {
            max = score;
            answer = ryan.clone();
        }
        else if (max > 0 && max == score) {
            for (int i = 10; i >= 0; i--) {
                if (answer[i] != ryan[i]) {
                    if (answer[i] < ryan[i]) {
                        answer = ryan.clone();
                    }
                    break;
                }
            }
        }
    }
    static void backtrack(int n, int idx, int[] ryan) {
        if (n == 0) {
            calculateDiff(ryan);
            return;
        }
        for (int i = idx; i <= 10; i++) {
            int cnt = Math.min(n, apeach[i] + 1);
            ryan[i] = cnt;
            backtrack(n - cnt, i + 1, ryan);
            ryan[i] = 0;
        }
    }
    public static int[] solution(int n, int[] info) {
        apeach = info;
        backtrack(n, 0, new int[11]);
        return max == 0 ? new int[]{-1} : answer;
    }
}

