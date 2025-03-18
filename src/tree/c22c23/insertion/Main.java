package tree.c22c23.insertion;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0;c<cases;c++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++) arr[i] = Integer.parseInt(st.nextToken());
            int[] ret = solve(arr);
            for(int i : ret) System.out.print(i + " ");
        }
    }
    public static int[] solve(int[] arr){
        int n = arr.length;
        int[] ret = new int[arr.length];
        Node root = new Node(1);
        for(int i=2; i<=n; i++) root = insert(root,new Node(i));
        for(int i=arr.length-1; i>=0; i--){
            ret[i] = kth(root, n - arr[i]);
            n--;
            root = erase(root, ret[i]);
        }
        return ret;
    }
    public static int countLessThan(Node root, int k){
        if(root == null) return 0;
        if(root.key >= k) return countLessThan(root.left, k);
        int leftSize = root.left != null ? root.left.size : 0;
        return leftSize + 1 + countLessThan(root.right, k);
    }
    public static int kth(Node root, int k){
        int leftSize = 0;
        if(root.left != null) leftSize = root.left.size;
        if(k <= leftSize) return kth(root.left, k);
        else if (k == leftSize+1) return root.key;
        else return kth(root.right, k-1-leftSize);
    }
    public static Node erase(Node node, int key){
        if(node == null) return node;
        if(node.key == key){
            return merge(node.left, node.right);
        }else if (node.key > key){
            node.setLeft(erase(node.left, key));
        }else{
            node.setRight(erase(node.right, key));
        }
        return node;
    }
    public static Node merge(Node node1, Node node2){
        if(node1 == null) return node2;
        if(node2 == null) return node1;
        if(node1.priority > node2.priority){
            node1.setRight(merge(node1.right, node2));
            return node1;
        }else{
            node2.setLeft(merge(node1, node2.left));
            return node2;
        }
    }
    public static Node insert(Node root, Node node){
        //base case
        if(root==null) return node;
        //Logic
        if(root.priority < node.priority){
            Map.Entry<Node, Node> splitted = split(root, node.key);
            node.setLeft(splitted.getKey());
            node.setRight(splitted.getValue());
            return node;
        }else if (root.key > node.key){
            root.setLeft(insert(root.left, node));
        }else{
            root.setRight(insert(root.right, node));
        }
        return root;
    }
    public static Map.Entry<Node, Node> split(Node root, int key){
        //base case
        if(root==null) return new AbstractMap.SimpleEntry<>(null,null);
        //Logic
        if(root.key > key){
            Map.Entry<Node, Node> splitted = split(root.left, key);
            root.setLeft(splitted.getValue());
            return new AbstractMap.SimpleEntry<>(splitted.getKey(),root);
        }else{
            Map.Entry<Node, Node> splitted = split(root.right, key);
            root.setRight(splitted.getKey());
            return new AbstractMap.SimpleEntry<>(root, splitted.getValue());
        }
    }
    static class Node{
        int key;
        int priority, size;
      Node left, right;
        Node(int key){
            this.key = key;
            this.priority = getRandom();
            this.size = 1;
        }
        void setLeft(Node left){
            this.left = left;
            calcSize();
        }
        void setRight(Node right){
            this.right = right;
            calcSize();
        }
        void calcSize(){
            this.size=1;
            if(this.left != null) size+= this.left.size;
            if(this.right != null) size+= this.right.size;
        }
        int getRandom(){
            Random rand = new Random();
            return rand.nextInt(50000);
        }
    }
}

// 문제 : https://algospot.com/judge/problem/read/INSERTION

//입력
/*
2
5
0 1 1 2 3
4
0 1 2 3
 */

//출력
/*
5 1 4 3 2
4 3 2 1
 */
