package dp.c9.packing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,Capacity;
    static String[] Items;
    static int[] Volume;
    static int[] Need;
    static int[][] Cache;
    static List<String> Choices;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++) {
            //init
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            Capacity = Integer.parseInt(st.nextToken());
            Items = new String[N];
            Volume = new int[N];
            Need = new int[N];
            Cache = new int[N][Capacity+1];
            Choices = new ArrayList<>();
            Arrays.stream(Cache).forEach(r->Arrays.fill(r, -1));
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                Items[i] = st.nextToken();
                Volume[i] = Integer.parseInt(st.nextToken());
                Need[i] = Integer.parseInt(st.nextToken());
            }
            //Logic
            System.out.print(pack(0, Capacity) + " ");
            reconstruct(0, Capacity);
            System.out.println(Choices.size());
            for(String str : Choices) System.out.println(str);
        }
    }
    public static void reconstruct(int itemNum, int capacity) {
        //base case
        if(itemNum==N) return;
        //Logic
        if(pack(itemNum, capacity) == pack(itemNum+1, capacity)){
            reconstruct(itemNum+1, capacity);
        }else{
            Choices.add(Items[itemNum]);
            reconstruct(itemNum+1, capacity-Volume[itemNum]);
        }
    }
    public static int pack(int itemNum, int capacity) {
        //base case 1
        if(capacity < 0) return -(int)1e7;
        //base case 2
        if(itemNum == N) return 0;
        //Memoization
        if(Cache[itemNum][capacity] != -1) return Cache[itemNum][capacity];
        //Logic
        int ret = 0;
        ret = Math.max(pack(itemNum+1, capacity - Volume[itemNum]) + Need[itemNum], pack(itemNum+1, capacity));
        return Cache[itemNum][capacity] = ret;
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
