import java.util.*;
import java.io.*;

class Main {
    
    static int[] num = new int[12];
    static StringBuilder sb = new StringBuilder();
    static int[] arr = new int[6];
    static int s;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String str;
        while (!(str = br.readLine()).equals("0")) {
            st = new StringTokenizer(str);
            s = Integer.parseInt(st.nextToken());
            visited = new boolean[s];
            for (int i = 0; i < s; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            func(0);
            sb.append("\n");
        }
        
        System.out.println(sb);
        
    }
    
    private static void func(int cnt) {
        if (cnt == 6) {
            for (int i : arr) {
                sb.append(num[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        int start = 0;
        if (cnt != 0) start = arr[cnt - 1] + 1;
        for (int i = start; i < s; i++) {
            arr[cnt] = i;
            func(cnt + 1);
        }
    }
}
