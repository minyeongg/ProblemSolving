import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        // Please write your code here.
        int from = 11 * 24 * 60 + 11 * 60 + 11;
        int to = A * 24 * 60 + B * 60 + C;
        int answer = from > to ? -1 : to - from;
        System.out.println(answer);
    }
}