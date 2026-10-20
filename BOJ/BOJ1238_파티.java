import java.io.*;
import java.util.*;

public class BOJ1238_파티 {
    static int N, M, X;
    static final int INF = (int) 1e9;
    static class Node implements Comparable<Node> {
        int to, w;
        public Node(int to, int w) { this.to = to; this.w = w;}
        @Override
        public int compareTo(Node o) { return this.w - o.w; }
    }
    static List<Node>[] original;
    static List<Node>[] reverse;
    static int[] dijkstra(int start, List<Node>[] graph) {
        int[] dist = new int[N + 1]; // start 부터의 거리
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            // 여기서도 가중치 비교 필요
            if (dist[cur.to] < cur.w) continue;

            for (Node next : graph[cur.to]) {
                int nd = dist[cur.to] + next.w;
                if (dist[next.to] > nd) {
                    dist[next.to] = nd;
                    pq.add(new Node(next.to, nd));
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        original = new List[N + 1];
        reverse = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            original[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            original[a].add(new Node(b, c));
            reverse[b].add(new Node(a, c));
        }

        int[] from = dijkstra(X, original);
        int[] to = dijkstra(X, reverse);

        int MX = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = from[i] + to[i];
            if (MX < sum) MX = sum;
        }

        System.out.println(MX);
    }
}
