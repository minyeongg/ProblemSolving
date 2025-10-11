import java.util.*;
import java.io.*;

class Main {
    
    static int n, m;
    static List<Integer[]> houses = new ArrayList<>();
    static List<Integer[]> chickens = new ArrayList<>();
    static int[][] map = new int[52][52];
    static int mn = Integer.MAX_VALUE;
    static int[][] chickenArray;
    static boolean[] selected;
    
    private static void solution(int idx, int cnt) {
       if (cnt == m) {
            calculate();
            return;
        }
        
        for (int i = idx; i < chickens.size(); i++) {
            selected[i] = true;
            solution(i + 1, cnt + 1); 
            selected[i] = false;
        }
    }
    
    private static void calculate() {
        int totalDis = 0;
        // selected[i] = true인 chickenArray[i]에 대하여 계산
        for (Integer[] house : houses) {
            int minDis = Integer.MAX_VALUE;
            for (int i = 0; i < chickenArray.length; i++) {
                if (!selected[i]) continue;
                int dis = Math.abs(house[0] - chickenArray[i][0]) + Math.abs(house[1] - chickenArray[i][1]);
                minDis = Math.min(dis, minDis);
            }
            totalDis += minDis;
        }
        mn = Math.min(totalDis, mn);
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) houses.add(new Integer[]{i, j});
                if (map[i][j] == 2) chickens.add(new Integer[]{i, j});
            }
        }
        
        chickenArray = new int[chickens.size()][2];
        selected = new boolean[chickens.size()];
        
        for (int i = 0; i < chickens.size(); i++) {
            Integer[] chicken = chickens.get(i);
            chickenArray[i][0] = chicken[0];
            chickenArray[i][1] = chicken[1];
        }
        
        solution(0, 0);
        
        System.out.println(mn);
        
    }
}
