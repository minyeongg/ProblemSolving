import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to, w;
        Node(int to, int w) { this.to = to; this.w = w; }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int n, m;
    static ArrayList<Node>[] graph;

    static long prim(int start) {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        long total = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.to]) continue;

            visited[cur.to] = true;
            total += cur.w;
            if (++cnt == n) break;

            for (Node next : graph[cur.to]) {
                if (!visited[next.to]) pq.add(next);
            }
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            graph[x].add(new Node(y, z));
            graph[y].add(new Node(x, z));
        }

        System.out.print(prim(1));
    }
}
