package graph.c28.wordchain;

import java.io.*;
import java.util.*;

public class EulerCircuit {
    static int N;
    static int[][] Graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Graph = new int[N+1][N+1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Graph[a][b] ++;
            Graph[b][a] ++;
        }
        Stack<Integer> circuit = new Stack<>();
        getEulerCircuit(1, circuit);
        while(!circuit.isEmpty()) System.out.print(circuit.pop() + " ");
    }
    public static void getEulerCircuit(int here, Stack<Integer> circuit){
        for(int i=1; i<=N; i++){
            while(Graph[here][i] > 0){
                Graph[here][i]--;
                Graph[i][here]--;
                getEulerCircuit(i, circuit);
            }
        }
        circuit.push(here);
    }
}

//입력
/*
7 8
1 2
1 4
2 3
3 4
3 5
3 7
5 6
6 7
 */
