package etc.useful;

import java.util.*;

public class ListExample {
    public static void main(String[] args) {
        List<Integer>[][] graph = new ArrayList[5][5];

        //이렇게 선언하면 하나의 참조값이 동일한 row에 할당된다 !!
        Arrays.stream(graph).forEach(r->Arrays.fill(r, new ArrayList<>()));

        graph[0][0].add(1);
        graph[0][2].add(2);
        graph[0][3].add(2);

        for(int i : graph[0][0]) System.out.println(i);
    }
}
