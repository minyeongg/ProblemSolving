import java.util.ArrayList;
import java.util.Queue;

class Solution {
  private static ArrayList<Integer>[] adjList;

  private static boolean[] visited;
  private static ArrayList<Integer> answer;

  private static int[] solution(int[][] graph, int start, int n) {
    adjList = new ArrayList[n + 1];
    for (int i = 0; i < adjList.legth; i++) {
      adjList[i] = new ArrayList<>();
    }

    for (int[] edge : graph) {
      adjList[edge[0]].add(edge[1]);
    }

    visited = new boolean[n + 1];
    answer = new ArrayList<>();
    bfs(start);

    return answer.steam().mapTonInt(Integer::inValue).toArray();
  }

  private static void bfs(int start) {
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    visited[start] = true;

    while (!q.isEmpty()) {
      int now = q.poll();
      answer.add(now);
      for (int next : adjList[now]) {
        if (!visited[next]) {
          q.add(next);
          visited[next] = true;
        }
      }
    }
  }
}
