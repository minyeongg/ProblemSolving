import java.io.*;
import java.util.*;

public class 실패율 {
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
            }
            return Double.compare(s.failRate, this.failRate);
        }
    }
    
    int[] solution(int N, int[] stages) {
        List<Stage> state = new ArrayList<>();
        int[] arrive = new int[N + 2]; // 각 스테이지 별 도달한 플레이어 수
        int[] unclear = new int[N + 2]; // 각 스테이지 별 도달했으나 아직 클리어하지 못한 플레이어의 수
        for (int stage : stages) {
            for (int i = 1; i <= stage; i++) {
                arrive[i]++;
            }
            unclear[stage]++;
        }
        
        for (int i = 1; i <= N; i++) {
            if (arrive[i] == 0) {
                state.add(new Stage(i, 0));
            } else {
                state.add(new Stage(i, (double) unclear[i] / arrive[i]));
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
