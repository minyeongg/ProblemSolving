import java.io.*;
import java.util.*;

class Main {
    
    static int n;
    static int[][] board1 = new int[22][22];
    static int[][] board2 = new int[22][22];
    static int mx = Integer.MIN_VALUE;
    
    private static void tilt(int dir) {
        for (int d = 0; d < dir; d++) {
            rotate();
        }
        
        for (int i = 0; i < n; i++) {
            int[] tilted = new int[n];
            int idx = 0;
            for (int j = 0; j < n; j++) {
                if (board2[i][j] == 0) continue;
                if (tilted[idx] == 0) {
                    tilted[idx] = board2[i][j];
                } else if (tilted[idx] == board2[i][j]) {
                    tilted[idx++] *= 2;
                } else {
                    tilted[++idx] = board2[i][j];
                }
            }
            
            for (int j = 0; j < n; j++) {
                board2[i][j] = tilted[j];
            }
        }
        
    }
    private static void rotate() {
        int[][] tmp = new int[22][22];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = board2[i][j];
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board2[i][j] = tmp[n-j-1][i];
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board1[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int t = 0; t < 1024; t++) {
            for (int i = 0; i < n; i++) {
                System.arraycopy(board1[i], 0, board2[i], 0, n);
            }
            int turns = 5;
            int brute = t;
            for (int turn = 0; turn < turns; turn++) {
                int dir = brute % 4;
                brute /= 4;
                tilt(dir);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board2[i][j] > mx) mx = board2[i][j];
                }
            }
        }
        
        System.out.println(mx);
    }
}
