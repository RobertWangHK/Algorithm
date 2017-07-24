import java.awt.*;

/**
 * Created by rober on 6/29/2017.
 */
public class Particle {
    private static final double INFINITY = Double.POSITIVE_INFINITY;
    private double rx, ry; //position
    private double vx, vy; //velocity
    private int count;
    private final double radius;
    private final double mass;
    private final Color color;

    public Particle(double radius, double mass, Color color, int count,
                    double vy, double vx, double ry, double rx) {
        this.radius = radius;
        this.mass = mass;
        this.color = color;
        this.count = count;
        this.vy = vy;
        this.vx = vx;
        this.ry = ry;
        this.rx = rx;
    }

    public Particle() {
        rx     = StdRandom.uniform(0.0, 1.0);
        ry     = StdRandom.uniform(0.0, 1.0);
        vx     = StdRandom.uniform(-0.005, 0.005);
        vy     = StdRandom.uniform(-0.005, 0.005);
        radius = 0.01;
        mass   = 0.5;
        color  = Color.BLACK;
    }

    public void move(double dt) {
        rx = rx + vx * dt;
        ry = ry + vy * dt;
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }

    public double timeToHit(Particle that) {
        if (this == that) return INFINITY;
        double dx  = that.rx - this.rx;
        double dy  = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;
        if (dvdr > 0) return INFINITY;
        double dvdv = dvx*dvx + dvy*dvy;
        double drdr = dx*dx + dy*dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
        // if (drdr < sigma*sigma) StdOut.println("overlapping particles");
        if (d < 0) return INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitVerticleWall() {
        if (vx > 0) return (1.0 - radius - rx) / vx;
        else if (vx < 0) return (radius - rx) / vx;
        else return INFINITY;
    }

    public double timeToHitHorizontalWall() {
        if      (vy > 0) return (1.0 - ry - radius) / vy;
        else if (vy < 0) return (radius - ry) / vy;
        else             return INFINITY;
    }

    //how to prevent multiple updates on count attributes?
    public void bounceOff(Particle that) {
        double dx  = that.rx - this.rx;
        double dy  = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;             // dv dot dr
        double dist = this.radius + that.radius;   // distance between particle centers at collison

        // magnitude of normal force
        double magnitude = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);

        // normal force, and in x and y directions
        double fx = magnitude * dx / dist;
        double fy = magnitude * dy / dist;

        // update velocities according to normal force
        this.vx += fx / this.mass;
        this.vy += fy / this.mass;
        that.vx -= fx / that.mass;
        that.vy -= fy / that.mass;

        // update collision counts
        this.count++;
        that.count++;
    }

    public void bounceOffVerticalWall() {
        vx = - vx;
        count++;
    }

    public void bounceOffHorizontalWall() {
        vy = - vy;
        count++;
    }

    public double energy() {
        return 0.5 * mass * (vx * vx + vy * vy);
    }

    public int getCount() {
        return count;
    }
}
