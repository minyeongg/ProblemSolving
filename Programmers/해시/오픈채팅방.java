import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> hm = new HashMap<>();
        // record 대로 해시맵 업데이트하기
        for (String s : record) {
            String[] substr = s.split(" ");
            if (substr[0].equals("Enter") || substr[0].equals("Change")) {
                hm.put(substr[1], substr[2]);
            }
        }
        
        // 최종 해시맵 기준으로 record 대로 문자열 배열 만들기
        List<String> answer = new ArrayList<>();
        
        for (String s : record) {
            String[] substr = s.split(" ");
            if (substr[0].equals("Enter")) {
                answer.add(hm.get(substr[1]) + "님이 들어왔습니다.");
            }
            else if (substr[0].equals("Leave")) {
                answer.add(hm.get(substr[1]) + "님이 나갔습니다.");
            }
        }
        
        return answer.toArray(new String[0]); // answer.stream().toArray(String[]::new);
    }
}
