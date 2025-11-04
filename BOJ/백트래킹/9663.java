import java.io.*;
import java.util.*;

class Main {
    static int n, cnt;
    static int[] queenCol;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        queenCol = new int[n];
        solution(0);
        System.out.println(cnt);
    }
    static void solution(int row) {
        if (row == n) {
            cnt++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                queenCol[row] = col;
                solution(row + 1);
            }
        }
    }
    static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queenCol[i] == col) return false;
            if (Math.abs(row - i) == Math.abs(col - queenCol[i])) return false;
        }
        return true;
    }
}
