package tree.c21.fortress;

import java.io.*;
import java.util.*;

public class Main {
    static int N, Longest;
    static int[] X;
    static int[] Y;
    static int[] R;
    public static class TreeNode{
        int number;
        List<TreeNode> child;
        TreeNode(int number){
            this.number = number;
            child = new ArrayList<>();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c = 0; c < cases; c++) {
            //init
            N = Integer.parseInt(br.readLine());
            X = new int[N];
            Y = new int[N];
            R = new int[N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                X[i] = Integer.parseInt(st.nextToken());
                Y[i] = Integer.parseInt(st.nextToken());
                R[i] = Integer.parseInt(st.nextToken());
            }
            //트리 만들기
            TreeNode root = getTree(0);

            //debug
//            System.out.println("Tree 출력!!");
//            printTree(root);
//            System.out.println();
            //debug

            //트리의 최장 경로 구하기
            System.out.println(getLongPath(root));
        }
    }
    public static int getLongPath(TreeNode root) {
        Longest = -1;
        int treeHeight = height(root);
        return Math.max(Longest, treeHeight);
    }
    public static int height(TreeNode root) {
        //base case
        if(root.child.isEmpty()) return 0;
        //Tree Height Logic
        PriorityQueue<Integer> PQ = new PriorityQueue<>((o1,o2)-> o2-o1); //subTree들의 height
        for(TreeNode ch : root.child){
            PQ.add(height(ch));
        }
        //Node to Node 의 최장 길이 구하기
        int firstHeight = PQ.poll();
        if(!PQ.isEmpty()){
            int secondHeight = PQ.poll();
            Longest = Math.max(Longest, firstHeight+secondHeight+2);
        }
        //Returns the tree height including the path length 1 from root to subtree
        return firstHeight+1;
    }
    static TreeNode getTree(int root){
        TreeNode ret = new TreeNode(root);
        for(int i=1; i<N; i++){
            if(isChild(root,i)) ret.child.add(getTree(i));
        }
        return ret;
    }
    static boolean isChild(int parent, int child){
        if(!encloses(parent,child)) return false;
        for(int i=0; i<N; i++)
            if(i!=parent&&i!=child && encloses(parent,i) && encloses(i,child)) return false;
        return true;
    }
    static boolean encloses(int a, int b){
        return R[a] > R[b] && sqrDist(a,b) < sqr(R[a]-R[b]);
    }
    static int sqrDist(int a, int b){
        return sqr(X[a]-X[b]) + sqr(Y[a]-Y[b]);
    }
    static int sqr(int x){
        return x*x;
    }

    //debug
    //    public static void printTree(TreeNode root){
//        Queue<TreeNode> q = new LinkedList<>();
//        q.add(root);
//        while(!q.isEmpty()){
//            TreeNode node = q.poll();
//            System.out.print(node.number + " ");
//            for(TreeNode ch : node.child){
//                q.add(ch);
//            }
//        }
//    }
    //debug

}

//문제 :

//입력
/*
2
3
5 5 15
5 5 10
5 5 5
8
21 15 20
15 15 10
13 12 5
12 12 3
19 19 2
30 24 5
32 10 7
32 9 4
 */

//출력
/*
2
5
 */
