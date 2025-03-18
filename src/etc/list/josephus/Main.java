package etc.list.josephus;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            solve(n, k);
        }
    }
    public static void solve(int n, int k) {
        List<Integer> list = new LinkedList<>();
        for(int i=1; i<=n; i++) list.add(i);
        int kill = 0;
        while(list.size()>2){
            list.remove(kill);
            kill = (kill+k-1)%list.size();
        }
        if(list.get(0) < list.get(1)){
            System.out.println(list.get(0) + " " + list.get(1));
        }else{
            System.out.println(list.get(1) + " " + list.get(0));
        }
    }
}

//문제 : https://algospot.com/judge/problem/read/JOSEPHUS

//입력
/*
2
6 3
40 3
 */

//출력
/*
3 5
11 26
 */