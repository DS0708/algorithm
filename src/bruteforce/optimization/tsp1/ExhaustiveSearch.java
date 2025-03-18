package bruteforce.optimization.tsp1;

import java.io.*;
import java.util.*;

public class ExhaustiveSearch {
    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Cases = Integer.parseInt(st.nextToken());
        //logic
        for(int c = 0; c < Cases; c++) {
            //case당 input
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            double[][] dist = new double[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    dist[i][j] = Double.parseDouble(st.nextToken());
                }
            }
            //logic
            //debug
            //System.out.println("case: "+(c+1)+"\n");
            //debug
            double answer = Double.MAX_VALUE;
            for(int i=0; i<N; i++) {
                boolean[] visited = new boolean[N];
                Stack<Integer> path = new Stack<>();
                answer = Math.min(answer, shortestPath(i, path, dist ,visited,0));
            }
            System.out.printf("%.10f\n",answer);
        }
    }
    public static double shortestPath(int start, Stack<Integer> path, double[][] dist, boolean[] visited, double currentDist) {
        //start 노드부터 시작
        if(path.size()==0){
            path.push(start);
            visited[start] = true;
        }
        //base case : 모두 돌았으면 종료
        if(path.size() == visited.length){
            return currentDist;
        }
        //logic
        double ret = Double.MAX_VALUE;
        for(int next=0; next<visited.length; next++){
            if(!visited[next]){
                int cur = path.peek();
                path.push(next);
                visited[next] = true;
                //debug
                //System.out.println("shortestPath 호출 전 ->  " + "cur: " + cur + ", next: " + next + ", currentDist: " + currentDist);
                //debug
                double cand = shortestPath(start, path, dist, visited, currentDist + dist[cur][next]);
                //debug
                //System.out.println("shortestPath 호출 후 ->  " + "cur: " + cur + ", next: " + next + ", cand: " + cand);
                //debug
                path.pop();
                visited[next] = false;
                ret = Math.min(ret,cand);
            }
        }
        return ret;
    }
}


//문제 : https://algospot.com/judge/problem/read/TSP1

//입력
/*
2
3
0.0000000000  611.6157225201  648.7500617289
611.6157225201  0.0000000000  743.8557967501
648.7500617289  743.8557967501  0.0000000000
4
0.0000000000  326.0008994586  503.1066076077  290.0250922998
326.0008994586  0.0000000000  225.1785728436  395.4019367384
503.1066076077  225.1785728436  0.0000000000  620.3945520632
290.0250922998  395.4019367384  620.3945520632  0.0000000000
 */

//출력
/*
1260.3657842490
841.2045646020
 */