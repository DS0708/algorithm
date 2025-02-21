package c6.numofcases.permutation;

import java.util.Stack;

public class Main3 {
    public static void main(String[] args) {
        int n = 5;
        int r = 3;
        boolean[] visited = new boolean[n+1];
        Stack<Integer> stack = new Stack<>();
        System.out.println(permu(stack,visited,n,r));
    }
    public static int permu(Stack<Integer> stack,boolean[] visited, int n, int r){
        //base case
        if(r==0){
            for(int s : stack) System.out.print(s+" ");
            System.out.println();
            return 1;
        }
        //logic
        int ret = 0;
        for(int i=1; i<=n; i++){
            if(!visited[i]){
                stack.push(i);
                visited[i] = true;
                ret += permu(stack, visited, n, r-1);
                stack.pop();
                visited[i] = false;
            }
        }
        return ret;
    }
}
