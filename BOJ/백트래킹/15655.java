import java.util.*;
import java.io.*;

class Main {
    
    static int n, m;
    static int[] num;    // 입력 받은 숫자 저장
    static int[] arr;    // 선택한 숫자 저장
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        num = new int[n];
        arr = new int[m];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num); // 정렬
        func(0);

        System.out.print(sb);
    }
    
    private static void func(int cnt) throws IOException {
        if (cnt == m) {
            for (int i : arr) {
                sb.append(num[i] + " ");
            }
            sb.append("\n");
            return;
        }
        int start = 0;
        if (cnt != 0) {
            start = arr[cnt - 1] + 1;
        }
        for (int i = start; i < n; i++) {
                arr[cnt] = i;
                func(cnt + 1);
            }
        }
}
