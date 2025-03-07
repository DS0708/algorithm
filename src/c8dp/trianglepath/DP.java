package c8dp.trianglepath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 복잡도 : O(n^2) -> 부분문제의 수는 cache 배열의 크기인 n^2이고 pathDP 함수는 O(1)에 해결 가능
public class DP {
    static int N;
    static int[][] triangle;
    static int[][] cache; //(y,x)에서 시작해서 갈 수 있는 최대 합
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Cases = Integer.parseInt(st.nextToken());
        //Logic
        for(int c=0; c<Cases; c++) {
            N = Integer.parseInt(br.readLine());
            triangle = new int[N+1][N+1];
            cache = new int[N+1][N+1];
            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=1; j<=i; j++){
                    triangle[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println(pathDP(1,1));
        }
    }
    public static int pathDP(int y, int x) {
        //Memoization
        if(cache[y][x] != 0) return cache[y][x];
        //base case
        if(y==N) return cache[y][x] = triangle[y][x];
        //Logic
        return cache[y][x] = triangle[y][x] + Math.max(pathDP(y+1, x), pathDP(y+1, x+1));
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