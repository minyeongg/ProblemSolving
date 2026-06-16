import java.io.*;
import java.util.*;

class 실패율3 {
    class Stage implements Comparable<Stage> {
        int index;
        double failRate;
        Stage(int index, double failRate) {
            this.index = index;
            this.failRate = failRate;
        }
        
        @Override
        public int compareTo(Stage s) {
            if (this.failRate == s.failRate) {
                return this.index - s.index;
            } else {
                return Double.compare(s.failRate, this.failRate);
            }
        }
    }
    
    public int[] solution(int N, int[] stages) {
        // 계수 정렬을 사용하면 인덱스를 그대로 사용할 수 있다.
        int[] challenges = new int[N + 2];
        for (int stage : stages) {
            challenges[stage]++;
        }
        
        double total = stages.length;
        
        List<Stage> state = new ArrayList<>();
        
        for (int i = 1; i <= N; i++) {
            if (total == 0) {
                state.add(new Stage(i, 0.0));
            } else {
                state.add(new Stage(i, challenges[i] / total));
                total -= challenges[i];
            }
        }
        
        Collections.sort(state);
        
        int[] answer = new int[N];
        
        for (int i = 0; i < N; i++) {
            answer[i] = state.get(i).index;
        }
        
        return answer;
    }
}
