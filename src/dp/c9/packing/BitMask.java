package dp.c9.packing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도 O(2^N * N)
public class BitMask {
    static int N, Capacity;
    static long BestNeed;
    static String[] Item;
    static int[] Volume;
    static int[] Need;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            Capacity = Integer.parseInt(st.nextToken());
            Item = new String[N];
            Volume = new int[N];
            Need = new int[N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                Item[i] = st.nextToken();
                Volume[i] = Integer.parseInt(st.nextToken());
                Need[i] = Integer.parseInt(st.nextToken());
            }
            //Logic
            long bestBit = solve((1<<N)-1);
            System.out.println(BestNeed + " " + Long.bitCount(bestBit));
            printItem(bestBit);
        }
    }
    public static void printItem(long bit) {
        for(int i=0; i<N; i++) {
            if((bit&(1L<<i))!=0) System.out.println(Item[i]);
        }
    }
    public static long solve(long bit){
        long bestBit = 0;
        BestNeed= 0;
        for(long b=bit; b>0; b=(b-1)&bit){
            int cap = getCapacity(b);
            if(cap <= Capacity){
                int need = getNeed(b);
                if(need > BestNeed){
                    BestNeed = need;
                    bestBit = b;
                }
            }
        }
        return bestBit;
    }
    public static int getNeed(long bit){
        int need = 0;
        for(int i=0; i<N; i++){
            if((bit&(1L<<i))!=0) need += Need[i];
        }
        return need;
    }
    public static int getCapacity(long bit){
        int cap = 0;
        for(int i=0; i<N; i++){
            if((bit&(1L<<i)) != 0) cap += Volume[i];
        }
        return cap;
    }
}

//문제 : https://algospot.com/judge/problem/read/PACKING

//입력
/*
2
6 10
laptop 4 7
camera 2 10
xbox 6 6
grinder 4 7
dumbell 2 5
encyclopedia 10 4
6 17
laptop 4 7
camera 2 10
xbox 6 6
grinder 4 7
dumbell 2 5
encyclopedia 10 4
 */

//출력
/*
24 3
laptop
camera
grinder
30 4
laptop
camera
xbox
grinder
 */
