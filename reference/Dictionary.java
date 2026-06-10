import java.io.*;
import java.util.*;

public class Dictionary {

  public static void main(String[] args) throws Exception {
    // HashMap
    // 키로 값을 빠르게 조회/추가/수정/삭제할 때
    Map<String, Object> member = new HashMap<>();
    member.put("name", "Grace");
    member.put("age", 22);
    member.put("major", "Physics");
    System.out.println("초기 맵: " + member);

    System.out.println("이름 조회: " + member.get("name"));  // 조회
    member.put("gpa", 4.1);                                  // 새 키 추가
    member.put("age", 23);                                   // 기존 키 수정
    member.remove("major"); 
    System.out.println("수정 후 맵: " + member);
  }
}
