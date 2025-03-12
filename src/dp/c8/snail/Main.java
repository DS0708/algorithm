package dp.c8.snail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static double[][] Cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            Cache = new double[M][2*M+1];
            Arrays.stream(Cache).forEach(r -> Arrays.fill(r, -1));
            System.out.println(climb(0,0));
        }
    }
    public static double climb(int days, int climbed){
        //base case
        if(days==M) return climbed >= N ? 1 : 0;
        //Memoization
        if(Cache[days][climbed] != -1) return Cache[days][climbed];
        //Logic
        return Cache[days][climbed] = 0.25 * climb(days+1, climbed+1) + 0.75*climb(days+1, climbed+2);
    }
    public static double getPowerOfTwo(int n){
        double ret = 1;
        for(int i=0; i<n; i++) ret *=2;
        return ret;
    }
}

//문제 : https://algospot.com/judge/problem/read/SNAIL

//입력
/*
4
5 4
5 3
4 2
3 2
 */

//출력
/*
0.9960937500
0.8437500000
0.5625000000
0.9375000000
 */
