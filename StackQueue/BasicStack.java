package tianming.StackQueue;

import tianming.StdIn;
import tianming.StdOut;

import java.util.LinkedList;

/**
 * Created by tianming on 6/12/17.
 */
public class BasicStack {
    private LinkedList<String> ls;
    public BasicStack() {
        ls = new LinkedList<String>();
    }
    public String pop() {
        String temp = ls.getFirst();
        ls.removeFirst();
        return temp;
    }
    public void push(String s) {
        ls.addFirst(s);
    }
    public static void main(String[] args) {
        BasicStack ss = new BasicStack();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(ss.pop());
            else ss.push(s);
        }

    }
}
