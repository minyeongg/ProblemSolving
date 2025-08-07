import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = n - 1;

            while (start < end) {
                if (start == i || end == i) { //본인 이라면
                    if (start == i) start++;
                    else end--;
                } else {
                    int now = arr[start] + arr[end];
                    if (arr[i] == now) { //찾은 경우 
                        cnt++;
                        break;
                    } else if (now < arr[i]) { // 더 큰 숫자가 필요한 경우
                        start++;
                    } else { // 더 작은 숫자가 필요한 경우
                        end--;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}
