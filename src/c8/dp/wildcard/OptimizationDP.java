package c8.dp.wildcard;


import java.io.*;
import java.util.*;

//시간복잡도 = (Cases * WildStr.length() * Str.length())
public class OptimizationDP {
    static String WildStr, Str;
    //Memoization으로 WildStr의 시작점과 Str의 시작점이 주어질 때 가능한지 불가능한 지를 캐싱
    static int[][] Cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<Cases; c++){
            //init
            List<String> answerList = new ArrayList<>();
            WildStr = br.readLine();
            int n = Integer.parseInt(br.readLine());
            //Logic
            for(int i=0; i<n; i++){
                //WildStr과 Str의 길이는 1~100까지
                Cache = new int[101][101];
                //Cache는 문자열을 비교할때마다 초기화 해줘야 함
                Arrays.stream(Cache).forEach(row -> Arrays.fill(row,-1));
                Str = br.readLine();
                if(match(0, 0)==1) answerList.add(Str);
            }
            //Output
            Collections.sort(answerList, (o1,o2) -> o1.compareTo(o2));
            for(String answer : answerList) System.out.println(answer);
        }
    }
    public static int match(int w, int s){
        //Memoization
        if(Cache[w][s] != -1) return Cache[w][s];
        //Logic
        //while -> if, w번째와 s번째 문자열이 같기 때문에 그 다음 문자열을 비교하는 부분문제로 변환
        if(w < WildStr.length() && s < Str.length() && (WildStr.charAt(w)=='?' || WildStr.charAt(w)==Str.charAt(s))){
            return Cache[w][s] = match(w+1,s+1);
        }
        /*
        while문을 빠져나오는 경우의 수
        1. WildStr.charAt(pos)와 Str.charAt(pos)가 다른 문자일 떄
            -> 무조건 false
        2. pos가 WildStr의 길이와 같을 때
            -> 와일드카드의 문자열을 모두 확인한 것이기 때문에 pos==Str.length()라면 true
        3. pos가 Str의 길이와 같을 떄
            -> WildStr의 남은 문자열이 모두 *일때만 true
        4. WildStr.charAt(pos)가 *일 때
            -> WildStr.chatAt(pos+1)와 Str.charAt(pos), Str.charAt(pos+1), Str.charAt(pos+2)... 까지 모두 비교해서 하나라도 true이면 true
        */
        //2번 조건 처리
        if(w == WildStr.length()){
            if(s == Str.length()) return Cache[w][s]=1;
            else return Cache[w][s]=0;
        }
        //4번 조건 처리
        if(WildStr.charAt(w)=='*'){
            //WildStr의 문자열이 *일떄, *이 아무것도 대응되지 않거나, 그 다음 문자와 대응되는 부분문제로 변환 가능
            //이떄 *과 Str의 빈문자열이 비교되는 경우를 예외 처리해줘야 함
            if(match(w+1, s)==1 || (s < Str.length() && match(w, s+1)==1)) return Cache[w][s]=1;
        }
        //1번 조건, 3번 조건의 WildStr의 남은 문자열이 하나라도 *이 아닐 때 처리
        return Cache[w][s]=0;
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
