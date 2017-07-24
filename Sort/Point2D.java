package tianming.Sort;

import java.awt.*;
import java.util.Comparator;

/**
 * Created by tianming on 6/19/17.
 */
public class Point2D implements Comparable<Point2D>{

    private double x;
    private double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double theta() {
        return Math.atan2(this.y, this.x);
    }

    private double angleTo(Point2D r) {
        double x_diff = this.x - r.x;
        double y_diff = this.y - r.y;
        return Math.atan2(y_diff, x_diff);
    }

    public double distanceTo (Point2D r) {
        double x_diff = this.x - r.x;
        double y_diff = this.y - r.y;
        return Math.sqrt(x_diff*x_diff + y_diff*y_diff);
    }

    //this is the natural comparing method implemented by comparable interface
    public int compareTo(Point2D r) {
        if (getY() < r.getY()) return -1;
        if (getY() > r.getY()) return +1;
        if (getX() < r.getX()) return -1;
        if (getX() > r.getX()) return +1;
        return 0;
    }

    // helper function for checking counteclock to Point2D r
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double value =  (b.getX() - a.getX()) * (c.getY() - a.getY()) -
                (b.getY() - a.getY()) * (c.getX() - a.getX());
        if (value > 0) return 1;
        else if (value < 0) return -1;
        else return 0;
    }

    /**
     * below section comes all comparator helper functions
     * polarComparator compares according to polar respect to origin point
     * distanceComparator compares according to distance to origin point
     */

    public Comparator<Point2D> polarComparator() {
        return new PolarOrder();
    }

    //compare 2 points relative to this point by polar angle, 0 to 2pi
    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D a, Point2D b) {
            Point2D temp = Point2D.this;
            double x1_diff = a.getX() - x;
            double x2_diff = b.getX() - x;
            double y1_diff = a.getY() - y;
            double y2_diff = b.getX() - y;
            if (y1_diff == 0 && y2_diff == 0) {
                if (x1_diff >= 0 && x2_diff < 0) return -1;
                else if (x2_diff >= 0 && x1_diff < 0) return 1;
                else return 0;
            }
            else if (y1_diff >= 0 && y2_diff < 0) return -1;
            else if (y2_diff >= 0 && y1_diff < 0) return 1;
            else return -ccw(Point2D.this, a, b);
        }
    }

    public Comparator<Point2D> distanceComparator() {
        return new DistanceOrder();
    }

    private class DistanceOrder implements Comparator<Point2D> {
        public int compare(Point2D a, Point2D b) {
            double a_distance = Point2D.this.distanceTo(a);
            double b_distance = Point2D.this.distanceTo(b);
            if (a_distance > b_distance) return 1;
            else if (a_distance < b_distance) return -1;
            else return 0;
        }
    }

    /**
     * override several helper function, e.g. toString, hashCode, equals
     */

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) + Double.hashCode(y);
    }

    @Override
    public boolean equals(Object r) {
        if (!(r instanceof Point2D)) return false;
        return ((Point2D)r).getX() == x && ((Point2D) r).getY() == y;
    }

    public static void main(String[] args) {
        Point2D a = new Point2D(1,0);
        Point2D b = new Point2D(0.5,0.5);
        Point2D c = new Point2D(0.5,1);
        System.out.println(Point2D.ccw(a,b,c));
        System.out.println(a.toString());
        System.out.println(a);
        System.out.println(a.hashCode());
        System.out.println(a.equals(b));
    }
}
