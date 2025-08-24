import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        int[] dist = new int[100001];
        boolean[] visited = new boolean[100001];
        
        Queue<Integer> q = new LinkedList<>();
        
        q.add(n);
        visited[n] = true;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            if (cur == k) {
                System.out.println(dist[cur]);
                return;
            }
            int twice = cur * 2;
            if (twice >= 0 && twice <= 100000) {
                 if (!visited[twice]) {
                    dist[twice] = dist[cur];
                    q.add(twice);
                    visited[twice] = true;
                 }
            }
            if (twice == k) {
                System.out.println(dist[twice]);
                return;
            }
            for (int dir = 0; dir < 2; dir++) {
                int nx = -1;
                if (dir == 0) {
                    nx = cur - 1;
                }
                else if (dir == 1) {
                    nx = cur + 1;
                }
                if (nx == k) {
                    System.out.println(dist[cur] + 1);
                    return;
                }
                if (nx < 0 || nx > 100000) continue;
                if (visited[nx]) continue;
                dist[nx] = dist[cur] + 1;
                q.add(nx);
                visited[nx] = true;
            }
        }
     }
}
