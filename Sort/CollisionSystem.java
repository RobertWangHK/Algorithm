import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by rober on 6/29/2017.
 */
public class CollisionSystem {

    private final static double HZ = 0.5;
    private MinPQ<Event> pq; // priority queue of Events
    private double time = 0.0;
    private Particle[] particles;

    public CollisionSystem(Particle[] particles) {
        this.particles = particles.clone();
    }

    private void predict(Particle a, double limit) {
        if (a == null) return;

        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            if ((time + dt) < limit) {
                pq.insert(new Event(time + dt, a, particles[i]));
            }
        }

        double time_horizontal = a.timeToHitHorizontalWall();
        double time_vertical = a.timeToHitVerticleWall();

        if ((time_horizontal + time) < limit)
            pq.insert(new Event(time_horizontal + time, null, a));
        if ((time_vertical + time) < limit)
            pq.insert(new Event(time_vertical + time, a, null));
    }

    private void redraw(double limit) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show();
        StdDraw.pause(20); //pause for 20s
        if (time < limit) {
            pq.insert(new Event(time + 1.0 / HZ, null, null));
        }
    }

    /**
     * @param limit the amount of time that system simulates
     */
    public void simulate(double limit) {
        pq = new MinPQ<Event>();
        for (int i = 0; i < particles.length; i++) {
            predict(particles[i], limit);
        }
        pq.insert(new Event(0, null, null));

        //main loop for simulation
        while(!pq.isEmpty()) {
            Event current_event = pq.delMin();
            if (!current_event.isValid()) continue;
            Particle a = current_event.a;
            Particle b = current_event.b;

            for (int i = 0; i < particles.length; i++) {
                particles[i].move(current_event.time - time);
            }
            time = current_event.time;

            //process this event
            if (a != null && b != null) {
                a.bounceOff(b);
            }
            //hit vertical wall
            else if (a != null && b == null) {
                a.bounceOffVerticalWall();
            }
            //hit horizontal wall
            else if (a == null && b != null) {
                b.timeToHitHorizontalWall();
            }
            else redraw(limit);
            predict(a, limit);
            predict(b, limit);
        }
    }


    /**
     * Event denotes time at which a collides with b. if either one
     * of them is null -> collide with wall, if bothe null, ???
     */
    private static class Event implements Comparable<Event> {
        private final double time;
        private final Particle a;
        private final Particle b;
        private final int countA, countB;

        public Event(double time, Particle a, Particle b) {
            this.time = time;
            this.a = a;
            this.b = b;
            if (a != null) countA = a.getCount();
            else           countA = -1;
            if (b != null) countB = b.getCount();
            else           countB = -1;
        }

        public int compareTo(Event that) {
            return Double.compare(time, that.time);
        }

        //when collision occurs, check if still valid
        public boolean isValid() {
            //invalid if collision happens since creation of this event
            if (a != null && a.getCount() != countA) {
                return false;
            }
            if (b != null && b.getCount() != countB) {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {

        StdDraw.setCanvasSize(800, 800);

        // remove the border
        // StdDraw.setXscale(1.0/22.0, 21.0/22.0);
        // StdDraw.setYscale(1.0/22.0, 21.0/22.0);

        // enable double buffering
        StdDraw.enableDoubleBuffering();

        // the array of particles
        Particle[] particles;
        String readLine = "";
        String[] texts = null;
        int i = 0;

        try {
            File f = new File("D:\\CMU Courses\\Algorithm I\\Code\\src\\data.txt");
            BufferedReader buffer = new BufferedReader(new FileReader(f));

            int n =  Integer.parseInt(buffer.readLine());
            particles = new Particle[n];
            while((readLine = buffer.readLine()) != null) {
                texts = readLine.split(" ");
                double rx     = Double.parseDouble(texts[1]);
                double ry     = Double.parseDouble(texts[2]);
                double vx     = Double.parseDouble(texts[3]) + 5;
                double vy     = Double.parseDouble(texts[4]) + 5;
                double radius = Double.parseDouble(texts[5]);
                double mass   = Double.parseDouble(texts[6]);
                int r         = Integer.parseInt(texts[7]);
                int g         = Integer.parseInt(texts[8]);
                int b         = Integer.parseInt(texts[9]);
                Color color   = new Color(r, g, b);
                particles[i] = new Particle(radius, mass, color, 0, vy, vx, ry, rx);
                i++;
            }
            //create simulation system and simulate
            CollisionSystem system = new CollisionSystem(particles);
            system.simulate(10000);

        } catch (Exception e){
            System.out.print(e.getMessage());
        }
    }

}
