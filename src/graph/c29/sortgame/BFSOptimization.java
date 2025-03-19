package graph.c29.sortgame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import java.io.*;
import java.util.*;

//시간초과
public class BFSOptimization {
    static int N;
    static List<Integer> List;
    static Map<String, Integer> toSort;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.valueOf(br.readLine());
        for(int c = 0; c < cases; c++) {
            //init
            N = Integer.parseInt(br.readLine());
            List = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) List.add(Integer.parseInt(st.nextToken()));
            toSort = new HashMap<>();
            //Logic
            preCalc();
            System.out.println(solve());
        }
    }
    private static void preCalc() {
        StringBuilder sorted = new StringBuilder();
        for(int i=0; i<N; i++) sorted.append(i);
        Queue<String> Q  = new ArrayDeque<>();
        toSort.put(sorted.toString(), 0);
        Q.offer(sorted.toString());
        while(!Q.isEmpty()) {
            String cur = Q.poll();
            int cost = toSort.get(cur);
            for(int i=0; i<N; i++){
                for(int j=i+2; j<=N; j++){
                    String temp = reverse(cur,i,j);
                    if(!toSort.containsKey(temp)){
                        Q.offer(temp);
                        toSort.put(temp, cost+1);
                    }
                }
            }
        }
    }
    private static int solve() {
        StringBuilder perm = new StringBuilder();
        for(int i=0; i<N; i++){
            int smaller = 0;
            for(int j=0; j<N; j++){
                if(List.get(i) > List.get(j)) smaller++;
            }
            perm.append(smaller);
        }
        return toSort.get(perm.toString());
    }
    private static String reverse(String str, int from, int to){
        StringBuilder res = new StringBuilder(str.substring(0,from));
        StringBuilder reverseAdd = new StringBuilder(str.substring(from,to));
        reverseAdd.reverse();
        res.append(reverseAdd);
        res.append(str.substring(to));
        return res.toString();
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
