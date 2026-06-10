/*
 * [자주 나는 상황]
 *   - 배열/리스트를 길이 넘어서 접근
 *   - for (i=0; i <= arr.length; i++) 처럼 <= 로 한 칸 넘김
 *   - 마지막 인덱스를 arr.length 로 착각 (실제론 length - 1)
 *   - 빈 배열인데 [0] 으로 접근
 *
 * [에러 메시지]
 *   Index 5 out of bounds for length 5
 *
 * [예방 습관]
 *   - 반복문 조건은 i < length 로 (<= 아님)
 *   - 인덱스 접근 전에 범위 확인
 *   - 빈 배열 가능성 먼저 체크
 *
 * [빠른 점검 순서]
 *   1) 메시지의 Index 숫자 확인
 *   2) 그 인덱스 변수가 어디서 나왔는지 역추적
 *   3) 루프 경계 ( < vs <= ) 확인
 *   4) 입력이 빈 배열인지 확인
 * -----------------------------------------------------------------
 */

public class IndexOutOfBoundsExample {

    public static void main(String[] args) {
        int[] nums = {10, 20, 30}; // 인덱스는 0, 1, 2 까지만 유효

        // --- 나쁜 예시: 에러 발생 ---
        System.out.println("--- 나쁜 예시 ---");
        try {
            // length 가 3이므로 i=3 일 때 nums[3] 접근 -> 터짐
            for (int i = 0; i <= nums.length; i++) {   // <= 가 함정!
                System.out.println(nums[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("에러 발생: " + e.getMessage());
        }

        // --- 좋은 예시: 조건을 < 로 ---
        System.out.println("--- 좋은 예시 ---");
        for (int i = 0; i < nums.length; i++) {        // < 로 고침
            System.out.println(nums[i]);
        }
    }
}
