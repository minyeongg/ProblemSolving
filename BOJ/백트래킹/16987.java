import java.util.*;

public class Main {

    static int n;
    static int[] s = new int[10]; // 내구도
    static int[] w = new int[10]; // 무게
    static int mx = 0;
    static int cnt = 0; // 깨져있는 계란 수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            s[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        solve(0);

        System.out.println(mx);
    }

    static void solve(int a) {
        if (a == n) {
            mx = Math.max(mx, cnt);
            return;
        }

        // a번째 계란이 깨져있거나, 나머지 계란이 다 깨져있으면 넘어감
        if (s[a] <= 0 || cnt == n - 1) {
            solve(a + 1);
            return;
        }

        for (int t = 0; t < n; t++) {
            if (t == a || s[t] <= 0) continue;

            s[a] -= w[t];
            s[t] -= w[a];

            if (s[a] <= 0) cnt++;
            if (s[t] <= 0) cnt++;

            solve(a + 1);

            if (s[a] <= 0) cnt--;
            if (s[t] <= 0) cnt--;

            s[a] += w[t];
            s[t] += w[a];
        }
    }
}
