package bruteforce.optimization.clocksync;

import java.io.*;
import java.util.*;

public class Main {
    static final int clockNum = 16;
    static final int switchNum = 10;
    static List<Integer>[] switchs;
    static int[] clocks;
    static int curMin;
    public static void main(String[] args) throws IOException {
        initSwitchs();
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Cases = Integer.parseInt(st.nextToken());
        //logic
        for(int c = 0; c < Cases; c++) {
            //case당 input
            st = new StringTokenizer(br.readLine());
            clocks = new int[16];
            for(int i=0; i<clockNum; i++){
                clocks[i] = Integer.parseInt(st.nextToken());
            }
            //logic
            curMin = Integer.MAX_VALUE; //가지치기를 위한
            int answer = findMinPressSwitch(0, 0);
            if(answer == Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(answer);
        }
    }
    public static int findMinPressSwitch(int c, int pressSwitch){
        //가지치기
        if(pressSwitch >= curMin) return curMin;
        //base case : 모든 스위치의 경우의 수를 다 확인했을 때
        if(c==switchNum){
            return allClear(clocks) ? pressSwitch : Integer.MAX_VALUE;
        }else{
            for(int i=0; i<4; i++){
                curMin = Math.min(curMin, findMinPressSwitch(c+1, pressSwitch+i));
                push(c);
            }
            return curMin;
        }
    }
    public static void push(int sn){
        for(int  clock : switchs[sn]){
            clocks[clock] += 3;
            if(clocks[clock]==15) clocks[clock] = 3;
        }
    }
    public static boolean allClear(int[] clocks){
        boolean clear = true;
        for(int i=0; i<clockNum; i++){
            if(clocks[i]!=12){
                clear = false;
                break;
            }
        }
        return clear;
    }

    public static void initSwitchs(){
        switchs = new List[switchNum];
        switchs[0] = new ArrayList<>();
        switchs[0].add(0); switchs[0].add(1); switchs[0].add(2);
        switchs[1] = new ArrayList<>();
        switchs[1].add(3); switchs[1].add(7); switchs[1].add(9); switchs[1].add(11);
        switchs[2] = new ArrayList<>();
        switchs[2].add(4); switchs[2].add(10); switchs[2].add(14); switchs[2].add(15);
        switchs[3] = new ArrayList<>();
        switchs[3].add(0); switchs[3].add(4); switchs[3].add(5); switchs[3].add(6); switchs[3].add(7);
        switchs[4] = new ArrayList<>();
        switchs[4].add(6); switchs[4].add(7); switchs[4].add(8); switchs[4].add(10); switchs[4].add(12);
        switchs[5] = new ArrayList<>();
        switchs[5].add(0); switchs[5].add(2); switchs[5].add(14); switchs[5].add(15);
        switchs[6] = new ArrayList<>();
        switchs[6].add(3); switchs[6].add(14); switchs[6].add(15);
        switchs[7] = new ArrayList<>();
        switchs[7].add(4); switchs[7].add(5); switchs[7].add(7); switchs[7].add(14); switchs[7].add(15);
        switchs[8] = new ArrayList<>();
        switchs[8].add(1); switchs[8].add(2); switchs[8].add(3); switchs[8].add(4); switchs[8].add(5);
        switchs[9] = new ArrayList<>();
        switchs[9].add(3); switchs[9].add(4); switchs[9].add(5); switchs[9].add(9); switchs[9].add(13);
    }
}

//문제: https://algospot.com/judge/problem/read/CLOCKSYNC

//입력
/*
2
12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12
12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6
 */

//출력
/*
2
9
 */