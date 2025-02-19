package c6.numofcases;

import java.util.Stack;

//서로 다른 N개의 원소 중에서 R개를 순서 없이 골라내기
//nCr
public class Combination {
    public static void main(String[] args) {
        int n = 5;
        int r = 3;
        Stack<Integer> pick = new Stack<>();
        System.out.println(combi(pick,n,r));
    }
    public static int combi(Stack<Integer> pick, int n, int toPick) {
        //base case : 고를 원소가 없으면 종료
        if(toPick==0){
            for(int p : pick) System.out.print(p+" ");
            System.out.println();
            return 1;
        }
        //순서를 강제하면서, 안고른 가장 작은 번호 찾기
        int smallest = pick.size()==0 ? 0 : pick.peek()+1;
        int ret = 0;
        for(int next = smallest; next <n; next++){
            pick.push(next);
            ret += combi(pick,n,toPick-1);
            pick.pop();
        }
        return ret;
    }
}
