#1
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
        int start = 0;
        if (cnt != 0) start = arr[cnt - 1];
        for (int i = start; i < n; i++) {
            arr[cnt] = i;
            func(cnt + 1);
        }
    }
}

#2
  import java.io.*;
import java.util.*;

public class Main {
    static int[] arr = new int[10];
    static int[] num = new int[10];
    static int n, m;
    static StringBuilder sb = new StringBuilder();

    static void func(int k, int st) {
        if (k == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        int tmp = -1;
        for (int i = st; i < n; i++) {
            if (num[i] != tmp) {
                arr[k] = num[i];
                tmp = arr[k];
                func(k + 1, i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num, 0, n);

        func(0, 0);

        System.out.print(sb);
    }
}
