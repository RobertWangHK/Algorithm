package DataCompression;

import Graph.MinPQ;
import jdk.nashorn.internal.objects.NativeUint8Array;

/**
 * Created by rober on 7/25/2017.
 */
public class Huffman {

    private static char R = 256;

    private static class Node implements Comparable<Node> {
        private char ch;
        private int freq;
        private final Node left, right;

        public Node(Node left, Node right, char ch, int freq) {
            this.left = left;
            this.right = right;
            this.ch = ch;
            this.freq = freq;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public static void compress() {
        String s= BinaryStdIn.readString();
        char[] input = s.toCharArray();

        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;

        Node root = buildTrie(freq);

        // build char coding map table
        String[] st = new String[R];
        buildCode(st, root, "");

        writeTrie(root);
        BinaryStdOut.write(input.length);

        // start to compress input array
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            BinaryStdOut.write(code);
        }
        BinaryStdOut.close();
    }

    private static void buildCode(String[] st, Node curr, String code) {
        if (curr.isLeaf()) {
            st[curr.ch] = code;
        }
        else {
            buildCode(st, curr.left, code + '0');
            buildCode(st, curr.right, code + '1');
        }
    }

    public static void expand() {
        /* These two data are part of the huffman algorithm */
        Node root = readTrie();
        int N = BinaryStdIn.readInt();

        /* After get the trie & num of chars, it is pretty easy
        * to decode based on the trie structure -> traverse until
        * leaf */
        for (int i = 0; i < N; i++) {
            Node x = root;
            while(!x.isLeaf()) {
                boolean b = BinaryStdIn.readBoolean();
                if (b == false) {
                    x = x.left;
                }
                else {
                    x = x.right;
                }
            }
            BinaryStdOut.write(x.ch);
        }
        BinaryStdOut.close();

    }

    private static Node buildTrie(int[] freq) {
        MinPQ<Node> pq = new MinPQ<Node>();
        for (char i = 0; i < R; i++) {
            if (freq[i] > 0) pq.insert(new Node(null, null, i, freq[i]));
        }
        /**
         * Combine 2 nodes with least freq into one bigger one until one left
         */
        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            pq.insert(new Node(left, right, '\0', left.freq + right.freq));
        }
        return pq.delMin();
    }

    /**
     * Read in the trie for this data compression system
     */
    public static Node readTrie(){
        boolean b = BinaryStdIn.readBoolean();
        if (b == true) {
            return new Node(null, null, BinaryStdIn.readChar(), 0);
        }
        Node left = readTrie();
        Node right = readTrie();
        return new Node(left, right, '\0', 0);
    }

    public static void writeTrie(Node x) {

        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch);
            return;
        }

        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }
}
