import java.io.*;

import java.util.*;

class 카드 뭉치 {

    public String solution(String[] cards1, String[] cards2, String[] goal) {

        Queue<String> q1 = new ArrayDeque<>(Arrays.asList(cards1));

        Queue<String> q2 = new ArrayDeque<>(Arrays.asList(cards2));

        

        for (String s : goal) {

            if (!q1.isEmpty() && q1.peek().equals(s)) {

                q1.poll();

            }

            else if (!q2.isEmpty() && q2.peek().equals(s)) {

                q2.poll();

            }

            else {

                return "No";

            }

        }

        

        return "Yes";

    }

}
