package etc.qstackdeque.brackets2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=0; c<cases; c++) {
            String str = br.readLine();
            if(solve(str)) System.out.println("YES\n");
            else System.out.println("NO\n");
        }
    }
    private static boolean solve(String str) {
        Stack<Character> stack = new Stack<>();
        String opening = "([{";
        String closing = ")]}";
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if(opening.contains(String.valueOf(c))) {
                stack.push(c);
            }else{
                if(stack.isEmpty()) return false;
                char stackChar = stack.pop();
                if(opening.indexOf(stackChar) != closing.indexOf(c)) return false;
            }
        }
        return stack.isEmpty();
    }
}

//문제 : https://algospot.com/judge/problem/read/BRACKETS2

//입력
/*
3
()()
({[}])
({}[(){}])
 */

//출력
/*
YES
NO
YES
 */
