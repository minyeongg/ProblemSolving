import java.util.*;

class Main {
    static int[] d = new int[1005];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        d[1] = 1;
        d[2] = 2;
        for (int i = 3; i <= 1000; i++) {
            d[i] = (d[i-1] + d[i-2]) % 10007;
        }
        System.out.println(d[n]);
    }
}
