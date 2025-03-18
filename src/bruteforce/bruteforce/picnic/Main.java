package bruteforce.bruteforce.picnic;

import java.io.*;
import java.util.StringTokenizer;

//Exhaustive Searching
//O(Cases*(n-1)*(n-3)*(n-5)...)
public class Main {
    static boolean[] taken;
    static boolean[][] areFriends;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int Cases = Integer.parseInt(br.readLine());

        for(int i=0; i<Cases; i++) {
            //각 케이스 마다 입력
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            taken = new boolean[n];
            areFriends = new boolean[n][n];
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                int f1 = Integer.parseInt(st.nextToken());
                int f2 = Integer.parseInt(st.nextToken());
                areFriends[f1][f2] = true;
                areFriends[f2][f1] = true;
            }
            int count = countParings();
            System.out.println(count);
        }
    }
    public static int countParings() {
        //남아 있는 학생 중 가장 빠른 번호의 학생 찾기
        int firstFree = -1;
        for(int i=0; i<taken.length; i++) {
            if(!taken[i]){
                firstFree = i;
                break;
            }
        }
        //남아 있는 학생이 없다면, 모든 학생이 짝을 찾은 한 가지의 경우의 수
        if(firstFree==-1) return 1;
        //Main Logic : 순서대로 짝이 가능한 경우의 수를 구함
        int ret=0;
        for(int pairWith = firstFree+1; pairWith<taken.length; pairWith++) {
            if(!taken[pairWith] && areFriends[firstFree][pairWith]){
                taken[firstFree] = taken[pairWith] = true;
                ret += countParings();
                taken[firstFree] = taken[pairWith] = false;
            }
        }
        return ret;
    }
}

//문제 : https://algospot.com/judge/problem/read/PICNIC

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

//출력
/*
1
3
4
 */