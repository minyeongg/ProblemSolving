class Edge {
    int x, y, z;
    
    public Edge(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
};

public class PrimMain {
    public static final int INT_MAX = Integer.MAX_VALUE;
    
    // 변수 선언
    // 정점의 수 : 5, 간선의 수 : 7인 그래프
    public static final int n = 5, m = 7;
    public static int[][] graph = new int[n + 1][n + 1];
    public static boolean[] visited = new boolean[n + 1];
    
    public static int[] dist = new int[n + 1];

    public static void main(String[] args) {
        // 주어진 간선 정보 (x, y, z)
        // x <-> y로 향하는 간선이 있으며, 가중치는 z 
        Edge[] edges = new Edge[]{
            new Edge(-1, -1, -1),
            new Edge(2, 1, 2),
            new Edge(1, 4, 3),
            new Edge(4, 2, 1),
            new Edge(5, 2, 4),
            new Edge(5, 4, 2),
            new Edge(4, 3, 2),
            new Edge(1, 3, 6)
        };

        // 그래프를 인접행렬로 표현
        for(int i = 1; i <= m; i++) {
            int x = edges[i].x;
            int y = edges[i].y;
            int z = edges[i].z;
            graph[x][y] = z;
            graph[y][x] = z;
        }
        
        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 1; i <= n; i++)
            dist[i] = (int)1e9;

        // 시작위치에는 dist값을 0으로 설정
        dist[5] = 0;

        // O(|V|^2) 프림 코드
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            // V개의 정점 중 
            // 아직 방문하지 않은 정점 중
            // dist값이 가장 작은 정점을 찾아줍니다.
            int minIndex = -1;
            for(int j = 1; j <= n; j++) {
                if(visited[j])
                    continue;
                
                if(minIndex == -1 || dist[minIndex] > dist[j])
                    minIndex = j;
            }

            // 최솟값에 해당하는 정점에 방문 표시를 진행합니다.
            visited[minIndex] = true;

            // mst 값을 갱신해줍니다.
            ans += dist[minIndex];

            // 최솟값에 해당하는 정점에 연결된 간선들을 보며
            // 시작점으로부터의 최솟값을 갱신해줍니다.
            for(int j = 1; j <= n; j++) {
                // 간선이 존재하지 않는 경우에는 넘어갑니다.
                if(graph[minIndex][j] == 0)
                    continue;

                dist[j] = Math.min(dist[j], graph[minIndex][j]);
            }
        }

        System.out.print(ans);
    }
}
