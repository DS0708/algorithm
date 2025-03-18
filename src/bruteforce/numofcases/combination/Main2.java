package bruteforce.numofcases.combination;

import java.util.Stack;

public class Main2 {
    public static void main(String[] args) {
        int n=5;
        int r=3;
        Stack<Integer> stack = new Stack<>();
        System.out.println(combi(stack,n,r));
    }
    public static int combi(Stack<Integer> stack, int n, int r){
        if(r==0){
            for(int s : stack) System.out.print(s+" ");
            System.out.println();
            return 1;
        }else{
            int ret = 0;
            int smallest = stack.isEmpty() ? 1 : stack.peek() + 1;
            for(int next=smallest; next<=n; next++){
                stack.push(next);
                ret += combi(stack, n , r-1);
                stack.pop();
            }
            return ret;
        }
    }
}