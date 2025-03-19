package graph.c29.sortgame;

import java.io.*;
import java.util.*;

//느린 BFS로 풀기
public class BFS {
    static List<Integer> List;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c = 0; c < cases; c++) {
            int n = Integer.parseInt(br.readLine());
            List = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) List.add(Integer.parseInt(st.nextToken()));
            System.out.println(bfs());
        }
    }
    public static int bfs(){
        //init
        int n = List.size();
        List<Integer> sorted = new ArrayList<>();
        List<Integer> origin = new ArrayList<>();
        for(int i=0; i < n; i++) {
            sorted.add(List.get(i));
            origin.add(List.get(i));
        }
        Collections.sort(sorted);
        Queue<List<Integer>> Q = new ArrayDeque<>();
        Map<List<Integer>, Integer> distance = new HashMap<>();
        Q.offer(origin);
        distance.put(origin,0);
        while(!Q.isEmpty()){
            List<Integer> current = Q.poll();
            int cost = distance.get(current);
            if(current.equals(sorted)) return cost;
            for(int i=0; i<n; i++){
                for(int j=i+2; j<=n; j++){
                    List<Integer> temp = reverse(current, i, j);
                    if(!distance.containsKey(temp)){
                        Q.offer(temp);
                        distance.put(temp, cost + 1);
                    }
                }
            }
        }
        return -1;
    }
    public static List<Integer> reverse(List<Integer> list, int from, int to){
        List<Integer> ret = new ArrayList<>();
        for(int i=0; i<list.size(); i++) ret.add(list.get(i));
        Collections.reverse(ret.subList(from, to));
        return ret;
    }
}

//문제 : https://algospot.com/judge/problem/read/SORTGAME

//입력
/*
3
8
1 2 3 4 8 7 6 5
4
3 4 1 2
3
1 2 3
 */

//출력
/*
1
2
0
 */
