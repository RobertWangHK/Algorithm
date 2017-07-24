package tianming.StackQueue;

import tianming.StdOut;

import java.util.Iterator;

/**
 * Created by rober on 6/13/2017.
 */
public class IterableQueue<T> implements Iterable<T>{
    private Node first, last;
    public Iterator<T> iterator() {
        return new QueueIterator();
    }
    private class QueueIterator implements Iterator<T>{
        private Node point = first;
        public boolean hasNext() {
            return point != null;
        }
        public T next(){
            T result = point.item;
            point = point.next;
            return result;
        }
    }
    private class Node{
        T item;
        Node next;
    }
    private boolean isEmpty() {
        return first == null;
    }
    public void enqueue(T s) {
        Node oldLast = last;
        last = new Node();
        last.item = s;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
    }
    public T dequeue() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("you do not have enough items");
        T result = first.item;
        first = first.next;
        if (isEmpty())
            last = first;
        return result;
    }

    public static void main(String[] args) {
        IterableQueue<Integer> iq = new IterableQueue<Integer>();
        //while(!StdIn.isEmpty()) {
        //    int s = StdIn.readInt();
        //    if (s == 0) StdOut.print(iq.dequeue());
        //    else iq.enqueue(s);
        //}
        //iq.dequeue();
        iq.enqueue(4);
        iq.enqueue(4);
        Iterator it = iq.iterator();
        while(it.hasNext())
            StdOut.print(it.next());
    }
}
