import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List; // List 인터페이스 사용
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[][] board = new int[10][10];
    static int[][] play_board = new int[10][10];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<Coord> blank = new ArrayList<>();
    // Queue 대신 List로 변경하여 중복 초기화 제거
    static List<Coord> virus_coords = new ArrayList<>(); 
    static int max_safe_area = 0;

    
    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = arr.length - 1;
        while (arr[i] >= arr[j]) {
            j--;
        }

        swap(arr, i, j);

        reverse(arr, i + 1, arr.length - 1);

        return true;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }
    
    static boolean oob(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    // virus_coords 초기화 로직 제거. play_board 복사만 남음.
    static void reset_board() {
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, play_board[i], 0, m);
        }
    }

    static int bfs() {
        Queue<Coord> q = new LinkedList<>();
        // List에 저장된 시작점을 큐에 복사
        for(Coord c : virus_coords) {
            q.offer(c);
        }

        while (!q.isEmpty()) {
            Coord cur = q.poll();
            
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (oob(nx, ny)) continue;
                
                if (play_board[nx][ny] == 0) {
                    play_board[nx][ny] = 2;
                    q.offer(new Coord(nx, ny));
                }
            }
        }
        
        int safe_count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (play_board[i][j] == 0) {
                    safe_count++;
                }
            }
        }
        return safe_count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    blank.add(new Coord(i, j));
                } else if (board[i][j] == 2) {
                    // 초기 바이러스 위치를 main에서 단 한 번만 저장
                    virus_coords.add(new Coord(i, j));
                }
            }
        }

        int blank_size = blank.size();
        
        int[] brute = new int[blank_size];
        Arrays.fill(brute, 0);
        for (int i = 0; i < 3; i++) {
            brute[blank_size - 1 - i] = 1;
        }

        do {
            reset_board();
            
            for(int i = 0; i < blank_size; i++) {
                if (brute[i] == 1) {
                    Coord wall_coord = blank.get(i);
                    play_board[wall_coord.x][wall_coord.y] = 1;
                }
            }
            
            int safe_area = bfs();
            max_safe_area = Math.max(max_safe_area, safe_area);
            
        } while (nextPermutation(brute));

        System.out.println(max_safe_area);
    }
}
