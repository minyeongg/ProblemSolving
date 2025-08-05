import java.util.*;

class Main {
    
    static int[] d = new int[11];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
      
        d[1] = 1; d[2] = 2; d[3] = 4;
        for (int i = 4; i < 11; i++) {
            d[i] = d[i-1] + d[i-2] + d[i-3];
        }
        
        while (T-- > 0) {
            int n = sc.nextInt();
            System.out.println(d[n]);
        }
    }
}
