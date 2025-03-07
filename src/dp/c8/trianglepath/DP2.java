package dp.c8.trianglepath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//시간복잡도 O(n^2)
public class DP2 {
    static int N;
    static int[][] Triangle;
    //(y,x) 일때 남은 path 중 최대 합
    static int[][] Cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c = 0; c<cases; c++) {
            N = Integer.parseInt(br.readLine());
            Triangle = new int[N][N];
            Cache = new int[N][N];
            Arrays.stream(Cache).forEach(row-> Arrays.fill(row,-1));
            for(int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<=i; j++){
                    Triangle[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //output
            System.out.println(path(0,0));
        }
    }
    public static int path(int y, int x){
        //base case
        if(y==N-1) return Triangle[y][x];
        //Memoization
        if(Cache[y][x]!=-1) return Cache[y][x];
        //Logic
        return Cache[y][x] = Triangle[y][x] + Math.max(path(y+1,x), path(y+1, x+1));
    }
}

//문제 : https://algospot.com/judge/problem/read/TRIANGLEPATH

//입력
/*
2
5
6
1  2
3  7  4
9  4  1  7
2  7  5  9  4
5
1
2 4
8 16 8
32 64 32 64
128 256 128 256 128
 */

//출력
/*
28
341
 */