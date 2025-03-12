package dp.c8.tiling2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// O(N)
public class Main {
    static int MOD = 1000000007;
    static int Cache[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            int n = Integer.parseInt(br.readLine());
            Cache = new int[n+1];
            Arrays.fill(Cache, -1);
            System.out.println(tiling(n));
        }
    }
    public static int tiling(int n) {
        //base case
        if(n <= 1) return 1;
        //Memoization
        if(Cache[n] != -1) return Cache[n];
        //Logic
        Cache[n] = (tiling(n-1) + tiling(n-2)) % MOD;
        return Cache[n];
    }
}

//문제 : https://algospot.com/judge/problem/read/TILING2

//입력
/*
3
1
5
100
 */

//출력
/*
1
8
782204094
 */
