import java.util.*;
import java.io.*;

class Main {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        function(0, 0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(map[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void function(int x, int y, int n) {
        if (n == 1) {
            map[x][y] = '*';
            return;
        }
        int newN = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    for (int l = x + i * newN; l < x + (i + 1) * newN; l++) {
                        for (int m = y + j * newN; m < y + (j + 1) * newN; m++) {
                            map[l][m] = ' ';
                        }
                    }
                    continue;
                }
                function(x + i * newN, y + j * newN, newN);
            }
        }
    }
}
