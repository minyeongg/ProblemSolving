import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static List<String> ans;
    static String op[] = {"+", "-", " "};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            ans = new ArrayList<>();
            dfs(1, "1");
            Collections.sort(ans);
            for (String s : ans) {
                System.out.println(s);
            }
            System.out.println();
        }
    }
    
    private static void dfs(int num, String s) {
        if (num == n) {
            String express = s.replaceAll(" ", "");
            if (cal(express))
                ans.add(s);
            return;
        }
        for (int i = 0; i < 3; i++) {
            dfs(num + 1, s + op[i] + (num + 1));
        }
    }
    
    private static boolean cal(String express) {
        StringTokenizer st = new StringTokenizer(express, "-|+", true);
        int sum = Integer.parseInt(st.nextToken());
        while (st.hasMoreElements()) {
            String s = st.nextToken();
            if (s.equals("+")) {
                sum += Integer.parseInt(st.nextToken());
            } else {
                sum -= Integer.parseInt(st.nextToken());
            }
        }
        if (sum == 0)
            return true;
        return false;
    }
}
