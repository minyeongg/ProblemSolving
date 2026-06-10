import java.io.*;
import java.util.*;

public class Comprehension {

  public static void main(String[] args) throws Exception {
    // 기존 데이터로 새 리스트를 만들거나 조건으로 필터링하기
    List<Integer> cubes = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      cubes.add(i * i * i);
    }
    System.out.println("세제곱 (for문): " + cubes);

    // 1~20 의 3의 배수만 필터링하기
    List<Integer> multipleOf3 = IntStream.rangeClosed(1, 20)
                                          .filter(i -> i % 3 == 0)
                                          .boxed()
                                          .collect(Collectors.toList());
    System.out.println("3의 배수 (Stream): " + multiplesOf3);
  }
}
