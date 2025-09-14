import java.util.*;
import java.io.*;

class Main {
    static int cnt = 0;
    static int n;
    static boolean[] isused1 = new boolean[30];
    static boolean[] isused2 = new boolean[30];
    static boolean[] isused3 = new boolean[30];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        func(0);
        System.out.println(cnt);
    }
    private static void func(int cur) {
        if (cur == n) {
           cnt++;
           return;
        }
        for (int i = 0; i < n; i++) {
            if (isused1[i] || isused2[i + cur] || isused3[i - cur + n - 1])
                continue;
            isused1[i] = true;
            isused2[i + cur] = true;
            isused3[i - cur + n - 1] = true;
            func(cur + 1);
            isused1[i] = false;
            isused2[i + cur] = false;
            isused3[i - cur + n - 1] = false;
        }
    }
}
