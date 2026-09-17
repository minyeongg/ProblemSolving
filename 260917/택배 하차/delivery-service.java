import java.io.*;
import java.util.*;

public class Main {

    static class Box implements Comparable<Box> {
        int id, h, w, r, c, status; // status가 1이면 존재, 0이면 하차됨
        Box(int id, int h, int w, int c, int status) {
            this.id = id;
            this.h = h;
            this.w = w;
            this.c = c;
            this.status = status;
        }
        @Override
        public int compareTo(Box b) {
            return this.id - b.id;
        }
    }
    static int[][] hasBox; // 1-based, 0: 택배 상자 없음 1 ~ K: 택배 상자의 번호
    static int N, M;
    static List<Box> boxes = new ArrayList<>();
    static int removedCnt = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        hasBox = new int[N + 2][N + 2];
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int k = Integer.parseInt(input[0]);
            int h = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);
            int c = Integer.parseInt(input[3]);
            boxes.add(new Box(k, h, w, c, 1));
        }


        // 1. 택배 투입
        for (Box box : boxes) {
            if (box.status == 1) arrange(box);
        }

        while (removedCnt < M) {
            int prevCnt = removedCnt;

            // 2. 택배 하차 (좌측)
            removeLeft();

            // 3. 택배 하차 (우측)
            removeRight();

            if (prevCnt == removedCnt) {
                break;
            }
        }

        br.close();
        System.out.println(sb);

    }

    // 1. 택배 투입
    static void arrange(Box box) {
        // 택배 상자의 각 열 중에서 하한선이 가장 높은 행이 하한임
        int id = box.id, h = box.h, w = box.w, c = box.c;
        int colEnd = c + w - 1;
        int lowerLimit = N;
        for (int col = c; col <= colEnd; col++) {
            for (int row = 1; row <= N; row++) {
                if (hasBox[row][col] > 0) {
                    lowerLimit = Math.min(lowerLimit, row - 1);
                    break;
                }
            }
        }

        int rowStart = lowerLimit - h + 1;
        box.r = rowStart;

        for (int row = rowStart; row <= lowerLimit; row++) {
            for (int col = c; col <= colEnd; col++) {
                hasBox[row][col] = id;
            }
        }   
    }

    // 2. 택배 하차 (좌측)
    static void removeLeft() {
        Box target = null;
        PriorityQueue<Box> pq = new PriorityQueue<>();
        for (Box box : boxes) {
            if (box.status == 0) continue;
            pq.add(box);
        }

        boolean possible = true;
        while (!pq.isEmpty()) {
            Box cur = pq.poll();
            possible = true;
            int id = cur.id, h = cur.h, w = cur.w, r = cur.r, c = cur.c;
            // 각 행마다 좌측에 다른 상자가 없는지 확인
            outer: for (int row = r; row <= r + h - 1; row++) {
                    for (int col = c - 1; col >= 1; col--) {
                        if (hasBox[row][col] > 0) {
                            possible = false;
                            break outer;
                        }                  
                    }
            }

            if (possible) {
                target = cur;
                break;
            }
        }

        if (target != null) {
            int id = target.id, h = target.h, w = target.w, r = target.r, c = target.c;
            for (int row = r; row <= r + h - 1; row++) {
                for (int col = c; col <= c + w - 1; col++) {
                    hasBox[row][col] = 0;
                }
            }
            target.status = 0;
            removedCnt++;
            sb.append(id).append('\n');
            
            rearrangeAll();
        }
    }

    // 3. 택배 하차 (우측)
    static void removeRight() {
        Box target = null;
        PriorityQueue<Box> pq = new PriorityQueue<>();
        for (Box box : boxes) {
            if (box.status == 0) continue;
            pq.add(box);
        }

        boolean possible = true;
        while (!pq.isEmpty()) {
            Box cur = pq.poll();
            possible = true;
            int id = cur.id, h = cur.h, w = cur.w, r = cur.r, c = cur.c;
            // 각 행마다 우측에 다른 상자가 없는지 확인
            outer: for (int row = r; row <= r + h - 1; row++) {
                    for (int col = c + w; col <= N; col++) {
                        if (hasBox[row][col] > 0) {
                            possible = false;
                            break outer;
                        }                  
                    }
            }

            if (possible) {
                target = cur;
                break;
            }
        }

        if (target != null) {
            int id = target.id, h = target.h, w = target.w, r = target.r, c = target.c;
            for (int row = r; row <= r + h - 1; row++) {
                for (int col = c; col <= c + w - 1; col++) {
                    hasBox[row][col] = 0;
                }
            }
            target.status = 0;
            removedCnt++;
            sb.append(id).append('\n');
            
            rearrangeAll();
        }
    }

    // 헬퍼함수: 택배 중력 작용
    static void rearrangeAll() {
        for (int[] row : hasBox) Arrays.fill(row, 0);
        for (Box box : boxes) {
            if (box.status == 1) arrange(box);
        }
    }
}