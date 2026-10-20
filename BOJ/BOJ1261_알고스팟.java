import java.io.*;
import java.util.*;

public class BOJ1261_알고스팟 {
    static int N, M;
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
        if (x <= 0 || y <= 0 || x > N || y > M) return true;
        return false;
    }
    static int[][] map;
    static int[][] dijkstra(int x, int y) {
        int[][] dist = new int[N+1][M+1]; // start 부터의 거리
        for (int[] row : dist) Arrays.fill(row, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(x, y, 0));
        dist[x][y] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            // 여기서도 가중치 비교 필요
            if (dist[cur.x][cur.y] < cur.w) continue;

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir], ny = cur.y + dy[dir];
                if (oob(nx, ny)) continue;
                int nd = dist[cur.x][cur.y];
                if (map[nx][ny] == 1) nd++;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dist = dijkstra(1, 1);

        System.out.println(dist[N][M]);
    }
}
