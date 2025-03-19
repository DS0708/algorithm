package graph.c29.hanoi4;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Map<Integer, Integer> Dis;
    public static void main(String[] args) throws IOException {
        int iterator = Integer.parseInt(br.readLine());
        while((iterator--) > 0) solve();
    }
    private static void solve() throws IOException {
        //init
        int n = Integer.parseInt(br.readLine());
        Dis = new HashMap<>();
        int start = 0;
        int end = (1<<(n*2)) - 1 ;
        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for(int j=0; j<m; j++) {
                int disk = Integer.parseInt(st.nextToken());
                start = set(start, disk-1, i);
            }
        }
        System.out.println(bidirectionalBFS(n,start,end));
    }
    private static int bidirectionalBFS(int n, int start, int end){
        Queue<Integer> Q = new ArrayDeque<>();
        Q.offer(start);
        Q.offer(end);
        Dis.put(start,1);
        Dis.put(end,-1);
        while(!Q.isEmpty()) {
            int cur = Q.poll();
            int[] top = {-1,-1,-1,-1};
            for(int i=n-1; i>=0; i--){
                top[get(cur,i)] = i;
            }
            for(int i=0; i<4; i++){
                if(top[i]==-1) continue;
                for(int j=0; j<4; j++){
                    if(i!=j && (top[j]==-1 || top[i] < top[j])){
                        int there = set(cur,top[i],j);
                        //아직 방문하지 않았다면
                        if(Dis.get(there) == null){
                            Q.offer(there);
                            Dis.put(there, incr(Dis.get(cur)));
                        }else if(sgn(Dis.get(cur)) != sgn(Dis.get(there))){ // 가운데서 만났을 때
                            return Math.abs(Dis.get(cur)) + Math.abs(Dis.get(there)) - 1;
                        }
                    }
                }
            }
        }
        return -1;
    }
    //부호 반환
    private static int sgn(int val){
        return val > 0 ? 1 : -1;
    }
    //절대값 1 증가
    private static int incr(int val){
        return val >= 1 ? val+1 : val-1;
    }
    private static int get(int state, int idx){
        return (state >> (idx*2)) & 3 ;
    }
    private static int set(int state, int idx, int val){
        return (state & ~(3<<(idx*2))) | (val << (idx*2));
    }
}

//문제 : https://algospot.com/judge/problem/read/HANOI4

//입력
/*
3
5
1 1
1 3
2 5 4
1 2
3
1 2
0
2 3 1
0
10
2 8 7
2 5 4
3 6 3 2
3 10 9 1
 */

//출력
/*
10
4
24
 */


