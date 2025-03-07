package greedy.lunchbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//시간복잡도 : O(cases * nlogn)
public class Main {
    static int N;
    static int[] M;
    static int[] E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0;c<cases;c++) {
            //init
            N = Integer.parseInt(br.readLine());
            M = new int[N];
            E = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) M[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) E[i] = Integer.parseInt(st.nextToken());
            //Logic
            System.out.println(heat());
        }
    }
    public static int heat() {
        //먹는 데 가장 오래 걸리는 순으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> o2[1]-o1[1]);
        for(int i=0;i<N;i++) pq.offer(new int[]{M[i],E[i]});
        //Simulation
        // i번째 음식을 먹는데 걸리는 시간은 M[i] + (0~i까지 음식을 데우는 시간의 합)
        // 이 중 에서 최대로 걸리는 시간이, 모든 사람이 음식을 다 먹는데 걸리는 시간 !!
        int ret = 0;
        int mSum = 0;
        for(int i=0;i<N;i++) {
            int[] cur = pq.poll();
            mSum += cur[0];
            int time = cur[1] + mSum;
            ret = Math.max(ret,time);
        }
        return ret;
    }
}

//문제 : https://algospot.com/judge/problem/read/LUNCHBOX

//입력
/*
2
3
2 2 2
2 2 2
3
1 2 3
1 2 1
 */

//출력
/*
8
7
 */
