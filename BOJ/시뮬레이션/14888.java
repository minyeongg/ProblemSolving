import java.util.*;
import java.io.*;

class Main {
    static int n;
    static int[] num;
    static int[] cal;
    static int maxResult = Integer.MIN_VALUE;
    static int minResult = Integer.MAX_VALUE;
    private static void func(int currentAns, int k) {
        if (k == n) {
            maxResult = Math.max(currentAns, maxResult);
            minResult = Math.min(currentAns, minResult);
            return;
        }
        int nextNum = num[k];
        for (int i = 0 ; i < 4; i++) {
            if (cal[i] == 0) continue;
            cal[i]--;
            if (i == 0) func(currentAns + nextNum, k + 1);
            if (i == 1) func(currentAns - nextNum, k + 1);
            if (i == 2) func(currentAns * nextNum, k + 1);
            if (i == 3) func(currentAns / nextNum, k + 1);
            cal[i]++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        
        cal = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            cal[i] = Integer.parseInt(st.nextToken());
        }
        func(num[0], 1);
        System.out.println(maxResult);
        System.out.println(minResult);
    }
}
