import java.util.*;
import java.io.*;

class Main {
    static int n, m;
    static int[] arr;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n + 1];
        function(0);
        System.out.println(sb);
    }
    private static void function(int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= n; i++) {
            arr[depth] = i;
            function(depth + 1);
        }
    }
}
