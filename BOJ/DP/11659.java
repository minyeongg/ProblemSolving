import java.util.*;
import java.io.*;

class Main {
    static int[] d = new int[100005];
    static int[] s = new int[100005];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        for (int i = 1; i <= n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }
        
        d[1] = s[1];
        
        for (int i = 2; i <= n; i++) {
            d[i] = d[i-1] + s[i];
        }
        
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            bw.write(d[j]-d[i-1] + "\n");
        }
        
        bw.flush();
        bw.close();
    }
}
