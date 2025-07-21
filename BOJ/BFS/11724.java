import java.util.*;
import java.io.*;

class Main {
    
    static int N, M;
    static boolean[] visited;
    static List<Integer>[] graph;
    static int cnt = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                q.add(i);
                visited[i] = true;
                cnt++;
            }
            while(!q.isEmpty()) {
                int cur = q.poll();
                for (int node : graph[cur]) {
                    if (!visited[node]) {
                        q.add(node);
                        visited[node] = true;
                    }
                }
            }
        }
        
        System.out.println(cnt);
    }
}
