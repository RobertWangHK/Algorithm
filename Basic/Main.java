package tianming.Basic;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("main class");
        UnionFind wq = new UnionFind(10);
        wq.union(0,1);
        System.out.println(wq.connected(0,1));
        System.out.println(wq.connected(0,2));

    }
}
