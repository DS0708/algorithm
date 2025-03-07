package c8dp.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DPNoMaxLen {
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
            //Cache[0]은 전체 부분 수열에서 가장 긴 길이, 기존의 Cache값을 하나씩 뒤로 미뤘다고 생각하면 됨
            Cache = new int[N+1];
            Arrays.fill(Cache, -1);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                Arr[i] = Integer.parseInt(st.nextToken());
            }
            //output, -1을 넣어 모든 수가 시작점이 되도록 만듦
            //또한 임의로 1개를 추가했기 때문에 1개를 빼줘야 함
            System.out.println(lisDP(-1)-1);
        }
    }
    //lisDP(start) = Arr[start]에서 시작하는 증가 수열의 최대 길이
    public static int lisDP(int start){
        //Memoization
        if(Cache[start+1] != -1) return Cache[start+1];
        //Logic
        //자기 자신을 무조건 하나 포함할 수 있으므로
        Cache[start+1] = 1;
        for(int next=start+1; next<N; next++){
            if(start==-1 || Arr[start] < Arr[next]){
                Cache[start+1] = Math.max(Cache[start+1], 1 + lisDP(next));
            }
        }
        return Cache[start+1];
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
