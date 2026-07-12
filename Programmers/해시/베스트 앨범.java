import java.io.*;
import java.util.*;

class 베스트 앨범 {
    // 음악 클래스: 장르, 고유번호, 재생 수
    class Music implements Comparable<Music> {
        String genre;
        int id;
        int plays;
        public Music(String genre, int id, int plays) {
            this.genre = genre;
            this.id = id;
            this.plays = plays;
        }
        @Override
        public int compareTo(Music m) {
            return this.plays == m.plays ?
                this.id - m.id : m.plays - this.plays;
            
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        /*
        장르 별로 스트리밍 횟수 카운트 -> (장르, 총 스트리밍 횟수) 해시맵,
        해시맵 정렬
        정렬한 장르별로 속한 음악 정렬 -> 
            장르별 리스트 만들기
                리스트 별 정렬 기준: 
        
                많이 재생된 노래 두 곡만 순서대로 수록
                    재생횟수가 같으면 고유번호가 낮은 노래를 먼저 수록
        
                노래 수록하기 
        */
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> hm = new HashMap<>();
        int cnt = genres.length;
        for (int i = 0; i < cnt; i++) {
            hm.put(genres[i], hm.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        List<String> keySet = new ArrayList<>(hm.keySet());
        keySet.sort((o1, o2) -> hm.get(o2).compareTo(hm.get(o1)));
        for (String genre : keySet) {
            List<Music> list = new ArrayList<>();
            for (int i = 0; i < cnt; i++) {
                if (genres[i].equals(genre)) {
                    list.add(new Music(genre, i, plays[i]));
                }
            }
            Collections.sort(list);
            if (list.size() == 1) {
                answer.add(list.get(0).id);
            } else {
                answer.add(list.get(0).id);
                answer.add(list.get(1).id);
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
