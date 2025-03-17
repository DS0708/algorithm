package graph.c28.wordchain;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] Adj;
    static List<String> Graph[][];
    static int[] InDegree;
    static int[] OutDegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            //init
            N = Integer.parseInt(br.readLine());
            String[] words = new String[N];
            for(int i=0; i<N; i++) words[i] = br.readLine();
            //Logic
            System.out.println(solve(words));
        }
    }
    public static String solve(String[] words){
        makeGraph(words);
        if(!checkEuler()) return "IMPOSSIBLE";
        Stack<Integer> circuit = getEulerTrailOrCircuit();
        //하나의 컴포넌트에 속해 있는지 검사
        if(circuit.size() != words.length + 1) return "IMPOSSIBLE";
        //문자열 만들기
        StringBuilder sb = new StringBuilder();
        int temp = circuit.pop();
        while(!circuit.isEmpty()){
            int temp2 = circuit.pop();
            sb.append(Graph[temp][temp2].get(0)).append(" ");
            Graph[temp][temp2].remove(0);
            temp = temp2;
        }
        return sb.toString();
    }
    public static Stack<Integer> getEulerTrailOrCircuit(){
        Stack<Integer> circuit = new Stack<>();
        //트레일이라면 시작점을 찾아야함
        for(int i=0; i<26; i++){
            if(OutDegree[i] == InDegree[i]+1){
                getEulerCircuit(i, circuit);
                return circuit;
            }
        }
        //서킷이라면 존재하는 아무점에서나 시작 가능
        for(int i=0; i<26; i++){
            if(OutDegree[i] >= 1){
                getEulerCircuit(i, circuit);
                return circuit;
            }
        }
        //아무것도 아니라면 빈 stack 리턴
        return circuit;
    }
    public static void getEulerCircuit(int here, Stack<Integer> circuit){
        for(int i=0; i<26; i++){
            while(Adj[here][i]>0){
                Adj[here][i]--;
                getEulerCircuit(i, circuit);
            }
        }
        circuit.push(here);
    }
    public static boolean checkEuler(){
        int plus = 0;
        int minus = 0;
        for(int i=0; i<26; i++){
            int delta = InDegree[i] - OutDegree[i];
            if(delta > 1 || delta < -1) return false;
            if(delta==1) plus++;
            if(delta==-1) minus++;
        }
        return (minus==1 && plus==1) || (minus==0 && plus==0);
    }
    public static void makeGraph(String[] words){
        Adj = new int[26][26];
        Graph = new ArrayList[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                Graph[i][j] = new ArrayList<>();
            }
        }
        InDegree = new int[26];
        OutDegree = new int[26];
        for(int i=0; i<N; i++){
            int a = words[i].charAt(0) - 'a';
            int b = words[i].charAt(words[i].length()-1) - 'a';
            Graph[a][b].add(words[i]);
            OutDegree[a]++;
            InDegree[b]++;
            Adj[a][b]++;
        }
    }
}

//문제 : https://algospot.com/judge/problem/read/WORDCHAIN

//입력
/*
3
4
dog
god
dragon
need
3
aa
ab
bb
2
ab
cd
 */

//출력
/*
need dog god dragon
aa ab bb
IMPOSSIBLE
 */
