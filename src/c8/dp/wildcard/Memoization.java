package c8.dp.wildcard;

import java.io.*;
import java.util.*;

public class Memoization {
    static String wildStr, str;
    static int[][] cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Cases = Integer.parseInt(br.readLine());
        //logic
        for(int i=0;i<Cases;i++){
            wildStr = br.readLine();
            int strNum = Integer.parseInt(br.readLine());
            List<String> answerList = new ArrayList<String>();
            for(int j=0; j<strNum; j++){
                cache = new int[101][101];
                Arrays.stream(cache).forEach(r->Arrays.fill(r,-1));
                str = br.readLine();
                if(matchMemoized(0,0)==1) {
                    answerList.add(str);
                }
            }
            //사전순 정렬
            Collections.sort(answerList, (o1, o2)-> o1.compareTo(o2));
            for(String answer : answerList) System.out.println(answer);
        }
    }
    public static int matchMemoized(int w, int s){
        if(cache[w][s] != -1) return cache[w][s];
        while(w < wildStr.length() && s < str.length() && (wildStr.charAt(w)==str.charAt(s) || wildStr.charAt(w)=='?') ){
            w++;
            s++;
        }
        if(w==wildStr.length()) {
            if(s == str.length()) return cache[w][s]=1;
            else return cache[w][s]=0;
        }
        if(wildStr.charAt(w)=='*'){
            for(int skip=0; s+skip<=str.length(); skip++ ){
                if(matchMemoized(w+1,s+skip)==1) return cache[w][s]=1;
            }
        }
        return cache[w][s]=0;
    }
}

//문제 : https://algospot.com/judge/problem/read/WILDCARD

//입력
/*
2
he?p
3
help
heap
helpp
*p*
3
help
papa
hello
 */


//출력
/*
heap
help
help
papa
 */
