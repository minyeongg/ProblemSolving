/*
 * 타입은 맞는데(문자열) 값이 형식에 안 맞아서(숫자로 못 바꿈) 터지는 경우.
 * 코테에서 입력을 읽고 숫자로 바꿀 때 공백/개행이 섞여 자주 발생함.
 *
 * ------------------------------------------------------------------
 * [자주 나는 상황]
 *   - Integer.parseInt("abc") 처럼 숫자가 아닌 문자열 변환
 *   - 입력에 공백/개행이 섞여 있을 때
 *   - 빈 문자열을 파싱하려 할 때
 *
 * [에러 메시지]
 *   For input string: "abc"
 *
 * [예방 습관]
 *   - 입력값을 trim() 으로 앞뒤 공백 제거
 *   - 파싱 전에 형식 확인
 *   - try/catch 로 감싸서 안전하게 처리
 *
 * [빠른 점검 순서]
 *   1) 메시지의 "..." 안 실제 입력값 확인
 *   2) 공백/개행이 섞였는지 확인
 *   3) 빈 문자열인지 확인
 *   4) 입력 읽는 부분(split, readLine) 점검
 * ------------------------------------------------------------------
 */
public class NumberFormatExample {

    public static void main(String[] args) {

        // --- 나쁜 예시: 숫자가 아닌 문자열 파싱 ---
        System.out.println("--- 나쁜 예시 ---");
        String input = "12a";              // 숫자처럼 보이지만 'a' 가 섞임
        try {
            int n = Integer.parseInt(input);
            System.out.println("변환 결과: " + n);
        } catch (NumberFormatException e) {
            System.out.println("에러 발생: " + e.getMessage());
        }

        // --- 자주 데는 케이스: 공백 섞임 ---
        System.out.println("--- 공백 케이스 ---");
        String spaced = " 42 ";            // 앞뒤 공백
        try {
            int n = Integer.parseInt(spaced);   // 공백 때문에 터질 수 있음
            System.out.println("변환 결과: " + n);
        } catch (NumberFormatException e) {
            System.out.println("에러 발생: 공백 섞임 -> trim 필요");
        }

        // --- 좋은 예시: trim 으로 공백 제거 후 파싱 ---
        System.out.println("--- 좋은 예시 ---");
        int n = Integer.parseInt(spaced.trim()); // 공백 제거하면 안전
        System.out.println("변환 결과: " + n);
    }
}
