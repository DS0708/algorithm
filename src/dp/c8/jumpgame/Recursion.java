package dp.c8.jumpgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//시간 복잡도 -> O(cases * 2^(n*n))
public class Recursion {
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
            if(canJump(board,n,0,0)) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    public static boolean canJump(int[][] board, int n, int row, int col) {
        //base case 1
        if(row >= n || col >= n) return false;
        //base case 2
        if(row==n-1 && col==n-1) return true;
        //Logic
        return canJump(board,n, row+board[row][col],col) || canJump(board,n,row,col+board[row][col]);
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
