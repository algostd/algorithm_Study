package com.wanted_server.Class;

import java.util.*;

public class 베스트앨범 {
    /*
    Map<장르, 횟수> 횟수는 계속 갱신
    Map<장르, PriorityQueue<Song>> 정렬
    class(int id, int playingTimes)
     */
    static class Song {
        int id;
        int playingTimes;

        public Song(int id, int playingTimes) {
            this.id = id;
            this.playingTimes = playingTimes;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        LinkedList<Integer> answerList = new LinkedList<>();

        HashMap<String, Integer> countMap = new HashMap<>();
        HashMap<String, PriorityQueue<Song>> songMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            // 장르별 재생횟수 갱신
            String genre = genres[i];
            int play = plays[i];
            if (countMap.containsKey(genre)) {
                countMap.put(genre, countMap.get(genre) + play);
            } else {
                countMap.put(genre, play);
            }

            // 가장 많이 플레이된 노래로 갱신
            if (songMap.get(genre) == null) {
                PriorityQueue<Song> songArrayList = new PriorityQueue<Song>((a,b) ->{
                    if (a.playingTimes == b.playingTimes) {
                        return b.id - a.id;
                    }
                    return a.playingTimes - b.playingTimes;
                });
                songArrayList.add(new Song(i, play));
                songMap.put(genre, songArrayList);
            } else if (songMap.get(genre).size() == 1) {
                songMap.get(genre).add(new Song(i,play));
            } else { // 2개 이상일때
                songMap.get(genre).add(new Song(i, play));
                songMap.get(genre).poll();
            }
        }
        // 장르별로 재생횟수 높은 순으로 정렬
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(countMap.entrySet());
        Collections.sort(entryList, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        for (Map.Entry<String, Integer> entry : entryList) {
            PriorityQueue<Song> songs = songMap.get(entry.getKey());
            if (songs.size() == 1) {
                Integer first = songs.poll().id;
                answerList.add(first);
            } else{
                Integer second = songs.poll().id;
                Integer first = songs.poll().id;
                answerList.add(first);
                answerList.add(second);
            }
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}
