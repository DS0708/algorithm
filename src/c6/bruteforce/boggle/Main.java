package c6.bruteforce.boggle;

import java.io.*;
import java.util.*;

//O(M* N^2 * 8^word'length)
public class Main {
    static char[][] board;
    static int N;
    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        board = new char [N][N];
        //board 채우기
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                board[i][j] = st.nextToken().charAt(0);
            }
        }
        //단어 채우기
        String[] words = new String[M];
        for(int i=0;i<M;i++) words[i] = br.readLine();
        //output
        //O(M* N^2 * 8^word'length)
        for(String word : words){
            boolean isWord = false;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(hasWord(i,j,word)){
                        isWord = true;
                        break;
                    }
                }
            }
            if(isWord) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    public static boolean hasWord(int r, int c, String word){
        //base case1 : (i,j)가 범위 밖일 때, false
        if(r<0||r>=N || c<0||c>=N) return false;
        //base case2 : (i,j)가 word의 첫 단어가 아닐 때, false
        if(board[r][c] != word.charAt(0) ) return false;
        //base case3 : (i,j)가 한 글자 일때, true
        if(word.length() == 1) return true;

        //Logic : Exhaustive Searching
        boolean isWord = false;
        int[] dr = {-1, -1, -1, 1, 1, 1, 0, 0};
        int[] dc = {-1, 0, 1, -1, 0, 1, -1, 1};

        for(int i=0; i<8; i++){
            int nr = r+dr[i];
            int nc = c+dc[i];
            if(hasWord(nr,nc,word.substring(1))) {
                isWord = true;
                break;
            }
        }

        return isWord;
    }
}

//문제 : 게임판에서 상하좌우/대각선 으로 이동가능할 때, 해당 문자가 있는지 판별하기

//입력
/*
5 5
U R L P M
X P R E T
G I A E T
X T N Z Y
X O Q R S
PRETTY
GIRL
REPEAT
YES
A
 */

//출력
/*
YES
YES
YES
NO
YES
 */
