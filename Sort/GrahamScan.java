package tianming.Sort;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by tianming on 6/19/17.
 */
public class GrahamScan implements Iterable<Point2D>{

    private Stack<Point2D> hull = new Stack<Point2D>();

    public GrahamScan(Point2D[] list){

        int length = list.length;
        Point2D[] newList = new Point2D[length];

        for (int i = 0; i < length; i++) {
            if (list[i] == null)
                throw new IllegalArgumentException("points[" + i + "] is null");
            newList[i] = list[i];
        }
        //make use of natural compareto method of Point2D object, s.t. 1st element is right corner
        Arrays.sort(newList);

        //sort by polar angle with respect to origin point at newList[0]
        Arrays.sort(newList, 1, length, newList[0].polarComparator());

        /**
         * 1. adds 1st element to list
         * 2. find 1st element not inline with origin k2, push k2-1 element in stack
         * 3. for further element k3, pop until poped element, current top and k3
         * counter-clockwise turns, push back top and k3.
         */
        hull.push(newList[0]);

        int k1 = 1;
        for (; k1 < length; k1++) {
            if (!newList[0].equals(newList[k1])) break;
        }
        if (k1 == length) return;

        int k2 = k1 + 1;
        for (; k2 < length; k2++) {
            if (Point2D.ccw(newList[0], newList[k1], newList[k2]) != 0) break;
        }
        hull.push(newList[k2-1]);

        for (int i = k2; i < length; i++) {
            Point2D currentTop = hull.pop();
            while(Point2D.ccw(hull.peek(), currentTop, newList[i]) <= 0)
                currentTop = hull.pop();
            hull.push(currentTop);
            hull.push(newList[k2]);
        }

    }

    public Iterator<Point2D> iterator() {return new GrahamIterator();}

    private class GrahamIterator implements Iterator<Point2D>{
        private Stack<Point2D> copyHull;
        public GrahamIterator() {
            for (Point2D temp: hull)
                copyHull.push(temp);
        }
        public Point2D next(){
            return copyHull.pop();
        }
        public boolean hasNext() {
            return !copyHull.isEmpty();
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point2D(x, y);
        }
        GrahamScan graham = new GrahamScan(points);
        for (Point2D p : graham)
            StdOut.println(p);
    }
}
