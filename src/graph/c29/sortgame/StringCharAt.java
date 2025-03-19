package graph.c29.sortgame;

import java.util.ArrayList;
import java.util.List;

public class StringCharAt {
    public static void main(String[] args) {
        String str = "123456";
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<str.length(); i++) list.add((int)(str.charAt(i)-'0'));
        for(int i : list) System.out.print(i + " ");
    }
}
