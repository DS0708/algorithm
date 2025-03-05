package c8.dp.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//시간복잡도 O(n^2) -> 부분문제는 n개이고 재귀함수의 로직은 n
public class DP {
    static int[] cache;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c = 0; c < Cases; c++){
            int n = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            cache = new int[n];
            String[] strArr = br.readLine().split(" ");
            for(int i=0; i<n; i++) list.add(Integer.parseInt(strArr[i]));
            System.out.println(lisDP(0));
        }
    }
    public static int lisDP(int start){
        //Memoization
        if(cache[start] != 0) return cache[start];
        //base case
        int ret = 1;
        for(int next=start+1; next<list.size(); next++){
            if(list.get(start) < list.get(next)) ret = Math.max(ret, 1+lisDP(next));
        }
        return cache[start] = ret;
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
