package graph.c28.dictionary;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] graph;
    static List<Integer> graphList;
    static Stack<Integer> stack;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++) {
            N = Integer.parseInt(br.readLine());
            graph = new int[26][26];
            graphList = new ArrayList<>();
            stack = new Stack<>();
            visited = new boolean[26];
            String[] input = new String[N];
            for(int i=0; i<N; i++){
                input[i] = br.readLine();
            }
            //그래프 생성
            makeGraph(input);
            //위상정렬
            String output = topologicalSort();
            if(output.isEmpty()) {
                System.out.println("INVALID HYPOTHESIS");
            }else{
                System.out.println(output);
            }
        }
    }
    public static String topologicalSort() {
        //init
        StringBuilder sb = new StringBuilder();
        //Logic
        for(int i : graphList){
            if(!visited[i]) dfs(i);
        }
        //cycle 검사
        for(int i=0; i<stack.size(); i++){
            for(int j=i+1; j<stack.size(); j++){
                if(graph[stack.get(i)][stack.get(j)]==1) return sb.toString();
            }
        }
        //output만들기
        while(!stack.isEmpty()){
            char ch = (char)(stack.pop()+'a');
            sb.append(ch);
        }
        for(int i=0; i<26; i++){
            char ch = (char)(i+'a');
            if(!visited[i]) sb.append(ch);
        }

        return sb.toString();
    }
    public static void dfs(int here){
        visited[here] = true;
        for(int i=0; i<26; i++){
            if(graph[here][i]==1 && !visited[i]) dfs(i);
        }
        stack.push(here);
    }
    public static void makeGraph(String[] input) {
        for(int i=0; i<N-1; i++){
            int minLen = Math.min(input[i].length(), input[i+1].length());
            for(int j=0; j<minLen; j++){
                if(input[i].charAt(j) != input[i+1].charAt(j)){
                    int u = input[i].charAt(j) - 'a';
                    int v = input[i+1].charAt(j) - 'a';
                    graphList.add(u);
                    graph[u][v]=1;
                    break;
                }
            }
        }
    }
}

//문제 : https://algospot.com/judge/problem/read/DICTIONARY

//입력
/*
3
3
ba
aa
ab
5
gg
kia
lotte
lg
hanhwa
6
dictionary
english
is
ordered
ordinary
this
 */

//출력
/*
INVALID HYPOTHESIS
ogklhabcdefijmnpqrstuvwxyz
deiotabcfghjklmnpqrsuvwxyz
 */
