package etc.qstackdeque.ites;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            System.out.println(solve(k,n));
        }
    }
    private static int solve(int k, int n) {
        RNG rng = new RNG();
        int ret = 0;
        long rangeSum = 0;
        Queue<Long> Q = new LinkedList<>();
        for(int i=0; i<n; i++) {
            long newSignal = rng.next();
            rangeSum += newSignal;
            Q.offer(newSignal);

            while(rangeSum > k){
                long delete = Q.poll();
                rangeSum -= delete;
            }

            if(rangeSum == k) ret ++;
        }
        return ret;
    }
    private static class RNG{
        long seed;
        private RNG(){
            seed = 1983;
        }
        private long next(){
            long ret = seed;
            long mod = (long)Math.pow(2,32);
            seed = ((seed * 214013)%mod + 2531011) % mod;
            return ret % 10000 + 1;
        }
    }
}

//문제 : https://algospot.com/judge/problem/read/ITES

//입력
/*
3
8791 20
5265 5000
3578452 5000000
 */

//출력
/*
1
4
1049
 */
