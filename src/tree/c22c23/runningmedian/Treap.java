package tree.c22c23.runningmedian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

public class Treap {
    static int N;
    static int MOD = 20090711;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            RNG rng = new RNG(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            System.out.println(solve(rng));
        }
    }
    public static int solve(RNG rng){
        Node root = null;
        int ret = 0;
        for(int i=0; i<N; i++) {
            root = insert(root,new Node(rng.next()));
            ret = (ret + kth(root,(root.size+1)/2))%MOD;
        }
        return ret;
    }
    public static int kth(Node root, int k){
        //base case
        if(root == null) return 0;
        //Logic
        int leftSize = root.left != null ? root.left.size : 0 ;
        if(k <= leftSize) return kth(root.left, k);
        else if (k==leftSize+1) return root.key;
        else return kth(root.right, k-leftSize-1);
    }
    public static Node insert(Node root, Node node){
        //base case
        if(root==null) return node;
        //Logic
        if(root.priority < node.priority) {
            Map.Entry<Node, Node> splitted = split(root, node.key);
            node.setLeft(splitted.getKey());
            node.setRight(splitted.getValue());
            return node;
        }else if(root.key > node.key){
            root.setLeft(insert(root.left, node));
        }else{
            root.setRight(insert(root.right, node));
        }
        return root;
    }
    public static Map.Entry<Node, Node> split(Node root, int standardKey) {
        //base case
        if(root == null) return new AbstractMap.SimpleEntry<>(null, null);
        //Logic
        if(root.key > standardKey){
            Map.Entry<Node, Node> splitted = split(root.left, standardKey);
            root.setLeft(splitted.getValue());
            return new AbstractMap.SimpleEntry<>(splitted.getKey(), root);
        }else{
            Map.Entry<Node, Node> splitted = split(root.right, standardKey);
            root.setRight(splitted.getKey());
            return new AbstractMap.SimpleEntry<>(root, splitted.getValue());
        }
    }
    static class Node{
        int key;
        int size, priority;
        Node left, right;
        Node(int key){
            this.key = key;
            this.size = 1;
            this.priority = getRandom();
        }
        void setLeft(Node node){
            this.left = node;
            calc();
        }
        void setRight(Node node){
            this.right = node;
            calc();
        }
        void calc(){
            this.size = 1;
            if(left!=null) this.size+= left.size;
            if(right!=null) this.size+= right.size;
        }
        int getRandom(){
            Random random = new Random();
            return random.nextInt(N*10);
        }
    }
    static class RNG {
        int seed, a, b, ret;
        RNG(int a, int b) {
            this.seed = 1983;
            this.a = a;
            this.b = b;
        }
        int next(){
            int ret = seed;
            this.seed = (int)(((long)seed * a + b) % MOD);
            return ret;
        }
    }
    public static Node erase(Node root, int key){
        //base case
        if(root == null) return null;
        //Logic
        if(root.key == key){
            Node node = merge(root.left, root.right);
            return node;
        }else if (root.key > key){
            root.setLeft(erase(root.left, key));
        }else{
            root.setRight(erase(root.right, key));
        }
        return root;
    }
    public static Node merge(Node root1, Node root2){
        if(root1 == null) return root2;
        if(root2 == null) return root1;
        if(root1.priority > root2.priority){
            root1.setRight(merge(root1.right, root2));
            return root1;
        }else{
            root2.setLeft(merge(root1, root2.left));
            return root2;
        }
    }
    public static int countLessThan(Node root, int key){
        //base case
        if(root == null) return 0;
        //Logic
        if(root.key >= key) return countLessThan(root.left, key);
        int leftSize = root.left != null ? root.left.size : 0 ;
        return leftSize + 1 + countLessThan(root.right, key);
    }
}

//문제 : https://algospot.com/judge/problem/read/RUNNINGMEDIAN

//입력
/*
3
10 1 0
10 1 1
10000 1273 4936
 */

//출력
/*
19830
19850
2448920
 */
