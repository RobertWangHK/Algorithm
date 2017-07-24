package tianming.StackQueue;

import java.util.InputMismatchException;
import java.util.Iterator;

/**
 * Created by rober on 6/13/2017.
 */
public class IterableStack<Item> implements Iterable<Item> {
    private Node first;
    private class Node {
        private Item data;
        Node next;
    }
    public Iterator<Item> iterator() {
        return new stackIterator();
    }
    private class stackIterator implements Iterator<Item> {
        private Node current;
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            Item value = current.data;
            current = current.next;
            return value;
        }
    }
    public void push(Item s) {
        Node oldFirst = first;
        first = new Node();
        first.data = s;
        first.next = oldFirst;
    }
    public Item pop() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("not enough items");
        Item value = first.data;
        first = first.next;
        return value;
    }
    private boolean isEmpty() {
        return first == null;
    }
}
