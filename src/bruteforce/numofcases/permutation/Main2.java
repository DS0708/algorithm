package bruteforce.numofcases.permutation;

import java.util.Stack;

public class Main2 {
    public static void main(String[] args) {
        int n = 5;
        int r = 3;
        Stack<Integer> stack = new Stack<>();
        System.out.println(permu(stack,5,3));
    }
    public static int permu(Stack<Integer> stack,int n, int r) {
        if(r==0){
            for(int s : stack) System.out.print(s+" ");
            System.out.println();
            return 1;
        }else{
            int ret = 0;
            for(int i=1; i<=n; i++){
                if(!stack.contains(i)){
                    stack.push(i);
                    ret += permu(stack,n,r-1);
                    stack.pop();
                }
            }
            return ret;
        }
    }
}
