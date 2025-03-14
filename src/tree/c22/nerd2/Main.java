package tree.c22.nerd2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//시간 복잡도 : O (NLogN)
public class Main {
    static TreeMap<Integer, Integer> treeMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            //init
            treeMap = new TreeMap<>();
            int n = Integer.parseInt(br.readLine());
            int sum = 0;
            for(int i=0; i<n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                sum += registered(x,y);
            }
            System.out.println(sum);
        }
    }
    public static int registered(int x, int y){
        if(isDominated(x,y)) return treeMap.size();
        removeDominated(x,y);
        treeMap.put(x,y);
        return treeMap.size();
    }
    public static boolean isDominated(int x, int y){
        Integer higherX = treeMap.higherKey(x);
        if(higherX == null) return false;
        return y < treeMap.get(higherX);
    }
    public static void removeDominated(int x, int y){
        Integer lowerX = treeMap.lowerKey(x);
        if(lowerX == null) return;
        NavigableMap<Integer, Integer> navigableMap = treeMap.headMap(lowerX, true).descendingMap();
        List<Integer> deleteList = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : navigableMap.entrySet()){
            if(y > entry.getValue()) deleteList.add(entry.getKey());
            else break;
        }
        //delete
        for(int i : deleteList) treeMap.remove(i);
    }
}

//문제 : https://algospot.com/judge/problem/read/NERD2

//입력
/*
2
4
72 50
57 67
74 55
64 60
5
1 5
2 4
3 3
4 2
5 1
 */

//출력
/*
8
15
 */
