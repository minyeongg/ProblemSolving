import java.util.*;
import java.io.*;

public class Main {
    static int[] arr;
    static ArrayList<Integer> list;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        list = new ArrayList<>();
        visit = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            visit[i] = true;
            dfs(i, i);
            visit[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        list.stream().forEach(System.out::println);
    }

    private static void dfs(int idx, int st) {
        if (arr[idx] == st) {
            list.add(st);
        }

        if (!visit[arr[idx]]) {
            visit[arr[idx]] = true;
            dfs(arr[idx], st);
            visit[arr[idx]] = false;
        }
    }

}
