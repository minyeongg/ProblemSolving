/*
 * ------------------------------------------------------------------
 * [자주 나는 상황]
 *   - Object 를 엉뚱한 타입으로 캐스팅
 *   - 제네릭 없이 컬렉션을 쓰다가 꺼낼 때 캐스팅 실수
 *
 * [에러 메시지]
 *   class java.lang.String cannot be cast to class java.lang.Integer
 *
 * [예방 습관]
 *   - 제네릭 타입 명시 (List<String> 처럼)
 *   - 캐스팅 전에 instanceof 로 실제 타입 확인
 *
 * [빠른 점검 순서]
 *   1) 메시지의 X -> Y 타입 확인
 *   2) 그 캐스팅이 어디서 일어나는지 찾기
 *   3) 실제 객체 타입이 무엇인지 확인
 *   4) 제네릭으로 애초에 막을 수 있는지 검토
 * ------------------------------------------------------------------
 */
public class ClassCastExample {

    public static void main(String[] args) {

        // --- 나쁜 예시: 실제론 String 인데 Integer 로 캐스팅 ---
        System.out.println("--- 나쁜 예시 ---");
        Object obj = "hello";          // 안에 든 진짜 타입은 String
        try {
            Integer num = (Integer) obj; // String -> Integer 캐스팅 불가 -> 터짐
            System.out.println(num);
        } catch (ClassCastException e) {
            System.out.println("에러 발생: " + e.getMessage());
        }

        // --- 좋은 예시: instanceof 로 먼저 확인 ---
        System.out.println("--- 좋은 예시 ---");
        Object value = "hello";
        if (value instanceof Integer) {        // 진짜 Integer 일 때만 캐스팅
            Integer num = (Integer) value;
            System.out.println("숫자: " + num);
        } else if (value instanceof String) {
            String text = (String) value;
            System.out.println("문자열: " + text);
        }
    }
}
