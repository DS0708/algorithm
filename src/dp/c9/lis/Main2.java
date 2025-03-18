package dp.c9.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {
    static int N;
    static int[] Arr;
    static int[] Cache;
    static int[] Choices;
    static List<Integer> Seq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0;c<cases;c++){
            N = Integer.parseInt(br.readLine());
            Arr = new int[N+1];
            Arr[0] = -1;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) Arr[i] = Integer.parseInt(st.nextToken());
            Cache = new int[N+1];
            Arrays.fill(Cache,-1);
            Choices = new int[N+1];
            Seq = new ArrayList<>();
            //Logic
            System.out.println(lis(0)-1);
            reconstruct(Choices[0]);
            for(int i : Seq) System.out.print(i + " ");
            System.out.println();
//            System.out.println("Choices");
//            for(int i : Choices) System.out.print(i+ " ");
//            System.out.println();
//            System.out.println("Cache");
//            for(int i : Cache) System.out.print(i+ " ");
//            System.out.println();
        }
    }
    public static void reconstruct(int start){
        if(start!=-1) Seq.add(Arr[start]);
        int next = Choices[start];
        if(next!=-1) reconstruct(next);
    }
    public static int lis(int start){
        //base case 없음
        //Memoization
        if(Cache[start] != -1) return Cache[start];
        //Logic
        int ret = 1;
        int bestNext = -1;
        for(int next = start+1; next<=N; next++) {
            if(Arr[start] < Arr[next]){
                int cand = 1 + lis(next);
                if(cand > ret){
                    ret = cand;
                    bestNext = next;
                }
            }
        }
        Choices[start] = bestNext;
        return Cache[start] = ret;
    }
}

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
1 2 3 4
4
5 6 7 8
4
5 6 7 8
 */
