package dp.c8.tripathcnt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] Triangle;
    static int[][] PathCache;
    static int[][] CountCache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            //init
            N = Integer.parseInt(br.readLine());
            Triangle = new int[N][N];
            PathCache = new int[N][N];
            Arrays.stream(PathCache).forEach(row -> Arrays.fill(row, -1));
            CountCache = new int[N][N];
            Arrays.stream(CountCache).forEach(row -> Arrays.fill(row, -1));
            for(int i=0;i<N;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<=i; j++) Triangle[i][j] = Integer.parseInt(st.nextToken());
            }
            //최대 경로의 개수 출력하기
            System.out.println(countPath(0,0));
        }
    }
    public static int countPath(int y, int x) {
        //base case
        if(y==N-1) return 1;
        //Memoization
        if(CountCache[y][x]!=-1) return CountCache[y][x];
        //logic
        if(path(y+1,x) == path(y+1,x+1)){
            CountCache[y][x] = countPath(y+1, x) + countPath(y+1, x+1);
        }else if (path(y+1,x) > path(y+1,x+1)){
            CountCache[y][x] = countPath(y+1, x);
        }else{
            CountCache[y][x] = countPath(y+1, x+1);
        }
        return CountCache[y][x];
    }
    public static int path(int y, int x){
        //base case
        if(y==N) return 0;
        //Memoization
        if(PathCache[y][x]!=-1) return PathCache[y][x];
        //Logic
        PathCache[y][x] = Math.max(Triangle[y][x] + path(y+1,x) , Triangle[y][x] + path(y+1,x+1));
        return PathCache[y][x];
    }
}

//문제 : https://algospot.com/judge/problem/read/TRIPATHCNT

//입력
/*
2
4
1
1 1
1 1 1
1 1 1 1
4
9
5 7
1 3 2
3 5 5 6
 */

//출력
/*
8
3
 */
