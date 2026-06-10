import java.io.*;
import java.util.*;

public class Lamda {

  record Player(String name, int score, int year) {}

  public static void main(String[] args) {
    // 람다 정렬
    List<Player> players = new ArrayList<>(List.of(
      new Player("Dana", 90, 1995),
      new Player("Evan", 88, 1994),
      new Player("Finn", 90, 1996)
    ));

    // 1순위: 점수 내림차순, 2순위: 출생년도 오름차순
    players.sort(
      Comparator.comparingInt(Player::score).reversed()
                .thenComparingInt(Player::year)
    );
    System.out.println("다중 조건 정렬:");
    for (Player p : players) {
      System.out.println(" " + p.name() + " / " + p.score() + " / " + p.year());
    }
  }
}
