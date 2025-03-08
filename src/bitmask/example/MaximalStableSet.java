package bitmask.example;

import java.io.*;
import java.util.*;

//극대 안정 집합을 비트마스킹으로 풀기
public class MaximalStableSet {
    static int N;
    static int[] Explodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Explodes = new int[N];
        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            Explodes[n1] |= (1<<n2);
            Explodes[n2] |= (1<<n1);
        }
        System.out.println(countStableSet());
    }
    public static int countStableSet() {
        int ret = 0;
        //모든 집합 만들기, 모든 집합이 1개만 있을 때 안정 집합이므로 공집합은 고려할 필요가 없음
        for(int set=1; set<(1<<N); set++){
            //우선 안정적이 아니라면 셀 필요가 없다.
            if(!isStable(set)) continue;
            //극대 안정 집합인지 확인하기 위해, 넣을 수 있는 다른 물질이 있나 확인
            boolean canExtend = false;
            for(int add=0; add<N; add++){
                //add가 집합에 포함되어 있지 않고, set에 add를 넣어도 안정적이라면 극대 안정 집합이 아님!!
                if( (set & (1<<add)) == 0 && (Explodes[add] & set) == 0 ){
                    canExtend = true;
                    break;
                }
            }
            if(!canExtend) ret++;
        }
        return ret;
    }
    public static boolean isStable(int set) {
        //집합에 포함된 i번째 원소와 같이 두었을 때 폭발하는 물질이 set에 있다면 false
        for(int i=0; i<N; i++)
            if((set & (1<<i)) !=0 && (Explodes[i] & set) != 0) return false;
        return true;
    }
}

//입력, 4개의 원소이고 0과1이 같이 있으면 폭발, 2와 3이 같이 있으면 폭발
/*
4
2
0 1
2 3
 */

//출력, {0,2}, {0,3}, {1,2}, {1,3} 이렇게 4개가 존재
/*
4
 */
