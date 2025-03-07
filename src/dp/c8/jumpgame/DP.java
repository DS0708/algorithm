package dp.c8.jumpgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP {
    static int BoardSize;
    static int[][] Board;
    static int[][] Cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int Cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c = 0; c < Cases; c++) {
            //init
            BoardSize = Integer.parseInt(br.readLine());
            Board = new int[BoardSize][BoardSize];
            Cache = new int[BoardSize][BoardSize];
            Arrays.stream(Cache).forEach(row -> Arrays.fill(row,-1));
            for(int i=0; i<BoardSize; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<BoardSize; j++) {
                    Board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //output
            if(jump(0,0)==1) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    public static int jump(int y, int x){
        //base case : board의 범위를 벗어나면 안됨
        if(y>=BoardSize || x>=BoardSize) return 0;
        //base case : 정답인 경우
        if(y+1==BoardSize && x+1==BoardSize) return 1;
        //Memoization
        if(Cache[y][x]!=-1) return Cache[y][x];
        //Logic
        int jumpSize = Board[y][x];
        if(jump(y+jumpSize,x)==1 || jump(y,x+jumpSize)==1) return Cache[y][x]=1;
        else return Cache[y][x]=0;
    }
}


//문제 : https://algospot.com/judge/problem/read/JUMPGAME

//입력
/*
2
7
2 5 1 6 1 4 1
6 1 1 2 2 9 3
7 2 3 2 1 3 1
1 1 3 1 7 1 2
4 1 2 3 4 1 2
3 3 1 2 3 4 1
1 5 2 9 4 7 0
7
2 5 1 6 1 4 1
6 1 1 2 2 9 3
7 2 3 2 1 3 1
1 1 3 1 7 1 2
4 1 2 3 4 1 3
3 3 1 2 3 4 1
1 5 2 9 4 7 0
 */

//출력
/*
YES
NO
 */