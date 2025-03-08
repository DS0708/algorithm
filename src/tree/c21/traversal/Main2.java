package tree.c21.traversal;

import java.io.*;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++){
            //init
            int n = Integer.parseInt(br.readLine());
            List<Integer> preorder = new ArrayList<>();
            List<Integer> inorder = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) preorder.add(Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) inorder.add(Integer.parseInt(st.nextToken()));
            //logic
            printPostOrder(preorder, inorder);
            System.out.println();
        }
    }
    public static void printPostOrder(List<Integer> preorder, List<Integer> inorder){
        //base case
        if(preorder.isEmpty()) return;
        //Logic
        int treeSize = preorder.size();
        int root = preorder.get(0);
        int leftSubtreeSize = inorder.indexOf(root);
        int rightSubtreeSize = treeSize - leftSubtreeSize - 1;
        //left
        printPostOrder(slice(preorder,1,leftSubtreeSize), slice(inorder,0,leftSubtreeSize));
        //right
        printPostOrder(slice(preorder,1+leftSubtreeSize,rightSubtreeSize), slice(inorder, 1+leftSubtreeSize, rightSubtreeSize));
        //root
        System.out.print(root+" ");
    }
    public static List<Integer> slice(List<Integer> list, int from, int size){
        return new ArrayList<>(list.subList(from, from+size));
    }
}

//문제 : https://algospot.com/judge/problem/read/TRAVERSAL

//입력
/*
2
7
27 16 9 12 54 36 72
9 12 16 27 36 54 72
6
409 479 10 838 150 441
409 10 479 150 838 441
 */

//출력
/*
12 9 16 36 72 54 27
10 150 441 838 479 409
 */
