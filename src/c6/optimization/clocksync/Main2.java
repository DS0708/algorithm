package c6.optimization.clocksync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {
    static final int clockNum = 16;
    static final int switchNum = 10;
    static List<Integer>[] switchs;
    static int minClick;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //switch 초기화
        initSwitchs();
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Cases = Integer.parseInt(st.nextToken());
        //logic
        for(int c=0; c<Cases; c++) {
            st = new StringTokenizer(br.readLine());
            int[] clocks = new int[clockNum];
            for(int i=0; i<clockNum; i++) {
                clocks[i] = Integer.parseInt(st.nextToken());
            }
            minClick = INF;
            //Logic
            solve(clocks, 0,0);
            //output
            if(minClick == INF) System.out.println(-1);
            else System.out.println(minClick);
        }
    }
    public static void solve(int[] clocks, int curSwitch, int curClick) {
        //가지치기
        if(curClick >= minClick) return;
        //base case
        if(curSwitch == switchNum){
            if(complete(clocks)){
                minClick = Math.min(curClick, minClick);
            }
            return;
        }
        //Logic
        for(int i=0; i<4; i++){
            solve(clocks, curSwitch+1, curClick + i);
            clickSwitch(clocks,curSwitch);
        }
    }
    public static void clickSwitch(int[] clocks, int curSwitch) {
        for(int swt : switchs[curSwitch]){
            clocks[swt] += 3;
            if(clocks[swt]==15) clocks[swt] = 3;
        }
    }
    public static boolean complete(int[] clocks) {
        boolean ok = true;
        for(int i=0; i<clockNum; i++){
            if(clocks[i]!=12){
                ok = false;
                break;
            }
        }
        return ok;
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

/*
2
12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12
12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6
 */
