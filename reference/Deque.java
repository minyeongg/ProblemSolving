import java.io.*;
import java.util.*;

public class Deque {

  public static void main(String[] args) throws Exception {
    // 앞뒤로 빠르게 데이터를 넣고 뺄 때 (BFS, 큐, 스택 등)
    // O(1)
    // 큐: FIFO
    Deque<Integer> queue = new ArrayDeque<>();
    queue.addLast(11);
    queue.addLast(22);
    queue.addLast(33);
    System.out.println("큐 상태: " + queue);
    System.out.println("꺼낸 값(앞): " + queue.pollFirst()); // 파이썬 popleft
    System.out.println("큐 남음: " + queue);

    // 스택: LIFO
    Deque<Integer> stack = new ArrayDeque<>();
    stack.addLast(100);
    stack.addLast(200);
    System.out.println("꺼낸 값(뒤): " + stack.pollLast()); // pop
    System.out.println("스택 남음: " + stack);
  }
}
