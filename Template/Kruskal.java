import java.io.*;
import java.util.*;

public class Kruska {
    static class Edge implements Comparable<Edge> {
        int from, to, w;
        Edge(int from, int to, int w) { this.from = from; this.to = to; this.w = w; }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int v, e;
    static int[] parent;   // 루트면 -(집합 크기), 아니면 부모 정점

    static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);     // 경로 압축
    }

    static boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;             // 같은 집합 → 사이클

        if (parent[ra] > parent[rb]) {          // ra 쪽이 더 작으면 교체
            int tmp = ra; ra = rb; rb = tmp;
        }
        parent[ra] += parent[rb];               // 크기 합치기
        parent[rb] = ra;
        return true;
    }

    static long kruskal(PriorityQueue<Edge> pq) {
        parent = new int[v + 1];
        Arrays.fill(parent, -1);

        long total = 0;
        int cnt = 0;   // MST에 추가한 간선 수
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (!union(cur.from, cur.to)) continue;

            total += cur.w;
            if (++cnt == v - 1) break;
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            pq.add(new Edge(x, y, z));
        }

        System.out.print(kruskal(pq));
    }
}
