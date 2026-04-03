import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, weight;
    Edge(int u, int v, int weight) {
        this.u = u; this.v = v; this.weight = weight;
    }
    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

public class Main {
	
	static int N, M;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static boolean[][] v;
	static boolean oob(int x, int y) {
		return (x < 0 || y < 0 || x >= N || y >= M);
	}
	static int[] p;
	static void bfs(int r, int c, int idx) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        v[r][c] = true;
        map[r][c] = idx;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (!oob(nr, nc) && !v[nr][nc] && map[nr][nc] == 1) {
                    v[nr][nc] = true;
                    map[nr][nc] = idx;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }
	
	static void makeSet(int size) {
		p = new int[size + 1];
		Arrays.fill(p, -1);
	}
	
	static boolean union(int u, int v) {
		int U = find(u);
		int V = find(v);
		if (U == V) return false;
		if (p[U] < p[V]) {
			p[V] = U;
		} else if (p[U] > p[V]) {
			p[U] = V;
		} else {
			p[V] = U;
			p[U]--;
		}
		return true;
	}
	
	static int find(int x) {
		if (p[x] < 0) return x;
		return p[x] = find(p[x]);
	}
	
	static void makeBridges(int r, int c, int islandIdx) {
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			int len = 0;
			
			while (!oob(nr, nc)) {
				if (map[nr][nc] == islandIdx) break;
				if (map[nr][nc] > 0) {
					if (len >= 2) pq.add(new Edge(islandIdx, map[nr][nc], len));
					break;
				}
				len++;
				nr += dr[dir];
				nc += dc[dir];
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1단계 : 섬 라벨링
		v = new boolean[N][M];
		int islandIdx = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !v[i][j]) {
					bfs(i, j, ++islandIdx); // 섬 라벨링
				}
			}
		}
		
		// 2단계: 다리 찾기
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) makeBridges(i, j, map[i][j]);
            }
        }
		
		// 3단계: MST (크루스칼)
		makeSet(islandIdx);
		int mst = 0, cnt = 0;
		
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (union(edge.u, edge.v)) {
				mst += edge.weight;
				cnt++;
			}
		}
		
		if (cnt == islandIdx - 1) {
			System.out.println(mst);
		} else {
			System.out.println(-1);
		}
		
	}
	
}
