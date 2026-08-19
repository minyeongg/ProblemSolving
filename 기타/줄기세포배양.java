import java.io.*;
import java.util.Scanner;

class data {
    //0: 빈 공간, 1: 비활성 상태, 2: 활성 상태, 3: 죽은 상태
    int status;
    //LP: Life Point
    //HP: 활성 상태: 0, 비활성 상태일 경우 증가, 활성 상태일 경우 감소
    int LP, HP;
}

public class 줄기세포배양 {
    static final int MAXL = 352;
    static int T, N, M, K;
    static data[][][] Map = new data[2][MAXL][MAXL];
    static int[][] dir = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++)
        {
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();
            //init
            for (int i = 0; i < N + K + 2; i++)
                for (int j = 0; j < M + K + 2; j++)
                {
                    Map[0][i][j] = new data();
                    Map[1][i][j] = new data();
                    Map[0][i][j].status = 0;
                    Map[1][i][j].status = 0;
                }

            // 초기 줄기 세포의 Life Point
            for (int i = 0 + K / 2 + 1; i < N + K / 2 + 1; i++)
                for (int j = 0 + K / 2 + 1; j < M + K / 2 + 1; j++)
                {
                    Map[0][i][j].LP = sc.nextInt();
                    if (Map[0][i][j].LP > 0)
                    {
                        Map[0][i][j].status = 1;
                        Map[0][i][j].HP = 0;
                    }
                }

            // 줄기 세포가 K시간 내에 분포할 수 있는 최대 범위
            N = N + K + 2;
            M = M + K + 2;

            int CurMap = 0;
            for (int k = 0; k < K; k++)
            {
                for (int i = 0; i < N; i++)
                    for (int j = 0; j < M; j++)
                    {
                        // 죽은 세포일 경우
                        if (Map[CurMap][i][j].status == 3)
                        {
                            // 아무 움직도 취하지 않음
                            Map[1 - CurMap][i][j].status = Map[CurMap][i][j].status;
                            continue;
                        }
                        // 비활성 상태일 경우
                        else if (Map[CurMap][i][j].status == 1)
                        {
                            // 비활성 시간 증가
                            Map[1 - CurMap][i][j].HP = Map[CurMap][i][j].HP + 1;
                            Map[1 - CurMap][i][j].LP = Map[CurMap][i][j].LP;
                            // 비활성 시간이 LP인를 지날을 경우
                            if (Map[1 - CurMap][i][j].HP == Map[1 - CurMap][i][j].LP)
                                // 활성 상태로 변경
                                Map[1 - CurMap][i][j].status = 2;
                            else
                                Map[1 - CurMap][i][j].status = 1;
                        }
                        // 활성 상태일 경우
                        else if (Map[CurMap][i][j].status == 2)
                        {
                            // 활성 상태가 된 후, 첫 시간일 경우
                            if (Map[CurMap][i][j].HP == Map[CurMap][i][j].LP) //expansion
                            {
                                // 현재케카에서 상, 하, 좌, 우 탐색
                                for (int d = 0; d < 4; d++)
                                {
                                    int i2, j2;
                                    i2 = i + dir[d][0];
                                    j2 = j + dir[d][1];

                                    // 줄기 세포가 번식하는 방향이 비어있을 경우
                                    if (Map[CurMap][i2][j2].status == 0)
                                    {
                                        // 하나의 줄기 세포가 번식하려고 하는 경우
                                        if (Map[1 - CurMap][i2][j2].status == 0)
                                        {
                                            // 해당 줄기 세포가 셀을 차지
                                            Map[1 - CurMap][i2][j2].status = 1;
                                            Map[1 - CurMap][i2][j2].LP = Map[CurMap][i][j].LP;
                                            Map[1 - CurMap][i2][j2].HP = 0;
                                        }
                                        // 두 개 이상의 줄기 세포가 셀에 동시에 번식하려고 하는 경우
                                        // 생명력(LP)가 높은 줄기 세포가 셀을 차지
                                        else if (Map[1 - CurMap][i2][j2].status == 1 && Map[1 - CurMap][i2][j2].LP < Map[CurMap][i][j].LP)
                                            Map[1 - CurMap][i2][j2].LP = Map[CurMap][i][j].LP;
                                    }

                                }

                            }

                            // 활성 시간 증가
                            Map[1 - CurMap][i][j].HP = Map[CurMap][i][j].HP - 1;
                            // 활성 상태 시간이 LP 시간 만큼 지날을 경우
                            if (Map[1 - CurMap][i][j].HP == 0)
                                // 줄기 세포가 죽음
                                Map[1 - CurMap][i][j].status = 3;
                            else
                                Map[1 - CurMap][i][j].status = 2;
                        }

                    }
                // 다음 상태를 저장해 놓은 map을 현재 map으로 변경
                CurMap = 1 - CurMap;
            }

            // 살아 있는 줄기 세포 탐색
            int Answer = 0;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                {
                    if (Map[CurMap][i][j].status == 1 || Map[CurMap][i][j].status == 2)
                        Answer++;
                }

            System.out.printf("#%d %d\n", tc, Answer);
        }
    }
}
