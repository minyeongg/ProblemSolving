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
        
        int[] tmp = new int[n];
        arr = new int[m];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tmp[i] = Integer.parseInt(st.nextToken());
        }
        
        Set<Integer> set = new HashSet<>();
        for (int x : tmp) set.add(x);
        
        num = set.stream().sorted().mapToInt(Integer::intValue).toArray();
        n = num.length;

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
        for (int i = 0; i < n; i++) {
            arr[cnt] = i;
            func(cnt + 1);
        }
    }
}
