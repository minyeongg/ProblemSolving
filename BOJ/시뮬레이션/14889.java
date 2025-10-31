import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] A;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] team = new int[N];
        Arrays.fill(team, N / 2, N, 1);
        
        do {
            calculateDifference(team);
        } while (nextPermutation(team));
        
        System.out.println(minDiff);
    }

    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if (i <= 0) return false;
        
        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1]) j--;
        
        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;
        
        j = arr.length - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return true;
    }

    static void calculateDifference(int[] team) {
        int teamAScore = 0;
        int teamBScore = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (team[i] == team[j]) {
                    if (team[i] == 0) {
                        teamAScore += A[i][j] + A[j][i];
                    } else {
                        teamBScore += A[i][j] + A[j][i];
                    }
                }
            }
        }

        int diff = Math.abs(teamAScore - teamBScore);
        minDiff = Math.min(minDiff, diff);
    }
}
