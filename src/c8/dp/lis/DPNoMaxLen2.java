package c8.dp.lis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DPNoMaxLen2 {
    static int N;
    static int[] Arr;
    static int[] Cache;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++){
            N = Integer.parseInt(br.readLine());
            Arr = new int[N];
            Cache = new int[N+1];
            Arrays.fill(Cache, -1);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) Arr[i] = Integer.parseInt(st.nextToken());
            System.out.println(lisDP2(-1)-1);
        }
    }
    public static int lisDP2(int start){
        //Memoization
        int cacheIdx = start+1;
        if(Cache[cacheIdx] != -1) return Cache[cacheIdx];
        //Logic
        Cache[cacheIdx] = 1;
        for(int next = start+1; next<N; next++){
            if(start==-1 || Arr[start] < Arr[next]) Cache[cacheIdx] = Math.max(Cache[cacheIdx], 1 + lisDP2(next));
        }
        return Cache[cacheIdx];
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
