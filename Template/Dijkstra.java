import java.io.*;
import java.util.*;

public class Dijkstra {
    static class Node implements Comparable<Node> {
        int to; long w;
        Node(int to, long w) { this.to = to; this.w = w; }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.w, o.w);
        }
    }

    static final long INF = Long.MAX_VALUE;
    static int n, m;
    static ArrayList<Node>[] graph;

    static long[] dijkstra(int start) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.w > dist[cur.to]) continue;      // 낡은 항목

            for (Node next : graph[cur.to]) {
                long nd = cur.w + next.w;
                if (nd < dist[next.to]) {            // 완화
                    dist[next.to] = nd;
                    pq.add(new Node(next.to, nd));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            graph[x].add(new Node(y, z));
            // 무방향이면: graph[y].add(new Node(x, z));
        }

        long[] dist = dijkstra(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dist[i] == INF ? "INF" : dist[i]).append('\n');
        }
        System.out.print(sb);
    }
}
