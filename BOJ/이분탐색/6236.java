import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] arr;
    static int max = 0;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];

        for (int i = 0; i < N; ++i) {
            arr[i] = sc.nextInt();
            max = Integer.max(max, arr[i]);
        }
        // 돈을 가장 많이 쓰는 날 이상의 금액을 인출해야 한다.
        // 그렇지 않으면 인출을 하더라도 금액이 부족하기 때문에 계속 인출을 반복하게 된다.
        int left = max;
        int right = 10_000 * 100_000;
        int count = 0;
        // 이진 탐색을 이용하여 해답을 찾는다.
        while (left <= right) {
            int mid = (left + right) / 2;
            // 지정한 횟수 이하의 횟수만큼 인출해야 할 경우,
            // 인출 금액이 더 적은 경우에 해답이 있는지 탐색해 봐야 한다.
            if (M >= getWithdrawalCount(mid)) {
                result = mid;
                right = mid - 1;
                // 지정한 횟수보다 더 많이 인출해야 할 경우,
                // 인출 금액이 더 커야한다.
            } else {
                left = mid + 1;
            }
        }
        System.out.println(result);
    }
    /**
     * @param withdrawalAmount 현금 인출 금액
     * @return 돈을 계획대로 쓰기 위해 필요한 인출 횟수
     */
    static int getWithdrawalCount(int withdrawalAmount) {
        int count = 1;
        int money = withdrawalAmount;

        for (int i : arr) {
            money -= i;
            // 돈이 모자라면 현금을 다시 인출하여 사용
            if (money < 0) {
                ++count;
                money = withdrawalAmount - i;
            }
        }
        return count;
    }
}
