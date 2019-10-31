package dsa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryTree implements Iterable<String> {
    public Node root;

    @Override
    public Iterator<String> iterator() {
        return new InOrderIterator();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterator<String> getPreOrderIterator() {
        return new PreOrderIterator();
    }

    class InOrderIterator implements Iterator<String> {
        List<String> keys = new ArrayList<>();
        Iterator<String> iterator;

        public InOrderIterator() {
            getKeysByInOrderTraversal(root, keys);
            iterator = keys.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public String next() {
            return iterator.next();
        }
    }

    class PreOrderIterator implements Iterator<String> {
        List<String> keys = new ArrayList<>();
        Iterator<String> iterator;

        public PreOrderIterator() {
            getKeysByPreOrderTraversal(root, keys);
            iterator = keys.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public String next() {
            return iterator.next();
        }
    }

    private void getKeysByPreOrderTraversal(Node node, List<String> keys) {
        if (node == null) return;
        keys.add(node.key);
        getKeysByInOrderTraversal(node.left, keys);
        getKeysByInOrderTraversal(node.right, keys);
    }

    private void getKeysByInOrderTraversal(Node node, List<String> keys) {
        if (node == null) return;
        getKeysByInOrderTraversal(node.left, keys);
        keys.add(node.key);
        getKeysByInOrderTraversal(node.right, keys);
    }

    static class Node {
        private final String key;
        private Node left, right;
        public Node(String key) {
            this.key = key;
        }

        public Node(String key, Node left, Node right) {
            this(key);
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");
        Node b = new Node("B", d, e);
        Node c = new Node("C", f, g);
        tree.root = new Node("A", b, c);

        //tree.preOrderPrint(); //A B D E C F G
        //tree.preOrderPrint(); //D B E A F C G
        System.out.println("---- iterator: ");
        for (String key: tree) {        //D B E A F C G
            System.out.print(key + " ");
        }

        StringBuffer buf = new StringBuffer();
        Iterator<String> i = tree.getPreOrderIterator();
        while (i.hasNext())                   //A B D E C F G
        {
            String key = i.next();
            buf.append(key);
        }
        System.out.print(buf);
    }

    private void inOrderPrint() {
        inOrderPrint(root);
    }

    private void inOrderPrint(Node node) {
        if (node==null) return;
        inOrderPrint(node.left);
        System.out.print(node.key + " ");
        inOrderPrint(node.right);
    }

    private void preOrderPrint() {
        preOrderPrint(root);
    }

    private void preOrderPrint(Node node) {
        if (node==null) return;
        System.out.print(node.key + " ");
        preOrderPrint(node.left);
        preOrderPrint(node.right);
    }
}
