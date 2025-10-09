import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][] note = new int[42][42]; 
    static int r, c;
    static int[][] paper = new int[12][12]; 

    static void rotate() {
        int[][] tmp = new int[12][12];
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                tmp[i][j] = paper[i][j];
            }
        }
        
        int newR = c;
        int newC = r;
        
        for (int i = 0; i < newR; i++) {
            for (int j = 0; j < newC; j++) {
                paper[i][j] = tmp[r - 1 - j][i]; 
            }
        }

        r = newR;
        c = newC;
    }

    static boolean pastable(int x, int y) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (paper[i][j] == 1 && note[x + i][y + j] == 1) {
                    return false;
                }
            }
        }
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (paper[i][j] == 1) {
                    note[x + i][y + j] = 1;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        for (int sticker = 0; sticker < k; sticker++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    paper[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            for (int rot = 0; rot < 4; rot++) {
                boolean is_paste = false; 
                
                for (int x = 0; x <= n - r; x++) {
                    if (is_paste) break; 
                    
                    for (int y = 0; y <= m - c; y++) {
                        if (pastable(x, y)) {
                            is_paste = true;
                            break; 
                        }
                    }
                }
                
                if (is_paste) break; 
                
                rotate();
            }
        }
        
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cnt += note[i][j];
            }
        }
        
        System.out.println(cnt);
    }
}
