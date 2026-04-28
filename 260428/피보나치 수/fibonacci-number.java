import java.io.*;
import java.util.*;

public class Main {
    static int[] memo = new int[50];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        Arrays.fill(memo, -1);

        System.out.println(fibo(n));
    }

    static int fibo(int n) {
        if (n <= 1) return n;

        if (memo[n] != -1) {
            return memo[n];
        }

        return memo[n] = fibo(n - 1) + fibo(n - 2);
    }
}