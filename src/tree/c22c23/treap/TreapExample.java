package tree.c22c23.treap;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;

public class TreapExample {
    public static void main(String[] args) {
        TreapNode<Integer> root = new TreapNode<>(5);
        root = insert(root, new TreapNode<>(1));
        root = insert(root, new TreapNode<>(2));

        TreapNode<Integer> root2 = new TreapNode<>(6);
        root2 = insert(root2, new TreapNode<>(9));
        root2 = insert(root2, new TreapNode<>(7));


        root = merge(root, root2);
        root.printInorder();

        //delete
        System.out.println("delete key 5");
        root = erase(root, 5);
        root.printInorder();

        //k번째 수 구하기
        System.out.println("2번째 노드 구하기");

        if(root.left!=null) System.out.println("왼쪽 서브 노드의 크기 : " + root.left.size);
        else System.out.println("왼쪽 서브 노드의 크기 : 0");
        if(root.right!=null) System.out.println("오른쪽 서브 노드의 크기 : " + root.right.size);
        else System.out.println("왼쪽 서브 노드의 크기 : 0");
        System.out.println(kth(root,3).key);

        //k보다 작은 노드의 개수 구하기
        System.out.println("7보다 작은 노드의 개수 구하기");
        System.out.println(countLessThan(root,7));
    }
    //k보다 작은 노드의 개수 구하기
    public static <T extends Comparable<T>> int countLessThan(TreapNode<T> root, T k){
        //base case
        if(root == null) return 0;
        //Logic
        if(root.key.compareTo(k) >= 0) return countLessThan(root.left, k);
        int leftSize = root.left != null ? root.left.size : 0;
        return leftSize + 1 + countLessThan(root.right, k);
    }
    //k번쨰 수 구하기
    public static <T> TreapNode<T> kth(TreapNode<T> root, int k){
        int leftSize = 0;
        if(root.left != null) leftSize = root.left.size;
        if(k <= leftSize) return kth(root.left, k);
        else if(k == leftSize+1) return root;
        else return kth(root.right, k-leftSize-1);
    }
    public static <T extends Comparable<T>> TreapNode<T> erase(TreapNode<T> root, T deleteKey) {
        //base case
        if(root == null) return root;
        //Logic
        if(root.key.compareTo(deleteKey)==0){
            TreapNode<T> ret = merge(root.left, root.right);
            return ret;
        }
        if(root.key.compareTo(deleteKey)>0){
            root.setLeft(erase(root.left, deleteKey));
        }else{
            root.setRight(erase(root.right, deleteKey));
        }
        return root;
    }
    //max(node1) < min(node2) 일때, 합치기
    public static <T extends Comparable<T>> TreapNode<T> merge(TreapNode<T> node1, TreapNode<T> node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        if(node1.priority < node2.priority) {
            node2.setLeft(merge(node1, node2.left));
            return node2;
        }else{
            node1.setRight(merge(node1.right, node2));
            return node1;
        }
    }
    public static <T extends Comparable<T>> TreapNode<T> insert(TreapNode<T> root, TreapNode<T> node){
        //base case
        if(root == null) return node;
        //node가 root를 대체해야 한다. 해당 서브트리를 반으로 잘라 각각 자손으로 한다.
        if(root.priority < node.priority){
            Map.Entry<TreapNode<T>, TreapNode<T>> splitted = split(root, node.key);
            node.setLeft(splitted.getKey());
            node.setRight(splitted.getValue());
            return node;
        }else if (root.key.compareTo(node.key) > 0){ //root.key > node.key
            root.setLeft(insert(root.left, node));
        }else{ // root.key >= node.key
            root.setRight(insert(root.right, node));
        }
        return root;
    }
    //node를 key 기준으로 왼쪽 서브트리와 오른쪽 서브트리로 나눈다.
    public static <T extends Comparable<T>> Map.Entry<TreapNode<T>, TreapNode<T>> split(TreapNode<T> root, T standardKey){
        //base case
        if(root==null) return new AbstractMap.SimpleEntry<>(null,null);
        //Logic
        if(root.key.compareTo(standardKey) > 0){
            Map.Entry<TreapNode<T>, TreapNode<T>> splitted = split(root.left, standardKey);
            root.setLeft(splitted.getValue());
            return new AbstractMap.SimpleEntry<>(splitted.getKey(), root);
        }else{
            Map.Entry<TreapNode<T>, TreapNode<T>> splitted = split(root.right, standardKey);
            root.setRight(splitted.getKey());
            return new AbstractMap.SimpleEntry<>(root, splitted.getValue());
        }
    }
    static class TreapNode<T>{
        T key;
        int priority, size;
        TreapNode<T> left, right;

        TreapNode(T key){
            this.key = key;
            this.priority = getRand();
            calcSize();
        }
        void setLeft(TreapNode<T> left){
            this.left = left;
            calcSize();
        }
        void setRight(TreapNode<T> right){
            this.right = right;
            calcSize();
        }
        void calcSize(){
            size = 1;
            if(left != null) size += left.size;
            if(right != null) size += right.size;
        }
        int getRand(){
            Random random = new Random();
            return random.nextInt(100);
        }
        void printInorder(){
            if(this.left != null) this.left.printInorder();
            System.out.println("key : " + this.key + ", priority : " + this.priority);
            if(this.right != null) this.right.printInorder();
        }
    }
}
