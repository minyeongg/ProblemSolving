#1
import java.util.*;
import java.io.*;

class Main {
    static int n, m;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n + 1];
        function(1, 0);
    }
    private static void function(int idx, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = idx; i <= n; i++) {
                arr[depth] = i;
                function(i + 1, depth + 1);
        }
    }
}

#2
import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] arr = new int[10];

    public static void func(int k) {
        if (k == m) { // m개를 모두 택했으면
            for (int i = 0; i < m; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        int st = 1; 
        if (k != 0) st = arr[k - 1] + 1; // 이전 값보다 1 큰 수부터 시작

        for (int i = st; i <= n; i++) {
            arr[k] = i;     // k번째 수를 i로 정함
            func(k + 1);    // 다음 자리 선택
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        func(0);
    }
}
