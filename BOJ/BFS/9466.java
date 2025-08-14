import java.io.*;
import java.util.*;

public class Main {
    static int[] arr = new int[100005];
    static int n;
    static int[] state = new int[100005];
    static final int NOT_VISITED = 0;
    static final int CYCLE_IN = -1;
    
    static void run(int x) {
        int cur = x;
        while (true) {
            state[cur] = x;
            cur = arr[cur];
            // 이번 방문에서 지나간 학생에 도달했을 경우
            if (state[cur] == x) {
                while (state[cur] != CYCLE_IN) {
                    state[cur] = CYCLE_IN;
                    cur = arr[cur];
                }
                return;
            }
            // 이전 방문에서 지나간 학생에 도달했을 경우
            else if (state[cur] != 0) {
                return;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            
            // state 배열 초기화 (C++의 fill과 동일)
            Arrays.fill(state, 1, n + 1, 0);
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            for (int i = 1; i <= n; i++) {
                if (state[i] == NOT_VISITED) {
                    run(i);
                }
            }
            
            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (state[i] != CYCLE_IN) {
                    cnt++;
                }
            }
            
            bw.write(cnt + "\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
}
