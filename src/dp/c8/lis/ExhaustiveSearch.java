package dp.c8.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//시간복잡도 O(N^N * N^2)
public class ExhaustiveSearch {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++) {
            N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            Arrays.fill(arr,-1);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            //output
            System.out.println(lis(arr));
        }
    }
    public static int lis(int[] arr){
        //base case
        if(arr[0]==-1) return 0;
        //Logic
        int ret = 0;
        for(int i=0; i<arr.length; i++){
            int[] temp = new int[N];
            Arrays.fill(temp,-1);
            int tempIdx = 0;
            for(int j=i+1; j<arr.length; j++){
                if(arr[i] < arr[j]) temp[tempIdx++] = arr[j];
            }
            ret = Math.max(ret, 1 + lis(temp));
        }
        return ret;
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