package greedy.matchorder;

import java.io.*;
import java.util.*;

//시간복잡도 : O(cases * nlogn)
public class Main {
    static TreeMap<Integer, Integer> koreanRatings; //STL의 multiset을 구현하기 위해 사용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++) {
            //init
            int n = Integer.parseInt(br.readLine());
            int[] russian = new int[n];
            koreanRatings = new TreeMap<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) russian[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                int kor = Integer.parseInt(st.nextToken());
                koreanRatings.put(kor, koreanRatings.getOrDefault(kor,0)+1);
            }
            //Logic
            System.out.println(order(russian));
        }
    }
    //모든 한국 선수가 특정 러시아 선수를 이길 수 없다면, 레이팅이 제일 낮은 선수를 내보냄
    //나머지 경우에는 러시아 선수의 레이팅을 넘는 선수 중 제일 레이팅이 낮은 한국 선수를 내보냄
    public static int order(int[] russian) {
        int wins = 0;
        for(int rus : russian) {
            int korMax = koreanRatings.lastKey();
            if(rus > korMax) {
                int korLower = koreanRatings.firstKey();
                removeOne(korLower);
            }else{
                int korCeiling = koreanRatings.ceilingKey(rus);
                removeOne(korCeiling);
                wins++;
            }
        }
        return wins;
    }
    public static void removeOne(int key) {
        int val = koreanRatings.get(key);
        if(val > 1) koreanRatings.put(key, val-1);
        else koreanRatings.remove(key);
    }
}

//문제 : https://algospot.com/judge/problem/read/MATCHORDER

//입력
/*
3
6
3000 2700 2800 2200 2500 1900
2800 2750 2995 1800 2600 2000
3
1 2 3
3 2 1
4
2 3 4 5
1 2 3 4
 */

//출력
/*
5
3
3
 */