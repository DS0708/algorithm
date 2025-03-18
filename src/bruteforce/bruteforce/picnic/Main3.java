package bruteforce.bruteforce.picnic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Cases = Integer.parseInt(st.nextToken());

        for(int c=0; c<Cases; c++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] areFriends = new int[n][n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<m; i++){
                int f1 = Integer.parseInt(st.nextToken());
                int f2 = Integer.parseInt(st.nextToken());
                areFriends[f1][f2] = areFriends[f2][f1] = 1;
            }
            boolean[] taken = new boolean[n];
            int answer = findMinFriend(taken,n,areFriends);
            System.out.println(answer);
        }
    }
    public static int findMinFriend(boolean[] taken, int n, int[][] areFriends) {
        //가장 작은 친구 찾기
        int firstFree = -1;
        for(int i=0; i<n; i++){
            if(!taken[i]){
                firstFree = i;
                break;
            }
        }
        //base case
        if(firstFree==-1) return 1;
        //logic
        int ret = 0;
        for(int next = firstFree+1; next<n; next++){
            if(!taken[next] && areFriends[next][firstFree]==1){
                taken[firstFree] = taken[next] =true;
                ret += findMinFriend(taken, n, areFriends);
                taken[firstFree] = taken[next] =false;
            }
        }
        return ret;
    }
}


//입력
/*
3
2 1
0 1
4 6
0 1 1 2 2 3 3 0 0 2 1 3
6 10
0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5
 */