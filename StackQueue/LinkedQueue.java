package tianming.StackQueue;

import tianming.StdIn;
import tianming.StdOut;

/**
 * Created by tianming on 6/12/17.
 */
public class LinkedQueue {

    private Node first, last;

    private class Node {
        String data;
        Node next;
    }

    private boolean isEmpty() {
        return (first == null);
    }

    public void enqueue(String s) {
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

    public String dequeue() {
        String result = first.data;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedQueue ls = new LinkedQueue();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(ls.dequeue());
            else ls.enqueue(s);
        }
    }
}
