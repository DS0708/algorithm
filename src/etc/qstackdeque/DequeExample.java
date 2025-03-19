package etc.qstackdeque;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=1; i<=10; i++) deque.addLast(i);

        int size = deque.size();
        for(int i=0; i<size; i++) System.out.print(deque.removeFirst()+" ");
        System.out.println();

        for(int i=1; i<=10; i++) deque.addLast(i);
        for(int i=1; i<=10; i++) System.out.print(deque.removeLast()+" ");
        System.out.println();

        System.out.println("offer는 addLast와 같음");
        for(int i=1; i<=10; i++) deque.offer(i);
        for(int i=1; i<=10; i++) System.out.print(deque.removeFirst()+" ");
        System.out.println();
        for(int i=1; i<=10; i++) deque.addLast(i);
        for(int i=1; i<=10; i++) System.out.print(deque.removeFirst()+" ");
        System.out.println();

        System.out.println("push는 addFirst와 같음");
        for(int i=1; i<=10; i++) deque.push(i);
        for(int i=1; i<=10; i++) System.out.print(deque.removeFirst()+" ");
        System.out.println();
        for(int i=1; i<=10; i++) deque.addFirst(i);
        for(int i=1; i<=10; i++) System.out.print(deque.removeFirst()+" ");
    }
}
