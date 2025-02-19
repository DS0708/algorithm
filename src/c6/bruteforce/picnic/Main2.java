package c6.bruteforce.picnic;

import java.io.*;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());

        for(int i = 0; i < C; i++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            boolean[] taken = new boolean[N];
            boolean[][] areFriends = new boolean[N][N];
            st = new StringTokenizer(br.readLine());
            for(int j=0 ; j < M ; j++){
                int f1 = Integer.parseInt(st.nextToken());
                int f2 = Integer.parseInt(st.nextToken());
                areFriends[f1][f2] = areFriends[f2][f1] = true;
            }
            int count = getCount(taken,areFriends, N);
            System.out.println(count);
        }
    }
    static int getCount(boolean[] taken, boolean[][] areFriends, int N){
        int firstFree = -1;
        //앞쪽부터 짝이 없는 친구 검색
        for(int i=0; i<N; i++){
            if(!taken[i]){
                firstFree = i;
                break;
            }
        }
        //모두 수행했는지 검사
        if(firstFree==-1) return 1;
        //Logic
        int ret = 0;
        for(int pairWith=firstFree+1; pairWith<N; pairWith++){
            if(!taken[pairWith] && areFriends[firstFree][pairWith]){
                taken[pairWith] = taken[firstFree] = true;
                ret += getCount(taken, areFriends, N);
                taken[pairWith] = taken[firstFree] = false;
            }
        }
        return ret;
    }
}
