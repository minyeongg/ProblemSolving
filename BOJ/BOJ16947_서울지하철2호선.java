import java.io.*;
import java.util.*;

public class BOJ16947_서울지하철2호선 {

    static int N;
    static boolean[] v;
    static boolean[] onCycle;
    static int[] dist;
    static List<Integer>[] graph;

    static int dfs(int cur, int prev) {
        v[cur] = true;

        for (int next : graph[cur]) {
            if (next == prev) continue;

            if (v[next]) {
                onCycle[cur] = true;
                return next;
            }

            int msg = dfs(next, cur);
            if (msg == -2) return -2;
            if (msg > 0) {
                onCycle[cur] = true;
                if (msg == cur) return -2;
                return msg;
            }
        }

        return -1;
    }

    static void bfs() {
        Arrays.fill(dist, -1);
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (onCycle[i]) {
                dist[i] = 0;
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph[cur]) {
                if (dist[next] < 0) {
                    dist[next] = dist[cur] + 1;
                    q.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new boolean[N + 1];
        onCycle = new boolean[N + 1];
        dist = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0);

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dist[i]).append(' ');
        }

        System.out.println(sb);
        br.close();
    }
}
