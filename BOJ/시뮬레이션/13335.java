import java.io.*;
import java.util.*;

class Main {
    static int[] truck;
    static int weight = 0;
    static int w;
    static int mx;
    static int next = 1;
    static int t;
    static Queue<int[]> q = new LinkedList<>();
    static void checkNext() {
        if (weight + truck[next] <= mx) {
            q.add(new int[]{next, 0});
            weight += truck[next];
            next++;
        }
    }
    static void update() {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int[] p = q.poll();
            p[1]++;
            if (p[1] == w) {
                weight -= truck[p[0]];
            } else {
                q.add(p);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        truck = new int[n + 1];
        w = Integer.parseInt(st.nextToken());
        mx = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            truck[i] = Integer.parseInt(st.nextToken());
        }
        q.add(new int[]{next, 0});
        weight += truck[next];
        next++;
        
        while (true) {
            t++;
            if (next > n && q.isEmpty()) break;
            update();
            if (next <= n) checkNext();
        }
        
        System.out.println(t);
    }
}
