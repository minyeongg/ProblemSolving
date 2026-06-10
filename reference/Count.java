import java.io.*;
import java.util.*;

public class Count {

  public static void main(String[] args) throws Exception {

    // 개수 세기: HashMap + merge
    // 리스트 / 문자열 안에 각 항목이 몇 번 나오는지 셀 때
    String[] fruits = {"pear", "kiwi", "pear", "melon", "kiwi", "pear"};

    Map<String, Integer> counts = new HashMap<>();
    for (String f : fruits) {
      counts.merge(f, 1, Integer::sum);
    }
    System.out.println("개수 결과: " + counts);
    System.out.println("pear 개수: " + counts.get("pear"));
    // 없는 키 조회: null 리턴하므로 getOrDefault로 0으로 처리
    System.out.println("grape 개수: " + counts.getOrDefault("grape", 0));

    // 가장 많이 나온 항목 찾기
    String top = Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey();

    System.out.println("가장 많이 나온 것: " + top);
  }
}
