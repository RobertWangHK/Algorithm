package tianming.StackQueue;

import tianming.StdIn;
import tianming.StdOut;

/**
 * Created by tianming on 6/12/17.
 */
public class GenericQueue<T> {

    private Node first, last;

    private class Node {
        T data;
        Node next;
    }

    private boolean isEmpty() {
        return (first == null);
    }

    public void enqueue(T s) {
        Node oldLast = last;
        last = new Node();
        last.data = s;
        if (!isEmpty()) {
            oldLast.next = last;
        }
        else {
            first = last;
        }
    }

    public T dequeue() {
        T result = first.data;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return result;
    }

    public static void main(String[] args) {
        GenericQueue<Integer> ls = new GenericQueue();
        while(!StdIn.isEmpty()) {
            int s = StdIn.readInt();
            if (s == 0) StdOut.print(ls.dequeue());
            else ls.enqueue(s);
        }
    }
}
