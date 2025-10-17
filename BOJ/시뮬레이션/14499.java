import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int n, m, x, y;
    static int[][] map;
    
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    
    static int[] dice = new int[7]; 
    
    static int[][] idx = {
        {},
        {1, 4, 6, 3}, 
        {1, 3, 6, 4}, 
        {1, 5, 6, 2}, 
        {1, 2, 6, 5}  
    };

    static boolean oob(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= m) return true;
        return false;
    }

    static void move(int dir) {
        int[] p = idx[dir];
        
        int temp = dice[p[0]]; 

        for (int i = 0; i < 3; i++) {
            dice[p[i]] = dice[p[i+1]];
        }
        
        dice[p[3]] = temp;
    }

    static void copy() {
        if (map[x][y] == 0) {
            map[x][y] = dice[6];
        } else {
            dice[6] = map[x][y];
            map[x][y] = 0;
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        
        while(k-- > 0) {
            int dir = Integer.parseInt(st.nextToken());
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if (oob(nx, ny)) continue;
            
            x = nx;
            y = ny;
            
            move(dir);
            
            copy();
            
            sb.append(dice[1]).append("\n");
        }
        
        System.out.print(sb);
        
    }
}
