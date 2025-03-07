package c8dp.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP2 {
    static int N;
    static int[] Arr;
    static int[] Cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++){
            //init
            N = Integer.parseInt(br.readLine());
            Arr = new int[N];
            Cache = new int[N];
            Arrays.fill(Cache, -1);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) Arr[i] = Integer.parseInt(st.nextToken());
            //Logic
            int maxLen = 0;
            for(int i=0; i<N; i++) maxLen = Math.max(maxLen, lisDP(i));
            System.out.println(maxLen);
        }
    }
    public static int lisDP(int start){
        //Memoization
        if(Cache[start] != -1) return Cache[start];
        //Logic
        Cache[start] = 1;
        for(int next = start+1; next<N; next++){
            if(Arr[start] < Arr[next]) Cache[start] = Math.max(Cache[start], 1 + lisDP(next));
        }
        return Cache[start];
    }
}

//문제 : https://algospot.com/judge/problem/read/LIS

//입력
/*
3
4
1 2 3 4
8
5 4 3 2 1 6 7 8
8
5 6 7 8 1 2 3 4
 */

//출력
/*
4
4
4
 */