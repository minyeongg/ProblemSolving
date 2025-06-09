/* 순열 함수 구하는 방법인데 이 방법은 사용하지 않았고 일단 기록만 해둔거임 */


import java.io.*;
import java.util.*;

public class VectorMatching {
    static class Point {
        long x, y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    // next_permutation 구현
    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if (i <= 0) return false;

        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1]) j--;

        int tmp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = tmp;

        j = arr.length - 1;
        while (i < j) {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            Point[] points = new Point[N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long x = Long.parseLong(st.nextToken());
                long y = Long.parseLong(st.nextToken());
                points[i] = new Point(x, y);
            }

            int[] perm = new int[N];
            for (int i = N / 2; i < N; i++) perm[i] = 1;

            long min = Long.MAX_VALUE;

            do {
                long sx = 0, sy = 0;
                for (int i = 0; i < N; i++) {
                    if (perm[i] == 1) {
                        sx += points[i].x;
                        sy += points[i].y;
                    } else {
                        sx -= points[i].x;
                        sy -= points[i].y;
                    }
                }
                long dist2 = sx * sx + sy * sy;
                min = Math.min(min, dist2);
            } while (nextPermutation(perm));

            System.out.printf("%.6f\n", Math.sqrt(min));
        }
    }
}
