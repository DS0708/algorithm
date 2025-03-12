package dp.c8.poly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//O(N^3 * cases)
public class Main {
    static int MOD = (int)1e7;
    static long[][] Cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int i=0; i<cases; i++) {
            int n = Integer.parseInt(br.readLine());
            Cache = new long[n+1][n+1];
            Arrays.stream(Cache).forEach(x -> Arrays.fill(x, -1));
            System.out.println(poly(n,0));
        }
    }
    public static long poly(int n, int first){
        //base case
        if(n==first) return 1;
        //Memoization
        if(Cache[n][first]!=-1) return Cache[n][first];
        //Logic
        Cache[n][first] = 0;
        for(int second = 1; second<=n-first; second++){
            int mul = first != 0 ? first+second-1 : 1;
            Cache[n][first] += (mul * poly(n-first, second))%MOD;
        }
        return Cache[n][first]%MOD;
    }
}

//문제 : https://algospot.com/judge/problem/read/POLY

//입력
/*
3
2
4
92
 */

//출력
/*
2
19
4841817
 */
