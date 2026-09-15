import java.io.*;
import java.util.*;

public class Main { 
    static int N, K, L;
    static int[][] map; // 1-based. -1: 물건, 0: 먼지 없음. 1이상: 먼지 있음
    static StringBuilder sb = new StringBuilder();
    static class Robot {
        int x, y;
        Robot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Robot[] robots;
    static int[] dx = {-1, 0, 1, 0, 0}; // 0: 상, 1: 우, 2: 하, 3: 좌, 4: 그대로
    static int[] dy = {0, 1, 0, -1, 0};
    static boolean oob(int x, int y) {
        if (x < 1 || x > N || y < 1 || y > N) return true;
        return false;
    }
    static boolean[][] hasRobot;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        L = Integer.parseInt(input[2]);

        map = new int[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
            }
        }

        robots = new Robot[K];
        for (int i = 0; i < K; i++) {
            input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            robots[i] = new Robot(x, y);
        }

        hasRobot = new boolean[N + 2][N + 2];
        for (Robot robot : robots) {
            int x = robot.x, y = robot.y;
            hasRobot[x][y] = true;
        }

        // L번 동안 반복
        while (L-- > 0) {
            // 1. 청소기 이동
            move();

            // 2. 청소
            clean();

            // 3. 먼지 축적
            accumulate();

            // 4. 먼지 확산
            spread();

            // 5, 먼지량 출력
            printDust();
        }
        
        br.close();
        System.out.println(sb);
            
    }

    // 1. 청소기 이동
    // 오염된 격자로만 이동
    // 방해물: 물건(-1), 다른 청소기
    // 이동 우선순위: 행 번호 작은 순 > 열 번호 작은 순
    static void move() {
        for (int i = 0; i < K; i++) {
            Robot robot = robots[i];
            int x = robot.x, y = robot.y;
            if (map[x][y] > 0) continue;
            int[][] dist = new int[N + 2][N + 2];
            for (int[] row : dist) Arrays.fill(row, -1);

            // 시작점: (x, y)
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[] {x, y});
            dist[x][y] = 0;

            int tx = x, ty = y;
            int mn = Integer.MAX_VALUE; // 가장 가까운 거리

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int cx = cur[0], cy = cur[1];
                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d], ny = cy + dy[d];
                    if (oob(nx, ny)) continue;
                    if (dist[nx][ny] != -1) continue; // 이미 계산한 칸인 경우
                    if (map[nx][ny] == -1) continue; // 물건이 있거나 먼지가 없는 경우 다음 방향 탐색
                    if (hasRobot[nx][ny]) continue; // 로봇이 있는 경우 다음 방향 탐색
                    q.add(new int[] {nx, ny});
                    dist[nx][ny] = dist[cx][cy] + 1;
                    if (map[nx][ny] <= 0) continue; 
                    if (dist[nx][ny] < mn) {
                        mn = dist[nx][ny];
                        tx = nx; ty = ny;
                    }
                    else if (dist[nx][ny] == mn) {
                        if (nx < tx) {
                            tx = nx; ty = ny;
                        }
                        else if (nx == tx) {
                            if (ny < ty) {
                                ty = ny;
                            }
                        }
                    }
                }
            } // 로봇이 이동할 위치를 결정
            hasRobot[x][y] = false;
            robot.x = tx; robot.y = ty;
            hasRobot[tx][ty] = true;
        }
    }

    // 2. 청소
    // 모든 청소기마다
    // ㅗ 모양 모두 청소.
    // 4칸 합 들 중 가장 먼지량 큰 방향에서 청소 시작. 같다면 우 > 하 > 좌 > 상 순
    // 칸마다 최대 20만큼 청소 가능
    static void clean() {
        for (int i = 0; i < K; i++) {
            Robot robot = robots[i];
            int x = robot.x, y = robot.y;
            int targetDir = -1;
            // 0: 상, 1: 우, 2: 하, 3: 좌, 4: 그대로
            int[] dirIndex = {3, 0, 1, 2}; // 제거할 방향 순서
            int mx = Integer.MIN_VALUE;
            for (int d : dirIndex) { 
                int total = 0;
                // 제외 방향: d
                for (int dir = 0; dir < 5; dir++) {
                    if (dir == d) continue;
                    int nx = x + dx[dir], ny = y + dy[dir];
                    if (oob(nx, ny)) continue;
                    if (map[nx][ny] == -1) continue;
                    total += Math.min(map[nx][ny], 20);
                }
                if (total > mx) {
                    mx = total;
                    targetDir = d;
                }
            }

            // targetDir 방향으로 청소
            for (int dir = 0; dir < 5; dir++) {
                if (dir == targetDir) continue;
                int nx = x + dx[dir], ny = y + dy[dir];
                if (oob(nx, ny)) continue;
                if (map[nx][ny] == -1) continue;
                map[nx][ny] -= Math.min(map[nx][ny], 20);
            }
        }
    }

    // 3. 먼지 축적
    // 먼지가 있는 모든 격자에 동시에 먼지량 5씩 추가
    static void accumulate() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] > 0) map[i][j] += 5;
            }
        }
    }

    // 4. 먼지 확산
    // 깨끗한 격자를 대상으로 함
    // 주변 4방향 격자의 먼지량 합 / 10 만큼 확산
    // 모든 깨끗한 격자에 대해 동시에 확산
    static void spread() {
        int[][] delta = new int[N + 2][N + 2]; // 쓰기 배열
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int sum = 0;
                if (map[i][j] == -1 || map[i][j] > 0) continue;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d], ny = j + dy[d];
                    if (oob(nx, ny)) continue;
                    if (map[nx][ny] == -1) continue;
                    sum += map[nx][ny];
                }
                sum /= 10;
                delta[i][j] = sum;
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] += delta[i][j];
            }
        }
    }


    // 5. 전체 공간 먼지량 계산, 출력
    static void printDust() {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] > 0) sum += map[i][j];
            }
        }
        sb.append(sum).append('\n');
    }
}