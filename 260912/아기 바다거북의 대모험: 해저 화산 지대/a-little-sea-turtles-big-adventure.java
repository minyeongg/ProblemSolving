import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;  // 0-based 0: 빈 공간 1: 산호초 2: 화석
    static int[][] heat; // 칸의 열기 상태
    static int[][] hasTurtle; // 0: 거북이가 없음. 1~ M: 거북이의 번호
    static int[] turns = new int[102]; // 1-based.
    static Turtle[] turtles; // 1-based
    static Volcano[] volcanoes; // 1-based
    static boolean[] turtleOut;
    static boolean[] exploded;
    static boolean[] rocked;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static class Turtle {
        int id, r, c;
        public Turtle(int id, int r, int c) {
            this.id = id;
            this.r = r;
            this.c = c;
        }
    }

    static class Volcano {
        int r, c, p, cp;
        public Volcano(int r, int c, int p, int cp) {
            this.r = r;
            this.c = c;
            this.p = p;
            this.cp = cp;
        }
    }

    static boolean oob(int r, int c) {
        if (r < 0 || c < 0 || r >= N || c >= N) return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        map = new int[N + 2][N + 2];
        heat = new int[N + 2][N + 2];
        hasTurtle = new int[N + 2][N + 2];
        turtles = new Turtle[M + 1];
        volcanoes = new Volcano[K + 1];
        turtleOut = new boolean[M + 1];
        rocked = new boolean[M + 1];
        exploded = new boolean[K + 1];

        Arrays.fill(turns, -1);
    

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 1; i <= M; i++) {
            input = br.readLine().split(" ");
            int r = Integer.parseInt(input[0]);
            int c = Integer.parseInt(input[1]);
            turtles[i] = new Turtle(i, r, c);
            hasTurtle[r][c] = i;
        }

        for (int i = 1; i <= K; i++) {
            input = br.readLine().split(" ");
            int r = Integer.parseInt(input[0]);
            int c = Integer.parseInt(input[1]);
            int p = Integer.parseInt(input[2]);
            volcanoes[i] = new Volcano(r, c, p, 0);
        }

        // 100턴. 각 턴마다 모든 거북이가 한번씩 최단 경로를 탐색
        for (int turn = 1; turn <= 100; turn++) {

            // 1단계: 바다거북 이동
            for (int id = 1; id <= M; id++) { // 각 거북이마다
                int r = turtles[id].r, c = turtles[id].c;
                if (turtleOut[id]) continue; // 이미 빠져나간 거북이라면 다음 거북이로 넘어가기
                if (rocked[id]) continue; // 화석화된 거북이라면 다음 거북이로 넘어가기
                int[][] dist = new int[N + 2][N + 2]; // 최단거리 배열 만들기
                for (int[] row : dist) Arrays.fill(row, -1); // 아직 방문안한 칸은 -1로 초기화

                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[] {N-1, N-1});
                dist[N-1][N-1] = 0;

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int cr = cur[0], cc = cur[1];
                    for (int d = 0; d < 4; d++) {
                        int nr = cr + dr[d], nc = cc + dc[d]; // 다음 탐색 칸 위치
                        if (oob(nr, nc)) continue; // 범위 밖이면 다음 방향 탐색
                        if (dist[nr][nc] > -1) continue; // 이미 탐색했으면 다음방향 탐색
                        // 장애물: 산호초, 다른 바다거북, 화석
                        if (map[nr][nc] > 0) continue;
                        if (hasTurtle[nr][nc] > 0 && hasTurtle[nr][nc] != id) continue;
                        // 방문할 수 있다면 큐에 넣고 거리 갱신
                        q.add(new int[]{nr, nc});
                        dist[nr][nc] = dist[cr][cc] + 1;
                    }
                } // 최단거리 탐색 완료. 목적지까지의 최단 거리: dist[N-1][N-1]
                
                if (dist[r][c] == -1) continue; // 목적지까지 이동할 수 없다면 다음 거북이로 넘어감

                // 이동할 수 있다면 해당 경로의 첫 번째 칸으로 한 칸 이동
                int[] priority = {3, 2, 1, 0};
                for (int d : priority) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (oob(nr, nc)) continue;
                    if (dist[nr][nc] == dist[r][c] - 1) {
                        hasTurtle[r][c] = 0;
                        r = nr; c = nc;
                        hasTurtle[r][c] = id;
                        turtles[id].r = r; turtles[id].c = c;
                        break;
                    }
                }

                if (r == N - 1 && c == N - 1) {
                    turtleOut[id] = true;
                    hasTurtle[r][c] = 0;
                    turns[id] = turn;
                }
            } // 1단계 끝

            // 2단계: 화산 압력 증가
            volcanoUp();

            // 3-1단계: 열기 전파
            exploded = new boolean[K + 1];
            heatSpread();

            // 3-2단계: 연쇄 분출
            explode();

            // 3-3단계: 화석화
            rock();

            // 4단계: 환경 초기화
            reset();
        }

        StringBuilder sb = new StringBuilder();
        for (int id = 1; id <= M; id++) {
            sb.append(turns[id]).append('\n');
        }

        System.out.println(sb);
        br.close();
    }


    // 2단계: 화산 압력 증가
    static void volcanoUp() {
        for (int i = 1; i <= K; i++) {
            Volcano v = volcanoes[i];
            v.cp += 10;
        }
    }

    // 3-1단계: 열기 전파
        // 읽기용 배열, 쓰기용 배열 두 종류 필요
    static void heatSpread() {
        int[][] heatDelta = new int[N + 2][N + 2]; // 쓰기용 배열, 열기 변화량을 기록
        for (int idx = 1; idx <= K; idx++) {
            Volcano v = volcanoes[idx];
            int vr = v.r, vc = v.c, vp = v.p, vcp = v.cp;
            if (vcp < vp) continue;
            exploded[idx] = true;
            int heat = vp;
            int[][] dist = new int[N + 2][N + 2]; // 현재 화산으로 인한 열기 발생량
            for (int[] row : dist) Arrays.fill(row, -1);

            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[] {vr, vc});
            dist[vr][vc] = heat;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int cr = cur[0], cc = cur[1]; 
                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d], nc = cc + dc[d];
                    if (oob(nr, nc)) continue; // 범위밖이면 다음 방향 탐색
                    if (dist[nr][nc] > -1) continue; // 이미 탐색한 칸이면 다음방향 탐색
                    if (map[nr][nc] == 1) continue; // 산호초를 만나면 다음방향 탐색
                    int newHeat = dist[cr][cc] / 2;
                    if (newHeat > 0) {
                        q.add(new int[] {nr, nc});
                        dist[nr][nc] = newHeat;
                    }
                }
            } // 화산 하나 검토 

            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++) {
                    if (dist[i][j] > 0) heatDelta[i][j] += dist[i][j];
                }
        } // 모든 화산 검토 끝

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                heat[i][j] += heatDelta[i][j];
            }

    }

    // 3-2단계: 연쇄 분출. 종료 조건: 더 이상 새로 분출하는 화산이 없을 때까지
    static void explode() {
        int[][] heatDelta = new int[N + 2][N + 2]; // 쓰기용 배열, 열기 변화량을 기록
        while (true) {
            int explodeCnt = 0;
            for (int idx = 1; idx <= K; idx++) {
                
                if (exploded[idx]) continue;
                Volcano v = volcanoes[idx];
                int vp = v.p, vcp = v.cp, vr = v.r, vc = v.c;
                int totalHeat = heat[vr][vc];
                if (vcp + totalHeat >= vp) { // 아직 분출하지 않은 화산 중, (현재 마그마 압력 + 해당 칸에 누적된 외부 열기) ≥ 분출 임계치(P) 가 되면 해당 화산도 즉시 분출을 시작
                    // 열기 전파
                    explodeCnt++;
                    exploded[idx] = true;
                    int heat = vp;

                    int[][] dist = new int[N + 2][N + 2]; // 현재 화산으로 인한 열기 발생량
                    for (int[] row : dist) Arrays.fill(row, -1);

                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[] {vr, vc});
                    dist[vr][vc] = heat;

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int cr = cur[0], cc = cur[1]; 
                        for (int d = 0; d < 4; d++) {
                            int nr = cr + dr[d], nc = cc + dc[d];
                            if (oob(nr, nc)) continue; // 범위밖이면 다음 방향 탐색
                            if (dist[nr][nc] > -1) continue; // 이미 탐색한 칸이면 다음방향 탐색
                            if (map[nr][nc] == 1) continue; // 산호초를 만나면 다음방향 탐색
                            int newHeat = dist[cr][cc] / 2;
                            if (newHeat > 0) {
                                q.add(new int[] {nr, nc});
                                dist[nr][nc] = newHeat;
                            }
                        }
                    }

                    for (int i = 0; i < N; i++)
                        for (int j = 0; j < N; j++) {
                            if (dist[i][j] > 0) heatDelta[i][j] += dist[i][j];
                        }  
                } // 화산 하나 검토 끝
            } // 모든 화산 검토 끝
            if (explodeCnt == 0) break;
        } // 연쇄 분출 검토 끝

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                heat[i][j] += heatDelta[i][j]; // 열기 업데이트
            }
    }

    // 3-3단계: 화석화
    static void rock() {
        for (int i = 1; i <= M; i++) {
            Turtle turtle = turtles[i];
            int tr = turtle.r, tc = turtle.c;
            if (turtleOut[turtle.id]) continue;
            if (heat[tr][tc] >= 20) {
                // 화석화
                map[tr][tc] = 3;
                rocked[turtle.id] = true;
                hasTurtle[tr][tc] = 0;
            }
        }
    }

    // 4단계: 환경 초기화
    static void reset() {
        heat = new int[N + 2][N + 2];
        for (int idx = 1; idx <= K; idx++) {
            Volcano v = volcanoes[idx];
            if (exploded[idx]) {
                v.cp = 0;
            }
        }
    }

}