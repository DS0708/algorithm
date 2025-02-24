package c8.dp.jumpgame;

import java.io.*;
import java.util.*;

public class dp {
    static int[][] cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cases = Integer.parseInt(st.nextToken());
        //Logic
        for(int c = 0; c < cases; c++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] board = new int[n][n];
            for(int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            cache = new int[n][n];
            Arrays.stream(cache).forEach(row -> Arrays.fill(row,-1));
            if(canJump(board,n,0,0)==1) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    public static int canJump(int[][] board, int n, int r, int c) {
        //base case1
        if(r>=n || c>=n) return 0;
        //base case2
        if(r==n-1 && c==n-1) return 1;
        //Memoization
        if(cache[r][c] != -1) return cache[r][c];
        //Logic
        if(canJump(board,n,r+board[r][c], c) ==1 || canJump(board,n,r,c+board[r][c])==1) cache[r][c] = 1;
        else cache[r][c] = 0;
        return cache[r][c];
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