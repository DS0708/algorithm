package dp.c8.jlis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP {
    static int N,M;
    static long[] A;
    static long[] B;
    static long[][] Cache;
    //Cache[a][b] = A[a]와 B[b]에서 시작하는 가장 긴 합친 부분 증가 수열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++) {
            //init
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            A = new long[N];
            B = new long[M];
            Cache = new long[N+1][M+1];
            Arrays.stream(Cache).forEach(row-> Arrays.fill(row,Long.MIN_VALUE));
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++) B[i] = Integer.parseInt(st.nextToken());
            System.out.println(jlis(-1,-1)-2);
        }
    }
    public static long jlis(int aStart, int bStart){
        //memoization
        int aCacheIdx = aStart+1;
        int bCacheIdx = bStart+1;
        if(Cache[aCacheIdx][bCacheIdx]!=Long.MIN_VALUE) return Cache[aCacheIdx][bCacheIdx];
        //Logic
        Cache[aCacheIdx][bCacheIdx] = 2;
        long a = aStart == -1 ? Long.MIN_VALUE : A[aStart];
        long b = bStart == -1 ? Long.MIN_VALUE : B[bStart];
        long maxVal = Math.max(a, b);
        for(int aNext = aStart+1; aNext < N; aNext++) {
            if(maxVal < A[aNext]) Cache[aCacheIdx][bCacheIdx] = Math.max(1 + jlis(aNext,bStart), Cache[aCacheIdx][bCacheIdx]);
        }
        for(int bNext = bStart+1; bNext < M; bNext++) {
            if(maxVal < B[bNext]) Cache[aCacheIdx][bCacheIdx] = Math.max(1 + jlis(aStart,bNext), Cache[aCacheIdx][bCacheIdx]);
        }
        return Cache[aCacheIdx][bCacheIdx];
    }
}

//문제 : https://algospot.com/judge/problem/read/JLIS

//입력
/*
3
3 3
1 2 4
3 4 7
3 3
1 2 3
4 5 6
5 3
10 20 30 1 2
10 20 30
 */

//출력
/*
5
6
5
 */
