package dp.c9.lis;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] Arr;
    static int[] Cache;
    static int[] Choices;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++) {
            //Init
            N = Integer.parseInt(br.readLine());
            Arr = new int[N];
            Cache = new int[N+1];
            Choices = new int[N+1];
            Arrays.fill(Cache, -1);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) Arr[i] = Integer.parseInt(st.nextToken());
            //Logic
            System.out.println(lis(-1)-1);
//            System.out.println("Choices");
//            for(int i : Choices) System.out.print(i+ " ");
//            System.out.println();
//            System.out.println("Cache");
//            for(int i : Cache) System.out.print(i+ " ");
//            System.out.println();
            List<Integer> seq = new ArrayList<>();
            reconstruct(Choices[0], seq);
            for(int i : seq) System.out.print(i + " ");
            System.out.println();
        }
    }
    public static void reconstruct(int start, List<Integer> seq){
        if(start != -1) seq.add(Arr[start]);
        int next = Choices[start+1];
        if(next != -1) reconstruct(next, seq);
    }
    public static int lis(int start){
        //Memoization
        if(Cache[start+1]!=-1) return Cache[start+1];
        //Logic
        int ret = 1;
        int bestNext = -1;
        for(int next=start+1; next<N; next++){
            if(start==-1 || Arr[start] < Arr[next]){
                int cand = 1 + lis(next);
                if( ret < cand){
                    ret = cand;
                    bestNext = next;
                }
            }
        }
        Choices[start+1] = bestNext;
        return Cache[start+1] = ret;
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
