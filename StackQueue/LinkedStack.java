package tianming.StackQueue;

import tianming.StdIn;
import tianming.StdOut;

/**
 * Created by tianming on 6/12/17.
 */
public class LinkedStack {

    private Node first = new Node();

    private class Node{
        String item;
        Node next;
    }

    public void push(String s) {
        Node temp = new Node();
        temp.item = s;
        temp.next = first.next;
        first.next = temp;
    }

    public String pop() {
        Node temp = first.next;
        String result = temp.item;
        first.next = temp.next;
        temp = null;
        return result;
    }


    public static void main(String[] args) {
        LinkedStack ls = new LinkedStack();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(ls.pop());
            else ls.push(s);
        }
    }
}
