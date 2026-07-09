import java.io.*;
import java.util.*;

class 할인 행사 {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> wishlist = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wishlist.put(want[i], number[i]);
        }
        
        // 현재 탐색 범위에서 10일 간의 할인 제품과 수량
        Map<String, Integer> stock = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            stock.put(discount[i], stock.getOrDefault(discount[i], 0) + 1);
        }
        
        if (wishlist.equals(stock)) answer++;
        
        for (int i = 1; i <= discount.length - 10;  i++) {
            // i - 1이 빠지는 값, i + 9가 새로 들어오는 값
            int v = stock.get(discount[i - 1]) - 1;
            if (v == 0) {
                stock.remove(discount[i - 1]);
            } else {
                stock.put(discount[i - 1], v);
            }
            stock.put(discount[i + 9], stock.getOrDefault(discount[i + 9], 0) + 1);
            
            if (wishlist.equals(stock)) answer++;
            
        }
        
        return answer;
    }
}
