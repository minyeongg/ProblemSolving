import java.util.*;

class Main {
    
    static char[][] map = new char[5][5];
    static int ans = 0;
    static List<Integer> list = new ArrayList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        for (int i = 0; i < 5; i++) {
            map[i] = sc.next().toCharArray();
        }
        
        dfs(0, 0);
        
        System.out.println(ans);
        
    }
    
    private static void dfs(int depth, int idx) {
        if (depth == 7) {
            if (check()) ans++;
            return;
        }
        
        for (int i = idx; i < 25; i++) {
            list.add(i);
            dfs(depth + 1, i + 1);
            list.remove(list.size() - 1);
        }   
    }
    
    private static boolean check() {
        
        boolean[][] isp7 = new boolean[5][5];
        boolean[][] visited = new boolean[5][5];
        
        for (int i : list) {
            isp7[i / 5][i % 5] = true;
        }
        
        Queue<int[]> q = new LinkedList<>();
        int x = list.get(0) / 5;
        int y = list.get(0) % 5;
        
        q.add(new int[]{x, y});
        visited[x][y] = true;

        int connected = 1;
        int cntS = (map[x][y] == 'S') ? 1 : 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (!visited[nx][ny] && isp7[nx][ny]) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    connected++;
                    if (map[nx][ny] == 'S') cntS++; 
                }
            }
        }
        
        return (connected >= 7 && cntS >= 4);
    }
}
