package etc.useful;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionReverse {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<=10; i++) list.add(i);
        Collections.reverse(list.subList(0,3));
        for(int i : list) System.out.print(i + " ");
    }
}
