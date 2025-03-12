package bitmask.graduation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//시간 복잡도 = O (cases * 2^N * M*2^N)
public class Main {
    static int INF = 987654321;
    static int N,K,M,L; //전공과목 수, 들어야할 과목 수, 학기 수, 한 학기 당 들을 수 있는 최대 과목 수
    static int[] Prerequisite;
    static int[] Classes;
    static int[][] Cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0;c<cases;c++) {
            //init
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            Prerequisite = new int[N];
            Classes = new int[M];
            Cache = new int[M][1<<N];
            Arrays.stream(Cache).forEach(row -> Arrays.fill(row,-1));
            int taken=0;
            //선수과목 입력
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                int preNum = Integer.parseInt(st.nextToken());
                for(int j=0; j<preNum; j++){
                    int preAdd = Integer.parseInt(st.nextToken());
                    Prerequisite[i] |= (1<<preAdd);
                }
            }
            //학기 당 개설 과목 입력
            for(int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine());
                int classNum = Integer.parseInt(st.nextToken());
                for(int j=0; j<classNum; j++){
                    int classAdd = Integer.parseInt(st.nextToken());
                    Classes[i] |= (1<<classAdd);
                }
            }
            //output
            int output = graduate(0,taken);
            if(output >= INF) System.out.println("IMPOSSIBLE");
            else System.out.println(output);
        }
    }
    public static int graduate(int semester, int taken){
        //base case 1 : K개 이상 다 들었을 떄
        if(Integer.bitCount(taken) >= K) return 0;
        //base case 2 : 더 이상 들을 학기가 없을 때
        if(semester == M) return INF;
        //Memoization
        if(Cache[semester][taken] != -1) return Cache[semester][taken];
        //Logic
        Cache[semester][taken] = INF;
        //이번 학기에 개설되는 과목 중 듣지 않은 과목 구하기
        int canTake = (Classes[semester] & ~taken);
        canTake &= ~taken;
        //선수 과목을 모두 수류한 과목만 구하기
        for(int i=0; i<N; i++){
            if( (canTake & (1<<i)) != 0 && (taken&Prerequisite[i]) != Prerequisite[i])
                canTake &= ~(1<<i);
        }
        //L이 안넘도록 부분 집합 구하기
        for(int take=canTake; take!=0; take = (take-1)&canTake){
            if(Integer.bitCount(take) > L) continue;
            Cache[semester][taken] = Math.min(Cache[semester][taken], 1 + graduate(semester+1, taken | take));
        }
        //공집합일 때 처리
        Cache[semester][taken] = Math.min(Cache[semester][taken], graduate(semester+1, taken));
        //return
        return Cache[semester][taken];
    }
}

// 문제 : https://algospot.com/judge/problem/read/GRADUATION

//입력
/*
2
4 4 4 4
0
1 0
3 0 1 3
0
4 0 1 2 3
4 0 1 2 3
3 0 1 3
4 0 1 2 3
4 2 2 4
1 1
0
1 3
1 2
3 0 2 3
3 1 2 3
 */

//출력
/*
3
IMPOSSIBLE
 */


