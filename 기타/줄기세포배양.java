import java.io.*;
import java.util.*;

class Data {
    int status; // {0: 빈 칸, 1: 비활성화 상태, 2: 활성화 상태, 3: 죽은 상태
    int LP, HP; // LP: 주어진 생명력, HP: 현재 남은 생명력
}
public class 줄기세포배양 {
    static int N, M, K;
    static final int MAXL = 352;
    static Data[][][] Map = new Data[2][MAXL][MAXL];;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            K = Integer.parseInt(input[2]);

            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < MAXL; i++) {
                    for (int j = 0; j < MAXL; j++) {
                        Map[k][i][j] = new Data();
                    }
                }
            }

            for (int i = 0 + K / 2 + 1; i < N + K / 2 + 1; i++) {
                String[] arr = br.readLine().split(" ");
                for (int j = 0 + K / 2 + 1; j < M + K / 2 + 1; j++) {
                    Map[0][i][j].LP = Integer.parseInt(arr[j - (K / 2 + 1)]);
                    if (Map[0][i][j].LP > 0) {
                        Map[0][i][j].status = 1;
                    }
                }
            }

            int CurMap = 0;

            for (int k = 0; k < K; k++) {
                for (int i = 0; i < MAXL; i++) {
                    for (int j = 0; j < MAXL; j++) {
                        if (Map[CurMap][i][j].status == 3) {
                            Map[1 - CurMap][i][j].status = Map[CurMap][i][j].status;
                        }

                        else if (Map[CurMap][i][j].status == 2) {
                            // 번식 조건: HP가 활성화된 다음 턴 (== LP + 1)
                            if (Map[CurMap][i][j].HP == Map[CurMap][i][j].LP) {
                                for (int dir = 0; dir < 4; dir++) {
                                    int ni = i + dx[dir];
                                    int nj = j + dy[dir];
                                    if (Map[1 - CurMap][ni][nj].status == 0) { // 비어있을 경우 그냥 복제
                                        Map[1 - CurMap][ni][nj].status = 1;
                                        Map[1 - CurMap][ni][nj].LP = Map[CurMap][i][j].LP;
                                        Map[1 - CurMap][ni][nj].HP = 0;
                                    }
                                    else if (Map[1 - CurMap][ni][nj].status == 1 && Map[1 - CurMap][ni][nj].HP == 0 && Map[1 - CurMap][ni][nj].LP < Map[CurMap][i][j].LP) {
                                        Map[1 - CurMap][ni][nj].LP = Map[CurMap][i][j].LP;
                                    }
                                }
                            }

                            Map[1 - CurMap][i][j].HP = Map[CurMap][i][j].HP - 1;
                            Map[1 - CurMap][i][j].LP = Map[CurMap][i][j].LP;
                            if (Map[1 - CurMap][i][j].HP == 0) {
                                Map[1 - CurMap][i][j].status = 3;

                            }
                            else {
                                Map[1 - CurMap][i][j].status = 2;
                            }
                        }

                        else if (Map[CurMap][i][j].status == 1) {
                            Map[1 - CurMap][i][j].HP = Map[CurMap][i][j].HP + 1;
                            Map[1 - CurMap][i][j].LP = Map[CurMap][i][j].LP;
                            if (Map[1 - CurMap][i][j].HP == Map[1 - CurMap][i][j].LP) {
                                Map[1 - CurMap][i][j].status = 2;
                            }
                            else {
                                Map[1 - CurMap][i][j].status = 1;
                            }
                        }
                    }
                }

                CurMap = 1 - CurMap;
            }

            int answer = 0;

            for (int i = 0; i < MAXL; i++) {
                for (int j = 0; j < MAXL; j++) {
                    if (Map[CurMap][i][j].status == 1 || Map[CurMap][i][j].status == 2) {
                        answer++;
                    }
                }
            }

            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }

        System.out.println(sb);
        br.close();
    }
}
