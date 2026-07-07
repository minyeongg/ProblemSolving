import java.io.*;
import java.util.*;

class 완주하지 못한 선수 {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> hm = new HashMap<>();
        for (String s : completion) {
            hm.put(s, hm.getOrDefault(s, 0) + 1);
        }
        
        for (String s : participant) {
            if (hm.getOrDefault(s, 0) == 0) {
                return s;
            }
            hm.put(s, hm.get(s) - 1);
        }
        return null;
    }
}
