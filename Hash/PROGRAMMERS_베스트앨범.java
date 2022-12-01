package Hash;

import java.util.*;

class PROGRAMMERS_베스트앨범 {

    public static class Play implements Comparable<Play> {
        int count; // 재생 횟수
        int index; // 곡의 인덱스

        public Play(int count, int index) {
            this.count = count;
            this.index = index;
        }

        @Override
        public int compareTo(Play o) { // 재생 횟수가 많은 순서대로 정렬
            return o.count - this.count;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> genreSums = new HashMap<>(); // key: 장르, value: 총 재생 횟수
        Map<String, List<Play>> genrePlays = new HashMap<>(); // key: 장르, value: 장르의 곡 정보 리스트

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int value = genreSums.getOrDefault(genre, 0);

            genreSums.put(genre, value + plays[i]); // 장르의 재생 횟수 더하기

            // value 리스트 초기화
            if (!genrePlays.containsKey(genre)) {
                genrePlays.put(genre, new ArrayList<>());
            }

            genrePlays.get(genre).add(new Play(plays[i], i)); // 곡 정보 인스턴스 추가
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(genreSums.entrySet());

        entryList.sort((o1, o2) -> o2.getValue() - o1.getValue()); // 총 재생 횟수가 큰 순서대로 정렬

        // 많이 재생된 장르부터 하나씩 꺼내기
        for (Map.Entry<String, Integer> item : entryList) {
            String genre = item.getKey();

            List<Play> playList = genrePlays.get(genre);

            if (playList.size() == 1) { // 장르에 속한 곡이 하나면, 하나의 곡만 선택
                answer.add(playList.get(0).index);
            }
            else {
                Collections.sort(playList);

                answer.add(playList.get(0).index);
                answer.add(playList.get(1).index);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}