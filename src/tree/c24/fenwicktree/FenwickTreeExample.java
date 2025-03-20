package tree.c24.fenwicktree;

public class FenwickTreeExample {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        FenwickTree fwTree = new FenwickTree(arr);
        for(int i : fwTree.tree) System.out.print(i + " ");
        System.out.println();

        System.out.println("idx 0까지 부분합");
        System.out.println(fwTree.sum(0));

        System.out.println("idx 1까지 부분합");
        System.out.println(fwTree.sum(1));

        System.out.println("idx 2까지 부분합");
        System.out.println(fwTree.sum(2));

        System.out.println("idx 3까지 부분합");
        System.out.println(fwTree.sum(3));

        System.out.println("idx 1부터 3까지 부분합");
        System.out.println(fwTree.sum(3) - fwTree.sum(0));

        System.out.println("idx 0부터 2까지 부분합");
        System.out.println(fwTree.sum(2));

        System.out.println("idx 0번을 100으로 update");
        fwTree.add(0,100-arr[0]);
        System.out.println("update 후 2번인덱스까지의 부분합");
        System.out.println(fwTree.sum(2));
    }
    private static class FenwickTree{
        int[] tree;
        int size;
        FenwickTree(int[] arr){
            this.size = arr.length;
            tree = new int[size+1];
            for(int i=0; i<size; i++){
                add(i, arr[i]);
            }
        }
        private void add(int idx, int val){
            int treeIdx = idx+1;
            while(treeIdx <= size){
                tree[treeIdx] += val;
                treeIdx += (treeIdx & -treeIdx);
            }
        }
        private int sum(int idx){
            int ret = 0;
            int treeIdx = idx+1;
            while(treeIdx > 0){
                ret += tree[treeIdx];
                treeIdx -= (treeIdx & -treeIdx);
            }
            return ret;
        }
    }
}
