package divideandconquer.quadtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// O(n)
public class Main {
    static int idx;
    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Cases = Integer.parseInt(st.nextToken());
        //logic, output
        for(int c=0; c<Cases; c++) {
            idx=0;
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            String answer = reverse(str);
            System.out.println(answer);
        }
    }
    public static String reverse(String str) {
        //base case
        if(str.charAt(idx)!='x'){
            return String.valueOf(str.charAt(idx++));
        }
        //Logic
        StringBuilder sb = new StringBuilder("x");
        idx++;
        String[] strArr = new String[4];
        for(int i=0; i<4; i++){
            //divide
            strArr[i] = reverse(str);
        }
        //conquer
        return sb.append(strArr[2]).append(strArr[3]).append(strArr[0]).append(strArr[1]).toString();
    }
}

//문제 : https://algospot.com/judge/problem/read/QUADTREE

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