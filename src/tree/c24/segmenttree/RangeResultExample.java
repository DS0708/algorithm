package tree.c24.segmenttree;

//정렬된 배열이 주어졌을떄, 주어진 구간의 최대 출현 빈도 계산
public class RangeResultExample {
    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,2,2,2,2,3,3,3};
        RangeResult rangeResult = new RangeResult(arr);
        // 구간 [3,8]에서 2의 빈도수 6을 찾음
        System.out.println(rangeResult.query(3, 8).mostFrequent);  // 출력: 6
    }
    private static class RangeResult{
        int size;
        int mostFrequent;
        int leftNum, leftFreq;
        int rightNum, rightFreq;
        RangeResult[] tree;
        private RangeResult(){
            mostFrequent = leftFreq = rightFreq = 0;
        }
        private RangeResult(int[] arr) {
            size = arr.length;
            tree = new RangeResult[size*4];
            init(arr, 1, 0, size-1);
        }
        private RangeResult init(int[] arr, int node, int left, int right){
            RangeResult ret = new RangeResult();
            if(left==right) {
                ret.mostFrequent = ret.leftFreq = ret.rightFreq = 1;
                ret.leftNum = ret.rightNum = arr[left];
                ret.size = 1;
                return tree[node] = ret;
            }
            int mid = (left+right)/2;
            RangeResult leftRangeResult = init(arr, node*2, left, mid);
            RangeResult rightRangeResult = init(arr, node*2 + 1, mid+1, right);

            return tree[node] = merge(leftRangeResult, rightRangeResult);
        }
        private RangeResult merge(RangeResult a, RangeResult b) {
            RangeResult ret = new RangeResult();
            //left, right 의 숫자와 각 freq 계산
            ret.size = a.size + b.size;
            ret.leftNum = a.leftNum;
            ret.leftFreq = a.leftFreq;
            if(a.size==a.leftFreq && a.rightNum==b.leftNum) ret.leftFreq += b.leftFreq;
            ret.rightNum = b.rightNum;
            ret.rightFreq = b.rightFreq;
            if(b.size==b.rightFreq && b.leftNum==a.rightNum) ret.rightFreq += a.rightFreq;
            //기본적으로 둘 중 더 큰 것이 mostFreq
            ret.mostFrequent = Math.max(a.mostFrequent, b.mostFrequent);
            //숫자가 이어질 때 예외처리
            if(a.rightNum==b.leftNum) ret.mostFrequent = Math.max(ret.mostFrequent, a.rightFreq + b.leftFreq);

            return ret;
        }
        // 외부에서 호출할 query 메서드
        public RangeResult query(int left, int right) {
            return query(left, right,1, 0, size-1)
        }

        private RangeResult query(int left, int right, int node, int nodeLeft, int nodeRight) {
            // 구간이 겹치지 않는 경우
            if (right < nodeLeft || nodeRight < left) {
                return new RangeResult();
            }
            // 구간이 완전히 포함되는 경우
            if (left <= nodeLeft && nodeRight <= right) {
                return tree[node];
            }
            // 구간이 걸치는 경우
            int mid = (nodeLeft + nodeRight) / 2;
            RangeResult leftResult = query(node * 2, nodeLeft, mid, left, right);
            RangeResult rightResult = query(node * 2 + 1, mid + 1, nodeRight, left, right);

            return merge(leftResult, rightResult);
        }
    }
}
