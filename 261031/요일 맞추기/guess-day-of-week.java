import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m1 = sc.nextInt();
        int d1 = sc.nextInt();
        int m2 = sc.nextInt();
        int d2 = sc.nextInt();
        // Please write your code here.
        int[] days_of_month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] days = {"", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        // m1 d1 일의 지나간 일 수를 구한다.
        int days1 = 0;
        for (int month = 1; month < m1; month++) {
            days1 += days_of_month[month];
        }
        days1 += d1;
        // m2 d2 일의 지나간 일 수를 구한다.
        int days2 = 0;
        for (int month = 1; month < m2; month++) {
            days2 += days_of_month[month];
        }
        days2 += d2;

        int answer = 1;
        // m2 d2가 더 많이 지나갔다면
        if (days2 > days1) {
            int diff = days2 - days1;

            answer = 1 + (diff % 7); 
        }
            // 두 일수 차이를 구하고 7로 나눈 나머지 만큼 요일이 늘어난다.
        // m1 d1이 더 많이 지나갔다면
        else if (days1 > days2) {
            int diff = days1 - days2;
            if (diff % 7 != 0) answer = 7 - Math.abs(1 - (diff % 7));
        }
            // 두 일수 차이를 구하고 7로 나눈 나머지 만큼 요일이 줄어든다.
            /*
            1-0 = 1 -
            1-1 = 0 -> 7
            1-2 = -1-> 6
            1-3 = -2 -> 5
            */

        System.out.println(days[answer]);
    }
}