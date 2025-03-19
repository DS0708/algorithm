package graph.c29.hanoi4;

import java.io.*;
import java.util.*;

//Dis의 메모리가 너무 커서, 런타임 오류 발생
public class BFSPreCalcLogic {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int MAX_DISK = 12;
    static Map<Integer, Integer> Dis = new HashMap<>();
//    static int[][] Dis = new int[MAX_DISK+1][1<<(MAX_DISK*2)];
    public static void main(String[] args) throws IOException {
        int iterator = Integer.parseInt(br.readLine());
        for(int i=1; i<=MAX_DISK; i++) preCalc(i);
        while((iterator--) > 0) solve();
    }
    private static void solve() throws IOException {
        //init
        int n = Integer.parseInt(br.readLine());
        int start = 0;
        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for(int j=0; j<m; j++) {
                int disk = Integer.parseInt(st.nextToken());
                start = set(start, disk-1, i);
            }
        }
        start = setIdx(start, n);
        System.out.println(Dis.get(start));
    }
    private static void preCalc(int n){
        int end = (1<<(n*2)) - 1 ;
        end = setIdx(end, n);
        Dis.put(end, 0);
        Queue<Integer> Q = new ArrayDeque<>();
        Q.offer(end);
        while(!Q.isEmpty()) {
            int cur = Q.poll();
            int cost = Dis.get(cur);
            int[] top = {-1,-1,-1,-1};
            for(int i=n-1; i>=0; i--){
                top[get(cur,i)] = i;
            }
            for(int i=0; i<4; i++){
                if(top[i]==-1) continue;
                for(int j=0; j<4; j++){
                    //i에서 j로 갈 수 없고, j가 비어 있거나 j의 젤 위에 있는 disk가 더 커야함
                    if(i!=j && (top[j]==-1 || top[i] < top[j])){
                        int there = set(cur,top[i],j);
                        //end가 아니고, 방문하지 않았다면
                        if(Dis.get(there) == null){
                            Q.offer(there);
                            Dis.put(there, cost + 1);
                        }
                    }
                }
            }
        }
    }
    private static int get(int state, int idx){
        return (state >> (idx*2)) & 3 ;
    }
    private static int setIdx(int state, int idx){
        return (state & ~(15<<(MAX_DISK*2))) | (idx << (MAX_DISK*2));
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
