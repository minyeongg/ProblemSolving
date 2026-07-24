import java.io.*;
import java.util.*;

class 양과 늑대 {
  private static class Info {
    int node, sheep, wolf;
    HashSet<Integer> visited;

    public Info(int node, int sheep, int wolf, HashSet<Integer> visited) {
      this.node = node;
      this.sheep = sheep;
      this.wolf = wolf;
      this.visited = visited;
    }
  }

  private static ArrayList<Integer>[] tree;

  private static void buildTree(int[] info, int[][] edges) {
    tree = new ArrayList[info.length];
    for (int i = 0; i < tree.length; i++) {
      tree[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      tree[edge[0]].add(edge[1]);
    }
  }
  public int solution(int[] info, int[][] edges) {
    buildTree(info, edges);
    int answer = 0;

    ArrayDeque<Info> q = new ArrayDeque<>();
    q.add(new Info(0, 1, 0, new HashSet<>()));

    while (!q.isEmpty()) {
      Info now = q.poll();
      answer = Math.max(answer, now.sheep);
      now.visited.addAll(tree[now.node]);

      for (int next : now.visited) {
         HashSet<Integer> set = new HashSet<>(now.visited);
         set.remove(next);

        if (info[next] == 1) {
          if (now.sheep != now.wolf + 1) {
            q.add(new Info(next, now.sheep, now.wolf + 1, set));
          }
        }
        else {
          q.add(new Info(next, now.sheep + 1, now.wolf, set));
        }
      }
    }

    return answer;
  }
}



import java.io.*;
import java.util.*;

class 양과 늑대 {
  private int answer = 0;
  private int[] info;
  private List<Integer>[] tree;

  public int solution(int[] info, int[][] edges) {
      tree = new ArrayList[info.length];
      this.info = info;
      for (int i = 0; i < tree.length; i++) {
          tree[i] = new ArrayList<>();
      }
      for (int[] edge : edges) {
          tree[edge[0]].add(edge[1]);
      }

      Set<Integer> cand = new HashSet<>(trees[0]);
      dfs(cand, 1, 0);
      return answer;
  }
  private void dfs(Set<Integer> cand, int sheep, int wolf) {
      answer = Math.max(answer, sheep);

      for (int next : new ArrayList<>(cand)) {
          int ns = sheep + (info[next] == 0 ? 1: 0);
          int nw = wolf + (info[next] == 1 ? 1: 0);
          if (ns <= nw) continue;

          cand.remove(next);
          cand.addAll(tree[next]);
          dfs(cand, ns, nw);
          cand.removeAll(tree[next]);
          cand.add(next);
      }
  }
}


/*
[신호] N ≤ 17, 트리, 방문한 곳은 자유 이동, 순서 제약 없음
[기법] 상태 = 현재 위치가 아니라 "방문 집합" (경계로 관리)
[왜]   방문 노드는 통행 자유 → 위치 무의미.
       위치는 자식을 꺼낼 때만 쓰이고 그 뒤론 죽은 값.
[문제] 프로그래머스 양과 늑대

[막힌 곳] 재귀 계약을 안 정하고 코드부터 씀
[증상]    dfs(0,0,0)은 "안 셈" 가정, answer 갱신은 "셌음" 가정
          → 세 줄이 서로 다른 전제로 굴러가서 다음 줄이 안 나옴
[처방]    재귀는 코드 전에 주석 세 줄부터
          // 진입 시점 가정 / 하는 일 / 나갈 때 상태
[재발]    (다음에 또 걸리면 여기 정(正) 표시)

*/
