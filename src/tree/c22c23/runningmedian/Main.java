package tree.c22c23.runningmedian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//시간복잡도 O(NLogN)
public class Main {
    static int N;
    static int MOD = 20090711;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            RNG rng = new RNG(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            System.out.println(solve(rng));
        }
    }
    public static int solve(RNG rng){
        int ret = 0;
        int first = rng.seed;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1,o2) -> o2-o1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        maxHeap.add(first);
        for(int i=0; i<N; i++){
            ret = (ret + maxHeap.peek()) % MOD;
            if(maxHeap.size() == minHeap.size()){
                maxHeap.add(rng.next());
            }else{
                minHeap.add(rng.next());
            }
            if(maxHeap.peek() > minHeap.peek()){
                int maxH = maxHeap.poll();
                int minH = minHeap.poll();
                maxHeap.add(minH);
                minHeap.add(maxH);
            }
        }
        return ret;
    }
    static class RNG {
        int seed, a, b, ret;
        RNG(int a, int b) {
            this.seed = 1983;
            this.a = a;
            this.b = b;
        }
        int next(){
            //A[i] = (A[i-1] * a + b) % 20090711
            this.seed = (int)(((long)seed * a + b) % MOD);
            return this.seed;
        }
    }
}

//문제 : https://algospot.com/judge/problem/read/RUNNINGMEDIAN

//입력
/*
3
10 1 0
10 1 1
10000 1273 4936
 */

//출력
/*
19830
19850
2448920
 */