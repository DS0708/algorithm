package dp.c8.asymtiling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int MOD = 1000000007;
    static int[] Cache;
    static int[] Cache2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            int n = Integer.parseInt(br.readLine());
            Cache = new int[n+1];
            Arrays.fill(Cache, -1);
            Cache2 = new int[n+1];
            Arrays.fill(Cache2, -1);
            //"전체 - 대칭타일의 수 = 비대칭타일의 수"를 통해구하기
//            System.out.println(asymtiling(n));
            //비대칭 타일의 수를 세기
            System.out.println(asymtiling2(n));
        }
    }
    public static int asymtiling2(int width){
        //base case
        if(width <= 2) return 0;
        //Memoization
        if(Cache2[width] != -1) return Cache2[width];
        //Logic
        Cache2[width] = asymtiling2(width-2) % MOD;
        Cache2[width] = (Cache2[width] + asymtiling2(width-4)) % MOD;
        Cache2[width] = (Cache2[width] + tiling(width-3)) % MOD;
        Cache2[width] = (Cache2[width] + tiling(width-3)) % MOD;
        return Cache2[width];
    }
    public static int asymtiling(int width){
        //n이 홀수일때
        if(width%2==1){
            return (tiling(width) - tiling(width/2) + MOD)%MOD;
        }else{ //n이 짝수 일때
            int ret = (tiling(width) - tiling(width/2) + MOD)%MOD;
            ret = (ret - tiling(width/2-1)+MOD)%MOD;
            return ret;
        }
    }
    public static int tiling(int width){
        //base case
        if(width <= 1) return 1;
        //Memoization
        if(Cache[width] != -1) return Cache[width];
        //Logic
        return Cache[width] = (tiling(width-1) + tiling(width-2))%MOD;
    }
}

//문제 : https://algospot.com/judge/problem/read/ASYMTILING

//입력
/*
3
2
4
92
 */

//출력
/*
0
2
913227494
 */
