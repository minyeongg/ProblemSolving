import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int m1 = sc.nextInt();
        int d1 = sc.nextInt();
        int m2 = sc.nextInt();
        int d2 = sc.nextInt();
        int[] days_of_month = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        Map<String, Integer> map = new HashMap<>();
        map.put("Mon", 1);
        map.put("Tue", 2);
        map.put("Wed", 3);
        map.put("Thu", 4);
        map.put("Fri", 5);
        map.put("Sat", 6);
        map.put("Sun", 7);

        String A = sc.next();
        int day = map.get(A);
        // Please write your code here.

        int days1 = days(m1, d1);
        int days2 = days(m2, d2);

        int diff = days2 - days1 + 1;

        int answer = diff / 7;

        int remainder = diff % 7;

        if (day <= remainder) answer++;
        System.out.println(answer);
    }

    public static int days(int m, int d) {
        int[] days_of_month = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int totalDays = 0;
        for (int month = 1; month < m; month++) {
            totalDays += days_of_month[month];
        }

        totalDays += d;
        return totalDays;
    }
}