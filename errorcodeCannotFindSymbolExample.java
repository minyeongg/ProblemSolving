/*
 * 선언 안 한 변수, 오타, 스코프 밖 변수 접근은
 * 자바에선 실행 전에 컴파일러가 "cannot find symbol" 로 잡아줌.
 * 그래서 try/catch 로 잡을 수 있는 게 아니라, 코드 자체가 컴파일이 안 됨.
 *
 * ------------------------------------------------------------------
 * [자주 나는 상황]
 *   - 선언 안 한 변수 사용
 *   - 변수 이름 오타
 *   - 스코프 밖 변수 접근 (if/for 블록 안에서 선언한 변수를 밖에서 씀)
 *   - import 빠뜨림
 *
 * [에러 메시지]
 *   error: cannot find symbol
 *
 * [예방 습관]
 *   - 변수는 쓰기 전에 선언
 *   - 필요한 스코프에서 선언 (블록 밖에서 쓸 거면 블록 밖에서 선언)
 *   - IDE 의 빨간 줄 무시하지 말기
 *
 * [빠른 점검 순서]
 *   1) cannot find symbol 의 symbol 이름 확인
 *   2) 오타인지 확인
 *   3) 선언 위치가 이 스코프에서 보이는지 확인
 *   4) import 빠졌는지 확인
 * ------------------------------------------------------------------
 */
public class CannotFindSymbolExample {

    public static void main(String[] args) {

        // --- 나쁜 예시 (아래 주석 풀면 컴파일 자체가 안 됨) ---
        //
        // 1) 스코프 밖 접근:
        // if (true) {
        //     int inside = 10;          // if 블록 안에서만 살아있음
        // }
        // System.out.println(inside);   // error: cannot find symbol (블록 밖이라 안 보임)
        //
        // 2) 오타:
        // int count = 5;
        // System.out.println(cont);     // error: cannot find symbol (count 의 오타)


        // --- 좋은 예시: 블록 밖에서 선언해서 끝까지 보이게 ---
        int result;                      // 미리 바깥 스코프에서 선언
        if (3 > 2) {
            result = 10;
        } else {
            result = 0;
        }
        System.out.println("result: " + result); // 잘 보임

        int count = 5;
        System.out.println("count: " + count);   // 오타 없이 정확히
    }
}
