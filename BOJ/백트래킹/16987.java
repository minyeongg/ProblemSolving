import java.util.*;

class Main {
    
    static int max = 0;
    static int[][] egg;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        egg = new int[n][2];
            
        for (int i = 0; i < n; i++) {
            egg[i][0] = sc.nextInt();
            egg[i][1] = sc.nextInt();
        }
            
        if (n == 1) {
            System.out.println(0);
            return;
        }
            
        dfs(0);
            
        System.out.println(max);
            
    }
    
    private static void dfs(int idx) {
        if (idx == n) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (egg[i][0] <= 0) cnt++;
            }
            max = Math.max(max, cnt);
            return;
        }
        if (egg[idx][0] <= 0) {
            dfs(idx + 1);
            return;
        }
        boolean remain = false;
        for (int i = 0; i < n; i++) {
            if (i == idx) continue;
            if (egg[i][0] > 0) {
                remain = true;
                egg[idx][0] -= egg[i][1];
                egg[i][0] -= egg[idx][1];
                dfs(idx + 1);
                egg[idx][0] += egg[i][1];
                egg[i][0] += egg[idx][1];
            }
        }
        if (!remain) dfs(idx + 1);
    }
}
        
