/*
 * 재귀 호출이 끝나지 않고 계속 쌓이면 호출 스택이 꽉 차서 터짐.
 * 종료 조건(base case)이 없거나 잘못됐을 때, 또는 깊이가 너무 깊을 때 발생.
 * 참고: StackOverflowError 는 Exception 이 아니라 Error 라서
 *       원래 try/catch 로 잡는 대상이 아님 (여기선 시연 위해 잡음).
 *
 * ------------------------------------------------------------------
 * [자주 나는 상황]
 *   - 재귀 종료 조건이 없음
 *   - 종료 조건이 있어도 거기 도달 못 함 (값이 안 줄어듦)
 *   - 깊이가 너무 깊은 재귀 (DFS 에서 자주)
 *
 * [에러 메시지]
 *   java.lang.StackOverflowError
 *
 * [예방 습관]
 *   - 재귀 함수는 종료 조건부터 쓰기
 *   - 매 호출마다 입력이 종료 조건 쪽으로 줄어드는지 확인
 *   - 깊이 깊으면 반복문 + 스택으로 전환
 *
 * [빠른 점검 순서]
 *   1) 종료 조건이 있는지 확인
 *   2) 종료 조건에 실제로 도달하는지 (매번 값이 줄어드는지) 확인
 *   3) 입력 크기로 재귀 깊이 추정
 *   4) 너무 깊으면 반복문으로 전환
 * ------------------------------------------------------------------
 */
public class StackOverflowExample {

    // 나쁜 예시: 종료 조건 없음 -> 무한 재귀
    static int badCountdown(int n) {
        System.out.println(n);
        return badCountdown(n - 1); // n 이 0, 음수 돼도 계속 호출 -> 스택 터짐
    }

    // 좋은 예시: 종료 조건 있음
    static int goodCountdown(int n) {
        if (n < 0) {          // 종료 조건 (base case)
            return 0;
        }
        System.out.println(n);
        return goodCountdown(n - 1); // 매번 n 이 줄어서 결국 종료 조건 도달
    }

    public static void main(String[] args) {
        System.out.println("--- 좋은 예시 ---");
        goodCountdown(3);

        System.out.println("--- 나쁜 예시 ---");
        try {
            badCountdown(3); // 끝없이 쌓이다 StackOverflowError
        } catch (StackOverflowError e) {
            System.out.println("에러 발생: 재귀가 안 끝나서 스택이 꽉 참");
        }
    }
}
