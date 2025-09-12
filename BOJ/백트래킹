import java.util.*;
import java.io.*;

class Main {
    static int n, m;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n + 1];
        visited = new boolean[n + 1];
        function(0);
    }
    private static void function(int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                arr[depth] = i;
                visited[i] = true;
                function(depth + 1);
                visited[i] = false;
            }
        }
    }
}
