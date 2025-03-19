package graph.c29.sortgame;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Map<Integer, Integer> toSort = new HashMap<>();
    public static void main(String[] args) throws IOException {
        int iterator = Integer.parseInt(br.readLine());
        for(int i=1; i<=8; i++) preCalc(i);
        while((iterator--) > 0) solve();
    }
    public static void solve() throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] perm = new int[n];
        for(int i=0; i<n; i++){
            int smaller = 0;
            for(int j=0; j<n; j++){
                if(arr[i] > arr[j]) smaller++;
            }
            perm[i] = smaller;
        }

        int state = getState(n,perm);

        System.out.println(toSort.get(state));
    }
    public static void preCalc(int n){
        int state = 0;
        for(int i=0; i<n; i++){
            state = set(state, i, i);
        }

        Queue<Integer> Q = new ArrayDeque<>();
        Q.offer(state);
        toSort.put(state, 0);

        while(!Q.isEmpty()){
            int curState = Q.poll();
            int cost = toSort.get(curState);
            for(int i=0; i<n; i++){
                for(int j=i+2; j<=n; j++){
                    int reverseState = reverse(n, curState, i, j);
                    if(toSort.get(reverseState) == null){
                        Q.offer(reverseState);
                        toSort.put(reverseState, cost + 1);
                    }
                }
            }
        }
    }
    public static int reverse(int n, int state, int from, int to){
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];

        for(int i=0; i<n; i++){
            arr1[i] = get(state, i);
            arr2[i] = get(state, i);
        }

        int pos = to;
        for(int i=from; i<to; i++){
            arr1[i] = arr2[--pos];
        }

        int retState = 0;
        for(int i=0; i<n; i++){
            retState = set(retState, i, arr1[i]);
        }

        return retState;
    }
    public static int getState(int n, int[] arr){
        int state = 0;
        for(int i=0; i<n; i++){
            state = set(state, i, arr[i]);
        }
        return state;
    }
    public static int get(int state, int idx){
        return (state >> (idx*3)) & 7;
    }
    public static int set(int state, int idx, int val){
        return (state & ~(7<<(idx*3))) | (val << (idx*3));
    }
    //TDD
    public static void reverseCheck() throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int state = 0;
        for(int i = 0; i < n; i++) {
            state = set(state, i, arr[i]);
        }

        int retState = reverse(n, state, 1, 4);

        for(int i=0; i<n; i++){
            arr[i] = get(retState,i);
        }

        for(int i : arr) System.out.print(i + " ");
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