/**
3 3
1 2 1
2 3 2
1 3 3
*/
import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int cost, index;
    Node(int cost, int index) {
        this.cost = cost;
        this.index = index;
    }
    @Override
    public int compareTo(Node n) {
        return this.cost - n.cost;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] dist = new int[N + 1]; // mst로부터 노드까지의 최단거리
        Arrays.fill(dist, (int)1e9);
        boolean[] v = new boolean[N + 1];

        ArrayList<Node>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(cost, end));
            graph[end].add(new Node(cost, start));
        }

        int mst = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 시작노드로부터의 거리를 오름차순 정렬
        dist[1] = 0;
        pq.add(new Node(dist[1], 1));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int index = node.index;
            int cost = node.cost;
            if (v[index]) continue;
            if (dist[index] != cost) continue;
            mst += cost;
            v[index] = true;
            for (int i = 0; i < graph[index].size(); i++) {
                int next = graph[index].get(i).index;
                int nextDist = graph[index].get(i).cost; // 방금 추가된 노드로부터 다음도느까지의 거리
                if (v[next]) continue;
                if (dist[next] > nextDist) {
                    dist[next] = nextDist;
                    pq.add(new Node(dist[next], next));
                }
            }
        }
        
        System.out.println(mst);

    }
}