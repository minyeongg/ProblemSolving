import java.io.*;
import java.util.*;

public class BOJ4485_녹색옷입은애가젤다지 {
    static int N;
    static final int INF = (int) 1e9;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static class Node implements Comparable<Node> {
        int x, y, w;
        public Node(int x, int y, int w) { this.x = x; this.y = y; this.w = w;}
        @Override
        public int compareTo(Node o) { return this.w - o.w; }
    }
    static boolean oob(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N) return true;
        return false;
    }
    static int[][] map;
    static int[][] dijkstra(int x, int y) {
        int[][] dist = new int[N][N]; // start 부터의 거리
        for (int[] row : dist) Arrays.fill(row, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(x, y, map[x][y]));
        dist[x][y] = map[x][y];

        while (!pq.isEmpty()) {
             Node cur = pq.poll();
            if (dist[cur.x][cur.y] < cur.w) continue;

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir], ny = cur.y + dy[dir];
                if (oob(nx, ny)) continue;
                int nd = dist[cur.x][cur.y] + map[nx][ny];
                if (dist[nx][ny] > nd) {
                    dist[nx][ny] = nd;
                    pq.add(new Node(nx, ny, nd));
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = 0;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            tc++;
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dist = dijkstra(0, 0);

            sb.append("Problem ").append(tc).append(": ").append(dist[N-1][N-1]).append('\n');
        }
        br.close();
        System.out.println(sb);
    }
}

