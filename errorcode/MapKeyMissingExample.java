/*
 * 자바 Map 은 없는 키를 get() 해도 예외가 안 나고 null 을 반환함.
 * 문제는 그 null 을 그대로 쓰다가 나중에 NullPointerException(NPE)으로 터지는 것.
 * 특히 int 같은 기본형으로 자동 언박싱할 때 터짐.
 *
 * ------------------------------------------------------------------
 * [자주 나는 상황]
 *   - HashMap 에서 없는 키를 get() 한 결과(null)를 바로 연산에 사용
 *   - Integer -> int 자동 언박싱 과정에서 null 이라 터짐
 *
 * [에러 메시지]
 *   Cannot invoke "java.lang.Integer.intValue()" because "..." is null
 *
 * [예방 습관]
 *   - getOrDefault(키, 기본값) 사용
 *   - containsKey() 로 먼저 확인
 *   - 카운팅은 merge(키, 1, Integer::sum) 사용
 *
 * [빠른 점검 순서]
 *   1) NPE 난 줄에서 null 인 변수 찾기
 *   2) 그게 map.get(...) 결과인지 확인
 *   3) 해당 키가 실제로 put 된 적 있는지 확인
 *   4) getOrDefault 로 교체
 * ------------------------------------------------------------------
 */
import java.util.HashMap;
import java.util.Map;

public class MapKeyMissingExample {

    public static void main(String[] args) {
        Map<String, Integer> stock = new HashMap<>();
        stock.put("apple", 5);
        stock.put("banana", 3);

        // --- 나쁜 예시: 없는 키를 get -> null -> 언박싱하다 NPE ---
        System.out.println("--- 나쁜 예시 ---");
        try {
            int grapeCount = stock.get("grape"); // 없는 키 -> null -> int 변환 시 터짐
            System.out.println("포도 개수: " + grapeCount);
        } catch (NullPointerException e) {
            System.out.println("에러 발생: 없는 키를 꺼내 null 이 됨 -> " + e.getMessage());
        }

        // --- 좋은 예시: getOrDefault 로 안전하게 ---
        System.out.println("--- 좋은 예시 ---");
        int grapeCount = stock.getOrDefault("grape", 0); // 없으면 0
        System.out.println("포도 개수: " + grapeCount);
    }
}
