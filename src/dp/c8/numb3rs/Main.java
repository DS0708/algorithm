package dp.c8.numb3rs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//search(here,days) -> 두니발 박사가 현재 here에 있고 days일 만큼 지났을 때, 목표 T에 도달하는 확률 (days는 0부터)
//시간복잡도 O(cases * N * ND * N)

//search2(here,dyas) -> 두니발 박사가 현재 here에 있고 days일 일때, P에서 시작해서 현재 위치에 있을 확률 (days는 P부터)
//시간복잡도 O(cases * ND * N)
public class Main {
    static int N,D,P,T;
    static int[][] Connected;
    static int[] Target;
    static double[][] Cached;
    static int[] Degree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            //Init
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            Connected = new int[N][N];
            Degree = new int[N];
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++) {
                    Connected[i][j] = Integer.parseInt(st.nextToken());
                    if(Connected[i][j]==1){
                        Degree[i]++;
                    }
                }
            }
            int targetNum = Integer.parseInt(br.readLine());
            Target = new int[targetNum];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<targetNum;i++) Target[i] = Integer.parseInt(st.nextToken());
            //Logic
            init2();
            for(int i=0; i<targetNum;i++){
//                init(Target[i]);
//                System.out.print(search(P,0)+" ");
                System.out.print(search2(Target[i],D)+" ");
            }
            System.out.println();
        }
    }
    public static void init2(){
        Cached = new double[N][D+1];
        Arrays.stream(Cached).forEach(r->Arrays.fill(r,-1));
    }
    public static double search2(int here,int days){
        //base case
        if(days==0) return (here==P ? 1.0 : 0.0);
        //Memoization
        if(Cached[here][days] > -0.5) return Cached[here][days];
        //Logic
        double ret = 0;
        for(int there=0; there<N; there++){
            if(Connected[here][there]==1){
                ret += search2(there, days-1) / Degree[there];
            }
        }
        return Cached[here][days] = ret;
    }
    public static void init(int target){
        T = target;
        Cached = new double[N][D+1];
        Arrays.stream(Cached).forEach(r->Arrays.fill(r,-1));
    }
    public static double search(int here, int days){
        //base case
        if(days==D){
            return here==T ? 1.0 : 0.0;
        }
        //Memoization
        if(Cached[here][days] > -0.5) return Cached[here][days];
        //Logic
        double ret = 0.0;
        for(int i=0; i<N; i++){
            if(Connected[here][i]==1){
                ret += search(i,days+1) / Degree[here];
            }
        }
        return Cached[here][days] = ret;
    }
}

//문제 : https://algospot.com/judge/problem/read/NUMB3RS

//입력
/*
2
5 2 0
0 1 1 1 0
1 0 0 0 1
1 0 0 0 0
1 0 0 0 0
0 1 0 0 0
3
0 2 4
8 2 3
0 1 1 1 0 0 0 0
1 0 0 1 0 0 0 0
1 0 0 1 0 0 0 0
1 1 1 0 1 1 0 0
0 0 0 1 0 0 1 1
0 0 0 1 0 0 0 1
0 0 0 0 1 0 0 0
0 0 0 0 1 1 0 0
4
3 1 2 6
 */

//출력
/*
0.83333333 0.00000000 0.16666667
0.43333333 0.06666667 0.06666667 0.06666667
 */
