package c6.bruteforce.boardcover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static int[][][] paintType = {
        { {0,0}, {0,1}, {1,1} },
        { {0,0}, {0,1}, {1,0} },
        { {0,0}, {1,0}, {1,1} },
        { {0,0}, {1,0}, {1,-1} }
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Cases = Integer.parseInt(st.nextToken());
        for(int c = 0; c < Cases; c++) {
            st = new StringTokenizer(br.readLine());
            int raw = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int[][] board = new int[raw][col];
            int canSolve = 0;
            for(int i = 0; i < raw; i++) {
                String str = br.readLine();
                for(int j = 0; j < col; j++) {
                    board[i][j] = str.charAt(j)=='#' ? 1 : 0;
                    if(board[i][j]==0) canSolve++;
                }
            }
            //예외 케이스
            if(canSolve%3 != 0){
                System.out.println(0);
                continue;
            }
            //일반적인 케이스
            int answer = solve(board,raw,col);
            System.out.println(answer);
        }
    }
    public static int solve(int[][] board, int raw, int col) {
        //왼쪽 맨 위 부터 안채워진 칸 찾기
        int fr= -1;
        int fc= -1;
        for(int i=0; i<raw; i++){
            for(int j=0; j<col; j++){
                if(board[i][j]==0){
                    fr = i;
                    fc = j;
                    break;
                }
            }
            if(fr!=-1) break;
        }
        //base case
        if(fr==-1) {
            return 1;
        }
        //logic
        int ret = 0;
        for(int type=0; type<4; type++){
            if(canPaint(board,fr,fc,type,1)){
                ret += solve(board, raw, col);
            }
            canPaint(board,fr,fc,type,-1);
        }
        return ret;
    }
    //칠하면서, 가능 여부 판별
    public static boolean canPaint(int[][] board, int r, int c, int type, int delta){
        boolean ok = true;
        for(int i=0; i<3; i++){
            int nr = r + paintType[type][i][0];
            int nc = c + paintType[type][i][1];
            if(nr<0||nr>=board.length || nc<0||nc>=board[0].length){
                ok = false;
            }else{
                board[nr][nc] += delta;
                if(board[nr][nc] > 1) ok = false;
            }
        }
        return ok;
    }
}


/*
3
3 7
#.....#
#.....#
##...##
3 7
#.....#
#.....#
##..###
8 10
##########
#........#
#........#
#........#
#........#
#........#
#........#
##########
 */


