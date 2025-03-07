package c8dp.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//시간복잡도 O(N^2)
public class DP {
    static int N;
    static int[] Arr;
    static int[] Cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++){
            //init
            N = Integer.parseInt(br.readLine());
            Arr = new int[N];
            Cache = new int[N];
            Arrays.fill(Cache, -1);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                Arr[i] = Integer.parseInt(st.nextToken());
            }
            //[5, 2, 3, 4] 의 경우 lisDP(0)만 호출하면 답은 1이다 -> 그래서 전체를 시작점으로 돌려야 함
            int maxLen = 0;
            for(int i=0; i<N; i++){
                maxLen = Math.max(maxLen, lisDP(i));
            }
            //output
            System.out.println(maxLen);
        }
    }
    //lisDP(start) = Arr[start]에서 시작하는 증가 수열의 최대 길이
    public static int lisDP(int start){
        //base case는 필요 없음

        //Memoization
        if(Cache[start] != -1) return Cache[start];
        //Logic
        //자기 자신을 무조건 하나 포함할 수 있으므로
        Cache[start] = 1;
        for(int next=start+1; next<N; next++){
            if(Arr[start] < Arr[next]){
                Cache[start] = Math.max(Cache[start], 1 + lisDP(next));
            }
        }
        return Cache[start];
    }
}


//문제 : https://algospot.com/judge/problem/read/LIS

//입력
/*
3
4
1 2 3 4
8
5 4 3 2 1 6 7 8
8
5 6 7 8 1 2 3 4
 */

//출력
/*
4
4
4
 */