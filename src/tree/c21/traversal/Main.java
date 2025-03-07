package tree.c21.traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++) {
            //init
            int N = Integer.parseInt(br.readLine());
            List<Integer> preorder = new ArrayList<>();
            List<Integer> inorder = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) preorder.add(Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) inorder.add(Integer.parseInt(st.nextToken()));
            //Logic
            printPostOrder(preorder, inorder);
            System.out.println();
        }
    }
    //트리의 전위 순회 순서와 중위 순회 순서가 주어질 떄, 후위 순회 순서를 출력
    public static void printPostOrder(List<Integer> preorder, List<Integer> inorder) {
        //base case : 텅 빈 트리이면 종료
        if(preorder.isEmpty()) return;
        //Logic
        int treeSize = preorder.size();
        int root = preorder.get(0); //전위 순회의 첫번째 노드는 항상 루트
        int leftSubTreeSize = inorder.indexOf(root); //왼쪽 서브트리의 크기는 중위 순회 리스트를 통해 구할 수 있음
        int rightSubTreeSize = treeSize - leftSubTreeSize - 1; //오른쪽 서브트리의 크기 = 전체 트리 사이즈 - 왼쪽서브트리사이즈 - 1(루트)
        //왼쪽 서브트리 출력
        printPostOrder(slice(preorder,1,leftSubTreeSize), slice(inorder,0,leftSubTreeSize));
        //오른쪽 서브트리 출력
        printPostOrder(slice(preorder,leftSubTreeSize+1,rightSubTreeSize), slice(inorder,leftSubTreeSize+1,rightSubTreeSize));
        //루트 출력
        System.out.print(root+" ");
    }
    //tree 리스트가 입력으로 들어오면, start부터 시작해 size만큼의 리스트 반환
    public static List<Integer> slice(List<Integer> tree, int start, int size){
        return new ArrayList<>(tree.subList(start, start+size));
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