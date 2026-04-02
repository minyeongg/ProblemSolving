import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int weight, start, end;

    public Edge(int weight, int start, int end) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Edge e) {
        return this.weight - e.weight; // 비용 기준 오름차순 정렬을 진행합니다.
    }
}

public class Main {
    public static final int MAX_M = 100000;
    public static final int MAX_N = 10000;
    
    // 변수 선언
    public static int n, m;
    
    public static int[] p = new int[MAX_N + 1]; // 부모노드 배열
    
    public static int find(int x) {
        if(p[x] < 0)
            return x;
        return p[x] = find(p[x]); // 루트노드가 될 때까지 탐색
    }
    
    public static void union(int x, int y) {
        int X = find(x);
        int Y = find(y);
        if (X >= Y) {
            p[x] = p[y];
            Y--;
        } else {
            p[y] = p[x];
            X--;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 모든 노드는 루트노드
        Arrays.fill(p, -1);

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.add(new Edge(weight, x, y));
        }

        int mst = 0; int cnt = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int start = cur.start;
            int end = cur.end;
            int weight = cur.weight;

            if (find(start) == find(end)) continue;
            union(start, end);
            mst += weight;
            if (++cnt == n - 1) break;
        }

        System.out.println(mst);
    }
}
