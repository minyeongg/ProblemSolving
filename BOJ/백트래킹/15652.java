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
        if (k != 0) st = arr[k - 1]; // 비내림차순 유지

        for (int i = st; i <= n; i++) {
            arr[k] = i;      // k번째 수를 i로 정함
            func(k + 1);     // 다음 수 선택
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        func(0);
    }
}
