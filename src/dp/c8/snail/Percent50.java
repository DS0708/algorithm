package dp.c8.snail;

import java.util.Arrays;
import java.util.Scanner;

public class Percent50 {
    static int N,M;
    static int[][] Cache;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt(); //우물의 깊이
        M = scanner.nextInt(); //장마 기간
        Cache = new int[M][2*M+1];
        Arrays.stream(Cache).forEach(row -> Arrays.fill(row,-1));
        System.out.println(climb(0,0)/getPowerOfTwo(M));
    }
    public static int climb(int days, int climbed){
        //base case
        if(days==M) return climbed>=N ? 1 : 0;
        //Memoization
        if(Cache[days][climbed]!=-1) return Cache[days][climbed];
        //Logic
        return Cache[days][climbed] = climb(days+1, climbed+1) + climb(days+1, climbed+2);
    }
    public static double getPowerOfTwo(int n){
        double ret = 1;
        for(int i=0; i<n; i++) ret *= 2;
        return ret;
    }
}

//입력
/*
10 5
 */

//출력
/*
0.03125
 */
