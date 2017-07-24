package tianming.Basic;

/**
 * Created by tianming on 6/12/17.
 */
public class UnionFind {
    private int[] parents; //parent of i
    private int[] sizes; //number of sites in tree rooted at i
    private int count; //count number of components
    private int total; //total number of elements

    public UnionFind(int n) {
        parents = new int[n];
        sizes = new int[n];
        count = n;
        total = n;
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
    }

    public int count(){return count;}
    public int find(int index) {
        valid(index);
        int root = index;
        while(root != parents[root])
            root = parents[root];
        //change items along the path parents -> root
        while(index != root) {
            int temp_parent = parents[index];
            parents[index] = root;
            index = temp_parent;
        }
        return root;
    }
    public boolean connected(int index1, int index2) {
        return find(index1) == find(index2);
    }
    public void union(int index1, int index2) {
        if (connected(index1, index2)) {
            return;
        }
        int root1 = find(index1);
        int root2 = find(index2);
        if (sizes[root1] >= sizes[root2]) {
            parents[root2] = root1;
            sizes[root1] += root2;
        }
        else {
            parents[root1] = root2;
            sizes[root2] += sizes[root1];
        }
        count -= 1;
    }
    private void valid(int index){
        if (index >=  total|| index < 0)
            throw new IndexOutOfBoundsException("out of bound");
    }
}
