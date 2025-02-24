package c8.dp.wildcard;

import java.io.*;
import java.util.*;

//시간복잡도 O (cases * s^w)
public class ExhaustiveSearch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Cases = Integer.parseInt(br.readLine());
        //logic
        for(int i=0;i<Cases;i++){
            String wildStr = br.readLine();
            int strNum = Integer.parseInt(br.readLine());
            List<String> answerList = new ArrayList<String>();
            for(int j=0; j<strNum; j++){
                String str = br.readLine();
                if(match(wildStr, str)) {
                    answerList.add(str);
                }
            }
            //사전순 정렬
            Collections.sort(answerList, (o1,o2)-> o1.compareTo(o2));
            for(String answer : answerList) System.out.println(answer);
        }
    }
    public static boolean match(String w, String s){
        int pos = 0;
        while(pos < w.length() && pos < s.length() && ( w.charAt(pos)==s.charAt(pos) || w.charAt(pos)=='?' )){
            pos++;
        }
        //pos가 와일드 카드의 길이를 넘어선 경우, s의 길이와 w의 길이가 같으면 true
        if(pos==w.length()) return pos==s.length();
        //와일드 카드의 위치에 *이 왔을 떄, 재귀 호출을 통해 모든 경우의 수를 구함
        if(w.charAt(pos)=='*'){
            for(int next=pos; next <= s.length(); next++){
                if(match(w.substring(pos+1), s.substring(next))) return true;
            }
        }
        //이외의 모든 경우는 false (pos는 s의 모든 길이를 돌았지만 w가 남아 있을 때)
        return false;
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
