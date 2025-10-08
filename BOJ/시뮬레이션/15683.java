# 바킹독님 풀이
import java.util.*;
import java.io.*;

class Main {
    // 좌표 쌍을 저장하기 위한 간단한 클래스 (C++의 pair<int, int> 역할)
    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    // 남(0), 동(1), 북(2), 서(3) 순서 (C++ 원본과 동일)
    static int[] dx = {1, 0, -1, 0}; 
    static int[] dy = {0, 1, 0, -1};
    
    // 최초 맵 상태 (C++의 board1)
    static int[][] board1; 
    // 작업용 맵 (C++의 board2)
    static int[][] board2; 
    
    // CCTV 좌표 리스트 (C++의 vector<pair<int, int> > cctv)
    static List<Point> cctv = new ArrayList<>();
    
    static int mn = Integer.MAX_VALUE;

    /**
     * 좌표가 지도 범위를 벗어나는지 확인합니다. (Out Of Bounds)
     */
    static boolean OOB(int a, int b) {
        return a < 0 || a >= n || b < 0 || b >= m;
    }

    /**
     * (x, y)에서 dir 방향으로 진행하며 감시 영역(7)으로 갱신합니다.
     */
    static void upd(int x, int y, int dir) {
        // C++ 원본처럼 dir이 4를 넘어갈 수 있으므로 나머지 연산을 사용해 0~3 범위로 유지
        dir %= 4; 
        while (true) {
            x += dx[dir];
            y += dy[dir];
            
            // 범위를 벗어나거나 벽(6)을 만나면 종료
            if (OOB(x, y) || board2[x][y] == 6) return;
            
            // 해당 칸이 빈칸(0)일 경우에만 7로 덮습니다.
            if (board2[x][y] == 0) { 
                board2[x][y] = 7;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board1 = new int[n][m];
        board2 = new int[n][m]; // NullPointerException 해결

        // 1. 맵 입력 및 CCTV 좌표 리스트 생성
        int initial_empty_count = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board1[i][j] = Integer.parseInt(st.nextToken());
                
                if (board1[i][j] != 0 && board1[i][j] != 6) {
                    // CCTV 좌표만 리스트에 저장 (C++ 원본과 동일)
                    cctv.add(new Point(i, j));
                }
                if (board1[i][j] == 0) {
                    initial_empty_count++;
                }
            }
        }
        
        // 초기 사각지대 개수를 최솟값으로 설정
        mn = initial_empty_count;
        
        int cctvCount = cctv.size();
        // 2. 총 경우의 수 계산 (4^N)
        // C++의 1 << (2 * cctv.size())와 동일한 Math.pow(4, cctvCount) 사용
        int totalCases = (int) Math.pow(4, cctvCount);

        // 모든 방향 조합(4^N)을 순회
        for (int tmp = 0; tmp < totalCases; tmp++) {
            
            // 3. 맵 복사 (board1 -> board2)
            // 새로운 조합 시뮬레이션을 위해 board2를 초기 상태로 복원
            for (int i = 0; i < n; i++) {
                System.arraycopy(board1[i], 0, board2[i], 0, m); 
            }

            int brute = tmp;
            
            // 4. 각 CCTV에 방향 적용 및 감시 갱신
            for (int i = 0; i < cctvCount; i++) {
                // tmp를 4진법으로 생각하여 i번째 CCTV의 방향을 결정
                int dir = brute % 4; 
                brute /= 4;
                
                Point p = cctv.get(i);
                int x = p.x;
                int y = p.y;
                // C++ 원본과 동일하게 board1에서 CCTV 타입을 꺼내옴
                int type = board1[x][y];
                
                // CCTV 타입에 따라 감시 방향 갱신
                if (type == 1) {
                    upd(x, y, dir);
                } else if (type == 2) {
                    upd(x, y, dir);
                    upd(x, y, dir + 2); 
                } else if (type == 3) {
                    upd(x, y, dir);
                    upd(x, y, dir + 1);
                } else if (type == 4) {
                    upd(x, y, dir);
                    upd(x, y, dir + 1);
                    upd(x, y, dir + 2);
                } else { // type == 5
                    upd(x, y, dir);
                    upd(x, y, dir + 1);
                    upd(x, y, dir + 2);
                    upd(x, y, dir + 3);
                }
            }
            
            // 5. 사각지대 개수 카운트 및 최솟값 갱신
            int val = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board2[i][j] == 0) {
                        val++;
                    }
                }
            }
            mn = Math.min(mn, val);
        }
        
        System.out.println(mn);
    }
}

#내 풀이
import java.util.*;
import java.io.*;

class Main {
    static class CCTV {
        int type;
        int x;
        int y;
        CCTV(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }
    static class Info {
        CCTV cctv;
        int idx;
        Info(CCTV cctv, int idx) {
            this.cctv = cctv;
            this.idx = idx;
        }
    }
    static String[][] map;
    static int n, m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][][] dir = 
    {
        {},
        {{0}, {1}, {2}, {3}},
        {{0, 2}, {1, 3}},
        {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
        {{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {0, 2, 3}},
        {{0, 1, 2, 3}}
    };
    static List<CCTV> list = new ArrayList<>();
    static List<Info> info = new ArrayList<>();
    static int mn = Integer.MAX_VALUE;
    static String[][] newMap;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new String[n][m];
        newMap = new String[n][m];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = st.nextToken();
                if (!map[i][j].equals("0") && !map[i][j].equals("6")) {
                    list.add(new CCTV(Integer.parseInt(map[i][j]), i, j));
                }
            }
        }
        
        copy();
        
        solve(0);
        
        System.out.println(mn);
        
    }
    
    private static void solve(int idx) {
        if (idx == list.size()) {
            update();
            mn = Math.min(mn, count());
            copy();
            return;
        }
        
        CCTV c = list.get(idx);
        for (int j = 0; j < dir[c.type].length; j++) {
            info.add(new Info(c, j));
            solve(idx + 1);
            info.remove(info.size() - 1);
        }
    }
    
    private static int count() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (newMap[i][j].equals("0")) cnt++;
            }
        }
        return cnt;
    }

    private static void update() {
        for (Info tar : info) {
            int type = tar.cctv.type;
            int x = tar.cctv.x;
            int y = tar.cctv.y;
    
            for (int d = 0; d < dir[type][tar.idx].length; d++) {
                int nd = dir[type][tar.idx][d];
                int nx = x;
                int ny = y;
    
                while (true) {
                    nx += dx[nd];
                    ny += dy[nd];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) break; // 범위 밖
                    if (newMap[nx][ny].equals("6")) break; // 벽
                    if (newMap[nx][ny].equals("0")) {
                        newMap[nx][ny] = "#";
                    }
                }
            }
        }
    }
    
    private static void copy() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMap[i][j] = map[i][j];
            }
        }
    }
}
