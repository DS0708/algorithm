package c6.bruteforce.boardcover;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int[][][] coverType = {
        { {0,0}, {0,1}, {1,0} },
        { {0,0}, {0,1}, {1,1} },
        { {0,0}, {1,0}, {1,-1} },
        { {0,0}, {1,0}, {1,1} }
    };
    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Cases = Integer.parseInt(st.nextToken());
        //logic
        for(int i = 0; i < Cases; i++) {
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int[][] board = new int[H][W];
            int white = 0;
            for(int r = 0; r < H; r++) {
                String line = br.readLine();
                for(int c = 0; c < W; c++) {
                    board[r][c] = line.charAt(c)=='#' ? 1 : 0;
                    if(board[r][c]==0) white++;
                }
            }
            //output
            int answer  = 0;
            //하얀색 칸이 없거나, 3의 배수가 아니라면 경우의 수가 존재하지 않음
            if(white!=0 && white%3==0){
                answer = cover(board, H, W);
            }
            System.out.println(answer);
        }
    }
    public static int cover(int[][] board, int H, int W){
        //제일 위쪽의 왼쪽부터 검사 -> 순서를 강제하여 중복으로 세는 것 방지
        int y = -1;
        int x = -1;
        for(int r = 0; r < H; r++) {
            for(int c = 0; c < W; c++) {
                if(board[r][c]==0){
                    y = r;
                    x = c;
                    break;
                }
            }
            if(y!=-1) break;
        }
        //base case : 모든 경우의 수를 찾으면 1을 return
        if(y==-1) return 1;
        //Logic
        int ret = 0;
        for(int type=0; type<4; type++){
            if(set(board,y,x,type,1)) ret += cover(board, H, W);
            set(board,y,x,type,-1);
        }
        return ret;
    }
    public static boolean set(int[][] board, int y, int x, int type, int delta){
        boolean ok = true;
        for(int i=0; i<3; i++){
            int ny = y+coverType[type][i][0];
            int nx = x+coverType[type][i][1];
            // ny, nx가 board의 범위 밖이면 false
            if(ny<0||ny>=board.length || nx<0||nx>=board[0].length){
                ok=false;
                break;
            }
            // ny, nx가 검은칸(1)이라면 false 리턴
            // 이 경우에는 어차피 -1을 통해 다시 줄어들게 되어 있음.
            else if ( (board[ny][nx]+=delta) > 1){
                ok=false;
                //이 조건문은 delta가 +1일 때만 수행되며, 여기서 break를 걸 경우, delta가 -1일 때는 모두 실행되기 때문에 쓰면 안됨
                //break;
            }
        }
        return ok;
    }
}

//문제 : https://algospot.com/judge/problem/read/BOARDCOVER

//예제 입력
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

//예제 출력
/*
0
2
1514
 */