package dp.c8.pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DP {
    static String Str;
    static int[] Cache;
    static int INF = 987654321;
    /*
    INF를 Integer.MAX_VALUE 로 설정 시 오버플로우 발생
    예시 :
    12341일때
    begin(0) -> begin(3) 호출
    begin(3)로직에서 begin(6), begin(7), begin(8) 모두 "12341"의 길이인 5를 초과하므로 Cache[3]은 그대로 Integer.MAX_VALUE로 설정되고 리턴됨
    이때 Integer.MAX_VALUE에 classify() 값을 더하면 오버플로우 발생 !!
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++){
            Str = br.readLine();
            Cache = new int[Str.length()];
            Arrays.fill(Cache, -1);
            System.out.println(memorize(0));
        }
    }
    //start에서 시작하는 난이도의 합 최소
    public static int memorize(int start){
        //base case
        if(start==Str.length()) return 0;
        //Memoization
        if(Cache[start]!=-1) return Cache[start];
        //Logic
        Cache[start] = INF;
        for(int L=3; L<=5; L++){
            if(start+L <= Str.length()) Cache[start] = Math.min(Cache[start], memorize(start+L) + classify(start,start+L));
        }
        return Cache[start];
    }
    //start ~ end-1 까지의 문자열의 난이도
    public static int classify(int start, int end){
        String subStr = Str.substring(start, end);
        //모든 숫자가 같은 지 검사
        boolean allEqual = true;
        for(int i=0; i<subStr.length(); i++){
            if(subStr.charAt(i) != subStr.charAt(0)){
                allEqual = false;
                break;
            }
        }
        if(allEqual) return 1;
        //등차 수열인지 검사
        boolean progressive = true;
        for(int i=0; i<subStr.length()-1; i++){
            if(subStr.charAt(i+1)-subStr.charAt(i) != subStr.charAt(1)-subStr.charAt(0)){
                progressive = false;
                break;
            }
        }
        //등차수열이고 그 차이가 -1이나 1인지 검사
        int dif = subStr.charAt(1) - subStr.charAt(0);
        if(progressive && (dif==1 || dif==-1) ) return 2;
        //두 개의 숫자가 번갈아가며 나타나는지 검사
        boolean alternative = true;
        for(int i=0; i<subStr.length()-2; i++){
            if(subStr.charAt(i)!=subStr.charAt(i+2)){
                alternative = false;
                break;
            }
        }
        if(alternative) return 4;
        //등차 수열 인지 검사
        if(progressive) return 5;
        //이 외의 모든 경우
        return 10;
    }
}

//문제 : https://algospot.com/judge/problem/read/PI

//입력
/*
5
12341234
11111222
12122222
22222222
12673939
 */


//출력
/*
4
2
5
2
14
 */
