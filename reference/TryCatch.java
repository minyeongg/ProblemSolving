import java.io.*;
import java.util.*;

public class TryCatch {
  
  public static void main(String[] args) throws Exception {
    // 예외처리
    // 0으로 나누기, 파싱 실패 등
    safeDivide(20, 0);
    safeParse("열다섯");
  }

  static void safeDivide(int a, int b) {
    try {
      int result = a / b;
    } catch (ArithmeticException e) {
      System.out.println("에러: 0으로 나눌 수 없음 -> " + e.getMessage());
    }
  }

  static void safeParse(String text) {
    try {
      int num = Integer.parseInt(text);
      System.out.println("숫자 변환 성공: " + num);
    } catch (NumberFormatException e) {
      System.out.println("에러: 숫자로 못 바꿈 -> " + text);
    }
  }
}
