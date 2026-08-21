import java.io.*;
import java.util.*;
import java.lang.*;

public class 활주로건설 {
    static int N, X;
    static int[][] map;
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        // 정답 변수(answer)를 0으로 초기화한다.
        int answer = 0;
        //테스트 케이스(T)만큼 반복한다:
        for (int t = 1; t <= T; t++) {
            answer = 0;
            //    지도의 크기 N과 경사로의 길이 X를 입력받는다.
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            X = Integer.parseInt(input[1]);

            map = new int[N][N];

            //    N x N 크기의 지형(map) 데이터를 입력받는다.
            for (int i = 0; i < N; i++) {
                String[] arr = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(arr[j]);
                }
            }
            //    // 1. 가로 줄 검사
            //    모든 행(i)을 0부터 N-1까지 반복한다:
            for (int i = 0; i < N; i++) {
                //        해당 행의 숫자들을 추출하여 1차원 배열(line)을 만든다.
                int[] line = new int[N];
                for (int j = 0; j < N; j++) {
                    line[j] = map[i][j];
                }
                //        만약 헬퍼함수(canBuild(line))가 참(True)을 리턴하면:
                //            answer를 1 증가시킨다.
                if (canBuild(line)) answer++;
            }

            //    // 2. 세로 줄 검사
            //    모든 열(j)을 0부터 N-1까지 반복한다:
            for (int j = 0; j < N; j++) {
                //        해당 열의 숫자들을 추출하여 1차원 배열(line)을 만든다.
                int[] line = new int[N];
                for (int i = 0; i < N; i++) {
                    line[i] = map[i][j];
                }
                //        만약 헬퍼함수(canBuild(line))가 참(True)을 리턴하면:
                //            answer를 1 증가시킨다.
                if (canBuild(line)) answer++;
            }
            //    현재 테스트 케이스의 결과(answer)를 출력한다.
            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }

        System.out.println(sb);
    }

    //-------------------------------------------------------------
    //[헬퍼 함수] canBuild(line):
    static boolean canBuild(int[] line) {
        //경사로 설치 여부를 기록할 배열(used)을 크기 N으로 만들고 모두 False로 초기화한다.
        boolean[] used = new boolean[N];

        //인덱스 i를 0부터 N-2까지 반복한다:
        for (int i = 0; i < N - 1; i++) {
            //    현재 칸(line[i])과 다음 칸(line[i+1])의 높이가 같다면:
            //        아무것도 하지 않고 넘어간다.
            if (line[i] == line[i + 1]) continue;

            //    높이 차이가 2 이상이라면:
            //        활주로 건설 불가능하므로 False를 리턴한다.
            //
            if (Math.abs(line[i] - line[i + 1]) >= 2) return false;

            //    오르막인 경우 (현재 < 다음, 차이가 1)
            //    만약 line[i] + 1 == line[i+1] 이라면:
            if (line[i] + 1 == line[i+1]) {
                //        현재 칸(i)을 기준으로 왼쪽으로 길이 X만큼의 구간을 확인한다:
                for (int j = i; j > i - X; j--) {
                    // 범위를 벗어나거나, 높이가 다르거나, 이미 경사로가 설치된 곳(used)이 있다면:
                    // False를 리턴한다.
                    if (j < 0 || line[j] != line[i] || used[j]) return false;

                }
                // 조건을 통과했다면, 해당 구간의 used 배열을 True로 체킹한다.

                for (int j = i; j > i - X; j--) {
                    used[j] = true;
                }
            }

            //    내리막인 경우 (현재 > 다음, 차이가 1)
            //    만약 line[i] - 1 == line[i+1] 이라면:
            if (line[i] - 1 == line[i+1]) {
                // 다음 칸(i+1)을 기준으로 오른쪽으로 길이 X만큼의 구간을 확인한다:
                for (int j = i + 1; j <= i + X; j++) {
                    //  범위를 벗어나거나, 높이가 다르거나, 이미 경사로가 설치된 곳(used)이 있다면:
                    //  False를 리턴한다.
                    if (j >= N || line[j] != line[i] - 1 || used[j]) return false;
                }
                // 조건을 통과했다면, 해당 구간의 used 배열을 True로 체킹한다.
                for (int j = i + 1; j <= i + X; j++) {
                    used[j] = true;
                }
            }
        }

        //모든 검사를 무사히 통과했다면 True를 리턴한다.
        return true;
    }
}
