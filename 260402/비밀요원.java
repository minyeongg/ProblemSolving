import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int index, cost;
    Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}

public class Main {
    static int N, M, X;
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] graph = new ArrayList[N + 1];      // 정방향 (복귀용)
        ArrayList<Node>[] reverseGraph = new ArrayList[N + 1]; // 역방향 (가는길용)

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
            reverseGraph[v].add(new Node(u, w)); // 방향을 뒤집어 저장
        }

        int[] distToHome = dijkstra(graph, X);      // X -> 모든 도시 (복귀)
        int[] distToParty = dijkstra(reverseGraph, X); // 모든 도시 -> X (가는길)

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, distToHome[i] + distToParty[i]);
        }
        System.out.println(maxTime);
    }

    static int[] dijkstra(ArrayList<Node>[] adj, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.index] < cur.cost) continue;

            for (Node next : adj[cur.index]) {
                if (dist[next.index] > dist[cur.index] + next.cost) {
                    dist[next.index] = dist[cur.index] + next.cost;
                    pq.add(new Node(next.index, dist[next.index]));
                }
            }
        }
        return dist;
    }
}
