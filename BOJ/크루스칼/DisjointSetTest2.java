import java.util.Arrays;

public class DisjointSetTest2 {

    static int N;
    static int[] parents;

    public static void makeSets() {
        parents = new int[N];
        // 모든 원소를 -1로 초기화 (루트 노드이며, 높이가 1임을 의미)
        Arrays.fill(parents, -1);
    }

    public static int findSet(int a) {
        // 음수라면 자기 자신이 루트 노드인 상태
        if (parents[a] < 0) return a;
        
        // Path Compression: 루트 노드를 찾아 부모로 설정하며 재귀 탈출
        return parents[a] = findSet(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        // 이미 같은 집합이라면 합치지 않음
        if (aRoot == bRoot) return false;

        // Union by Rank 최적화 (음수 비교이므로 더 작은 값이 랭크가 더 높음)
        // aRoot의 랭크가 더 큰 경우 (예: -3 < -2)
        if (parents[aRoot] < parents[bRoot]) {
            parents[bRoot] = aRoot; // 작은 집합(bRoot)을 큰 집합(aRoot)에 붙임
        } 
        // bRoot의 랭크가 더 크거나 같은 경우
        else {
            // 두 집합의 랭크가 같다면 합친 후 높이가 1 증가하므로 -1을 더해줌
            if (parents[aRoot] == parents[bRoot]) {
                parents[bRoot]--;
            }
            parents[aRoot] = bRoot; // aRoot를 bRoot 아래로 붙임
        }

        return true;
    }

    public static void main(String[] args) {
        N = 5;
        makeSets();
        
        System.out.println(union(0, 1)); // true
        System.out.println(union(2, 3)); // true
        System.out.println(union(0, 2)); // true
        
        // 결과 확인: 각 원소의 루트 노드 출력
        for (int i = 0; i < N; i++) {
            System.out.println(i + "의 루트: " + findSet(i));
        }
    }
}
