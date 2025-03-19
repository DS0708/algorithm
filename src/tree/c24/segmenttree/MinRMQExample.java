package tree.c24.segmenttree;

public class MinRMQExample {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 7, 9};
        MinRMQ rmq = new MinRMQ(arr);
        System.out.println(rmq.query(4,5));
        System.out.println(rmq.update(0,10));
        System.out.println(rmq.query(0,4));
    }
    private static class MinRMQ{
        //배열의 길이
        int n;
        //각 구간의 최소치
        int[] rangeMin;
        private MinRMQ(int[] arr){
            n = arr.length;
            rangeMin = new int[n*4];
            init(arr,0,n-1,1);
        }
        private int init(int[] arr, int left, int right, int node){
            if(left==right){
                return rangeMin[node] = arr[left];
            }
            int mid = (left+right)/2;
            int leftMin = init(arr, left, mid, node*2);
            int rightMin = init(arr, mid+1, right, node*2+1);
            return rangeMin[node] = Math.min(leftMin, rightMin);
        }
        // query()를 외부에서 호출하기 위한 인터페이스
        private int query(int left, int right){
            return query(left, right, 1, 0, n-1);
        }
        //node가 표현하는 범위 arr[nodeLeft ... nodeRight]가 주어질 떄,
        //이 범위와 arr[left .. right]의 교집합의 최소치를 구한다.
        private int query(int left, int right, int node, int nodeLeft, int nodeRight){
            //두 구간이 겹치지 않으면 아주 큰 값 반환
            if(right < nodeLeft || left > nodeRight) return Integer.MAX_VALUE;
            //node가 표현하는 범위가 arr[left .. right]에 완전히 포함되는 경우
            if(left <= nodeLeft && nodeRight <= right) return rangeMin[node];
            //양쪽 구간을 나눠서 푼 뒤 결과를 합침
            int mid = (nodeLeft + nodeRight) / 2;
            return Math.min(query(left,right,node*2,nodeLeft,mid), query(left,right,node*2+1,mid+1,nodeRight));
        }
        //update 외부 인터페이스
        private int update(int idx, int newVal){
            return update(idx, newVal, 1, 0, n-1);
        }
        //arr[idx]=newVal로 바뀌었을 때 node를 루트로하는
        //구간 트리를 갱신하고 노드가 표현하는 구간의 최소치를 반환
        private int update(int idx, int newVal, int node, int nodeLeft, int nodeRight){
            //idx가 해당 노드 구간과 상관없는 경우엔 무시
            if(idx<nodeLeft || nodeRight<idx) return rangeMin[node];
            //트리의 리프까지 내려온 경우
            if(nodeLeft==nodeRight) return rangeMin[node]=newVal;
            int mid = (nodeLeft + nodeRight) / 2;
            return rangeMin[node] = Math.min(update(idx,newVal,node*2,nodeLeft,mid), update(idx, newVal, node*2+1, mid+1, nodeRight));
        }
    }
}
