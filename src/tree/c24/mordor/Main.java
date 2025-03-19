package tree.c24.mordor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            //init
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] minArr = new int[n];
            int[] maxArr = new int[n];
            for(int i=0; i<n; i++) {
                int addNum = Integer.parseInt(st.nextToken());
                minArr[i] = addNum;
                maxArr[i] = -1 * addNum;
            }
            MinRMQ minRmq = new MinRMQ(minArr);
            MinRMQ maxRmq = new MinRMQ(maxArr);
            //Logic
            for(int i=0; i<q; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int ret = (maxRmq.query(from,to) + minRmq.query(from,to))*-1;
                System.out.println(ret);
            }
        }
    }
    private static class MinRMQ{
        int size;
        int[] rangeMin;
        private MinRMQ(int[] arr){
            size = arr.length;
            rangeMin = new int[size*4];
            init(arr,1,0,size-1);
        }
        private int init(int[] arr, int node, int left, int right){
            if(left==right) return rangeMin[node] = arr[left];
            int mid = (left+right)/2;
            return rangeMin[node] = Math.min(init(arr,node*2,left,mid), init(arr, node*2+1, mid+1, right));
        }
        private int query(int left, int right){
            return query(left, right, 1, 0, size-1);
        }
        private int query(int left, int right, int node, int nodeLeft, int nodeRight){
            if(left > nodeRight || right < nodeLeft) return Integer.MAX_VALUE;
            if(left <= nodeLeft && nodeRight <= right) return rangeMin[node];
            int mid = (nodeLeft+nodeRight)/2;
            return Math.min(query(left,right,node*2,nodeLeft,mid), query(left, right, node*2+1, mid+1, nodeRight));
        }
    }
}

//문제 : https://algospot.com/judge/problem/read/MORDOR

//입력
/*
2
3 1
1 2 3
0 2
10 4
3 9 5 6 10 8 7 1 2 4
1 6
4 7
9 9
5 8
 */

//출력
/*
2
5
9
0
7
 */
