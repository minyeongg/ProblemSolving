/* 순열 함수가 나은 경우 
- 뽑는 개수가 입력마다 다름 (r이 변수)
- 대상이 많음 (5개, 10개... for를 그만큼 못 중첩)
- "N과 M" 같은 순열 자체가 문제인 경우
*/


import java.util.*;
import java.lang.*;
import java.io.*;


class 주사위 조합 {
    int l, r;
    int answer = Integer.MIN_VALUE;
    Set<Integer> hs;
    int[] a;
    int[] b;
    int[] c = new int[6];

    void choose(int start, int count) {
        if (count == 6) {
            countValid();
            answer = Math.max(answer, hs.size());
            return;
        }
        for (int i = start; i <= 9; i++) {
            c[count] = i;
            choose(i + 1, count + 1);
        }
    }

    int[] faces(int v) {
        if (v == 6 || v == 9) return new int[] {6, 9};
        return new int[]{v};
    }

    void countValid() {
        int[][] dice = {a, b, c};
        hs = new HashSet<>();
        for (int h = 0; h < 3; h++) {
            for (int t = 0; t < 3; t++) {
                for (int o = 0; o < 3; o++) {
                    if ((h == t) || (h == o) || (t == o)) continue;
                    for (int hi = 0; hi < 6; hi++) {
                        for (int ti = 0; ti < 6; ti++) {
                            for (int oi = 0; oi < 6; oi++) {
                                for (int hv : faces(dice[h][hi])) {
                                    for (int tv : faces(dice[t][ti])) {
                                        for (int ov : faces(dice[o][oi])) {
                                             int num = 100 * hv + 10 * tv + ov;
                                             if (num >= l && num <= r) hs.add(num);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public int solution(int[] a, int[] b, int l, int r) {
        this.l = l;
        this.r = r;

        this.a = a;
        this.b = b;

        choose(0, 0);
        return answer;
    }
}
