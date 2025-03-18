package bruteforce.numofcases.permutation;

import java.util.*;

//서로 다른 n개의 원소 중에 순서 있이 r개를 뽑기
//nPr
public class Main {
    public static void main(String[] args) {
        int n = 5;
        int r = 3;
        Stack<Integer> push = new Stack<>();
        System.out.println(permu(push,5,3));
    }
    public static int permu(Stack<Integer> push, int n, int r){
        //base case
        if(r==0){
            for(int num : push) System.out.print(num + " ");
            System.out.println();
            return 1;
        }
        //logic
        int ret = 0;
        for(int i=0; i<n; i++){
            if(!push.contains(i)){
               push.push(i);
               ret += permu(push,n,r-1);
               push.pop();
            }
        }
        return ret;
    }
}
