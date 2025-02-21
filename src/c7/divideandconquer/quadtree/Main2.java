package c7.divideandconquer.quadtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static int idx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Cases = Integer.parseInt(st.nextToken());
        for(int c = 0; c < Cases; c++) {
            String str = br.readLine();
            idx = 0;
            String answer = solve(str);
            System.out.println(answer);
        }
    }
    public static String solve(String str){
        //base case
        if(str.charAt(idx) != 'x') return String.valueOf(str.charAt(idx++));
        //분할 정복 Logic
        String[] strArr = new String[4];
        StringBuilder sb = new StringBuilder("x");
        idx++;
        //Divide
        for(int i=0; i<strArr.length; i++){
            strArr[i] = solve(str);
        }
        //Conquer
        return sb.append(strArr[2]).append(strArr[3]).append(strArr[0]).append(strArr[1]).toString();
    }
}

//입력
/*
4
w
xbwwb
xbwxwbbwb
xxwwwbxwxwbbbwwxxxwwbbbwwwwbb
 */

//출력
/*
w
xwbbw
xxbwwbbbw
xxwbxwwxbbwwbwbxwbwwxwwwxbbwb
 */
