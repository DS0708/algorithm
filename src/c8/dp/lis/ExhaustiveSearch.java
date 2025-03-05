package c8.dp.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExhaustiveSearch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c = 0; c < Cases; c++){
            int n = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();
            String[] strArr = br.readLine().split(" ");
            for(int i=0; i<n; i++) list.add(Integer.parseInt(strArr[i]));
            System.out.println(lis(list));
        }
    }
    public static int lis(List<Integer> list){
        //base case
        if(list.isEmpty()) return 0;
        //Logic
        int ret = 0;
        for(int i=0; i<list.size(); i++){
            List<Integer> tmpList = new ArrayList<>(list);
            for(int j=i+1; j<list.size(); j++){
                if(list.get(i) < list.get(j)) tmpList.add(list.get(j));
            }
            ret = Math.max(ret, 1+ lis(tmpList));
        }
        return ret;
    }
}

//문제 : https://algospot.com/judge/problem/read/LIS

//입력
/*
3
4
1 2 3 4
8
5 4 3 2 1 6 7 8
8
5 6 7 8 1 2 3 4
 */

//출력
/*
4
4
4
 */
