import java.util.Scanner;
import java.lang.Math;
import java.io.*;
import java.util.*;
class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println((int)(Math.pow(2,n) - 1));
        Hanoi(n, 1, 2, 3);
        bw.flush();
    }
    public static void Hanoi(int n, int start, int mid, int end) throws IOException {
        if(n == 1) {
            bw.write(String.valueOf(start + " " + end + "\n"));
            return;
        }
        Hanoi(n-1, start, end, mid);
        bw.write(String.valueOf(start + " " + end + "\n"));
        Hanoi(n-1, mid, start, end);
    }
}
