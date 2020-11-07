import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryTree<Key> implements Iterable<Key> {
    public Node<Key> root;

    @Override
    public Iterator<Key> iterator() {
        return new InOrderIterator();
    }

    public Iterator<Key> getPreOrderIterator() {
        return new PreOrderIterator();
    }

    class InOrderIterator implements Iterator<Key> {
        List<Key> keys = new ArrayList<>();
        Iterator<Key> iterator;

        public InOrderIterator() {
            getKeysByInOrderTraversal(root, keys);
            iterator = keys.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Key next() {
            return iterator.next();
        }
    }

    class PreOrderIterator implements Iterator<Key> {
        List<Key> keys = new ArrayList<>();
        Iterator<Key> iterator;

        public PreOrderIterator() {
            getKeysByPreOrderTraversal(root, keys);
            iterator = keys.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Key next() {
            return iterator.next();
        }

        private void getKeysByPreOrderTraversal(Node<Key> node, List<Key> keys) {
            if (node == null) return;
            keys.add(node.key);
            getKeysByInOrderTraversal(node.left, keys);
            getKeysByInOrderTraversal(node.right, keys);
        }
    }

    private void getKeysByInOrderTraversal(Node<Key> node, List<Key> keys) {
        if (node == null) return;
        getKeysByInOrderTraversal(node.left, keys);
        keys.add(node.key);
        getKeysByInOrderTraversal(node.right, keys);
    }

    static class Node<Key> {
        private final Key key;
        private Node<Key> left, right;
        public Node(Key key) {
            this.key = key;
        }

        public Node(Key key, Node<Key> left, Node<Key> right) {
            this(key);
            this.left = left;
            this.right = right;
        }

        public String toString() {
            return key.toString();
        }
    }

    private void inOrderPrint() {
        inOrderPrint(root);
    }

    private void inOrderPrint(Node<Key> node) {
        if (node==null) return;
        inOrderPrint(node.left);
        System.out.print(node.key + " ");
        inOrderPrint(node.right);
    }

    private void preOrderPrint() {
        preOrderPrint(root);
    }

    private void preOrderPrint(Node<Key> node) {
        if (node==null) return;
        System.out.print(node.key + " ");
        preOrderPrint(node.left);
        preOrderPrint(node.right);
    }

    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree<>();
        Node<String> d = new Node<>("D");
        Node<String> e = new Node<>("E");
        Node<String> f = new Node<>("F");
        Node<String> g = new Node<>("G");
        Node<String> b = new Node<>("B", d, e);
        Node<String> c = new Node<>("C", f, g);
        tree.root = new Node<>("A", b, c);

        System.out.println("Size: " + tree.size());

        System.out.println("Height: " + tree.height());

        System.out.println("Find d: "+ tree.find("D"));
        System.out.println("Find z: "+ tree.find("Z"));

        //tree.preOrderPrint(); //A B D E C F G
        //tree.inOrderPrint(); //D B E A F C G
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

    public Node<Key> find(Key key) {
        return find(key, root);
    }

    private Node<Key> find(Key key, Node<Key> node) {
        if (node == null) return null;
        if (node.key.equals(key)) return node;
        Node<Key> result = find(key, node.left);
        return result != null ? result : find(key, node.right);
    }

    private int height() {
        return height(root);
    }

    private int height(Node<Key> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int size() {
        return size(root);
    }

    private int size(Node<Key> node) {
        if (node == null) return 0;
        return size(node.left) + size(node.right) + 1;
    }
}
