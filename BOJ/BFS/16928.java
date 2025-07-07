import java.io.*;
import java.util.*;
public class Main{
    static int arr[],N,M;
    static boolean visit[];
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[101];
        visit = new boolean[101];
        
        for(int i=0; i<N+M; i++){
            st = new StringTokenizer(br.readLine());
            int x =Integer.parseInt(st.nextToken());
            int y =Integer.parseInt(st.nextToken());
            arr[x]=y;
        }
        
        bfs(1);
    }
    static void bfs(int start){
        Queue<int[]>q=new LinkedList<>();
        q.add(new int[]{start,0});
        visit[start]=true;
        
        while(!q.isEmpty()){
            int p[] = q.poll();
            
            if(p[0]==100){
                System.out.println(p[1]);
                return;
            }
            
            for(int i=1; i<7; i++){
                int next = p[0]+i;
                if(next<=100){
                    if(arr[next]!=0){
                        next = arr[next];
                    }
                    if(!visit[next]){
                        visit[next] = true;
                        q.add(new int[]{next,p[1]+1});
                        
                    }
                }
            }
        }
    }
}
