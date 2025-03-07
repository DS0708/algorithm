package c8dp.trianglepath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간복잡도 = O(2^n-1) -> 2번째 줄부터 n번째 줄까지 각각 2가지의 경우의 수를 탐색할 수 있음
public class ExhaustiveSearch {
    static int N;
    static int[][] triangle;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Cases = Integer.parseInt(st.nextToken());
        //Logic
        for(int c=0; c<Cases; c++) {
            N = Integer.parseInt(br.readLine());
            triangle = new int[N+1][N+1];
            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=1; j<=i; j++){
                    triangle[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println(path(1,1,0));
        }
    }
    public static int path(int y, int x, int sum) {
        if(y==N) return sum + triangle[y][x];
        return Math.max(path(y+1,x,sum+triangle[y][x]), path(y+1,x+1, sum+triangle[y][x]));
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