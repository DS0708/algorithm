package tree.c24.segmenttree;

public class MaxRMQExample {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 11, 6, 2, 3};
        MaxRMQ rmq = new MaxRMQ(arr);
        System.out.println(rmq.query(1,4));
        System.out.println(rmq.update(1,10));
    }
    private static class MaxRMQ{
        int n;
        int[] rangeMax;
        private MaxRMQ(int[] arr){
            n = arr.length;
            rangeMax = new int[4*n];
            init(arr,1,0,n-1);
        }
        private int init(int[] arr, int node, int left, int right){
            if(left==right) return rangeMax[node]=arr[left];
            int mid = (left+right)/2;
            int leftMax = init(arr, node*2, left, mid);
            int rightMax = init(arr, node*2+1, mid+1, right);
            return rangeMax[node] = Math.max(leftMax, rightMax);
        }
        private int query(int left, int right){
            return query(left, right, 1, 0, n-1);
        }
        private int query(int left, int right, int node, int nodeLeft, int nodeRight){
            if(left > nodeRight || right < nodeLeft) return Integer.MIN_VALUE;
            if(left <= nodeLeft && nodeRight <= right) return rangeMax[node];
            int nodeMid = (nodeLeft + nodeRight)/2;
            return Math.max(query(left,right,node*2,nodeLeft,nodeMid), query(left,right,node*2+1,nodeMid+1,nodeRight));
        }
        private int update(int idx, int newVal){
            return update(idx, newVal, 1, 0, n-1);
        }
        private int update(int idx, int newVal, int node, int nodeLeft, int nodeRight){
            if(idx < nodeLeft || nodeRight < idx) return rangeMax[node];
            if(nodeLeft==nodeRight) return rangeMax[node]=newVal;
            int mid = (nodeLeft + nodeRight)/2;
            return rangeMax[node] = Math.max(update(idx,newVal,node*2,nodeLeft,mid), update(idx,newVal,node*2+1,mid+1, nodeRight));
        }
    }
}
