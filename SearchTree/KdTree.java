import sun.awt.image.ImageWatched;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by rober on 7/5/2017.
 * The main purpose of KDTree is to support geometric search & comparison
 */
public class KdTree {
    private Node root;

    private enum Orientation {  /* Orientation element */
        LeftRight, AboveBelow;
        public Orientation next() {
            if (this.equals(Orientation.AboveBelow))
                return Orientation.LeftRight;
            return Orientation.LeftRight;
        }
    }

    public int size() {return root.count;}
    public boolean isEmpty() {return root == null;}

    public void insert(Point2D p) {
        root = insert(root, root.orientation, p);
    }

    private Node insert(Node root, Orientation orientation, Point2D p) {
        if (root == null) {
            return new Node(p, orientation.next());
        }
        if (root.p.equals(p)) return root;
        else {
            boolean compare = compare(root.p, p, root.orientation);
            // need to somehow define a rect associated with each Node
            if (compare) {
                root.la = insert(root.la, root.orientation.next(), p);
            }
            else {
                root.rb = insert(root.rb, root.orientation.next(), p);
            }
        }
        return root;
    }

    public Iterable<Point2D> range(RectHV rect) {
        LinkedList<Point2D> list = new LinkedList<Point2D>();
        range(root, rect, list);
        return list;
    }

    private void range(Node root, RectHV rect, LinkedList<Point2D> list) {
        // need to somehow define a rect associated with each Node
        if (!rect.intersects(root.rect))
            return;
        if (rect.contains(root.p))
            list.add(root.p);
        range(root.la, rect, list);
        range(root.rb, rect, list);
    }

    public Point2D nearest(Point2D p) {
        return nearest(root, root.p, p);
    }

    private Point2D nearest(Node root, Point2D nearest, Point2D p) {
        if (root == null) return null;
        double distance = nearest.distanceTo(p);
        Point2D newer;

        boolean compare = compare(root.p, p, root.orientation);
        if (compare) {
            newer = nearest(root.la, nearest, p);
        }
        else {
            newer = nearest(root.rb, nearest, p);
        }
        if (newer == null) {
            return nearest;
        }
        double newer_distance = newer.distanceTo(p);
        if (newer_distance < distance) {
            nearest = newer;
        }
        return nearest;
    }

    /**
     * returns p greater than q in orientation
     */
    private boolean compare(Point2D p, Point2D q, Orientation orientation) {
        if (orientation == Orientation.AboveBelow) return Double.compare(p.y(), q.y()) > 0;
        return Double.compare(p.x(), q.x()) > 0;
    }

    public boolean contains(Point2D p) {
        return contains(root, p);
    }
    private boolean contains(Node root, Point2D p) {
        if (root == null) return false;
        if (root.p.equals(p)) return true;
        boolean compare = compare(root.p, p, root.orientation);
        if (compare) {
            return contains(root.la, p);
        }
        return contains(root.rb, p);
    }

    private static class Node{
        private Point2D p;
        private RectHV rect;
        private Node la; /* left or above node*/
        private Node rb; /* right or below node*/
        private int count;
        private Orientation orientation;
        public Node(Point2D p) {
            this.p = p;
            this.orientation = Orientation.LeftRight;
        }
        public Node(Point2D p, Orientation orientation) {
            this.p = p;
            this.orientation = orientation;
        }
    }

}
