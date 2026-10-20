import java.io.*;
import java.util.*;

public class BOJ_5972 {
    static int v, e;
    static final int INF = (int) 1e9;
    static class Node implements Comparable<Node> {
        int to, w;
        public Node(int to, int w) { this.to = to; this.w = w;}
        @Override
        public int compareTo(Node o) { return this.w - o.w; }
    }
    static List<Node>[] graph;
    static int[] dijkstra(int start) {
        int[] dist = new int[v + 1]; // start 부터의 거리
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
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new List[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] dist1 = dijkstra(1);
        int[] dist2 = dijkstra(v1);
        int[] dist3 = dijkstra(v2);

        System.out.println(Math.min(dist1[v1] + dist2[v2] + dist3[v],
                dist1[v2] + dist3[v1] + dist2[v]));

    }
}
