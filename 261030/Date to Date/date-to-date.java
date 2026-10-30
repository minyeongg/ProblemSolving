import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m1 = sc.nextInt();
        int d1 = sc.nextInt();
        int m2 = sc.nextInt();
        int d2 = sc.nextInt();
        // Please write your code here.
        int[] num_of_days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days1 = 0;
        for (int month = 1; month < m1; month++) {
            days1 += num_of_days[month - 1];
        }
        days1 += d1;

        int days2 = 0;
        for (int month = 1; month < m2; month++) {
            days2 += num_of_days[month - 1];
        }
        days2 += d2;

        System.out.println(days2 - days1 + 1);
    }
}