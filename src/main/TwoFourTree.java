public class TwoFourTree {
    Node root = new Node();

    public  String get(int key) {
        return get(key, root);
    }

    private String get(int key, Node node) {
        if (node == null) return null;
        int i = 0;
        while (key > node.key[i] && i<node.keys) i++;
        if (key == node.key[i]) return node.value[i];
        if (key < node.key[i]) return get(key, node.child[i]);
        return get(key, node.child[i+1]);
    }

    public void put(int key, String value) {
        if (root == null) root = new Node(key, value);
        else root = put(key, value, root);
    }

    private Node put(int key, String value, Node node) {
        if (node.isLeaf) {
            node.insert(key, value);
        }
        else {
            int i = 0;
            while (i < node.keys && key > node.key[i]) i++;
            Node temp = put(key, value, node.child[i]);
            if (temp != node.child[i]) {    //
                node.insert(temp.key[0], temp.value[0]);
                node.child[i] = temp.child[0];
                node.child[i + 1] = temp.child[1];
            }
        }
        if (node.overflown()) return node.split();
        return node;
    }

    public void print() {
        root.print();
        System.out.print("\n");
    }

    static final int MAX_KEYS = 3;
    public static class Node {
        Node child[] = new Node[MAX_KEYS + 2];
        int key[] = new int[MAX_KEYS + 1];
        String value[] = new String[MAX_KEYS + 1];
        int keys = 0;
        boolean isLeaf = true;

        public Node(int key, String value) {
            this.key[0] = key;
            this.value[0] = value;
            keys = 1;
        }

        public Node() {

        }

        public void insert(int newKey, String newValue) {
            int i = keys;
            while (i > 0 && key[i-1] > newKey) {
                key[i] = key[i-1];
                value[i] = value[i-1];
                child[i+1] = child[i];
                i--;
            }
            key[i] = newKey;
            value[i] = newValue;
            keys++;
        }

        public boolean overflown() {
            return keys > MAX_KEYS;
        }

        public Node split() {
            int mid = keys/2;
            Node parent = new Node(key[mid], value[mid]);
            parent.isLeaf = false;
            Node right = new Node();
            right.keys = keys - mid - 1;
            for (int i = mid + 1; i < keys; i++) {
                right.key[i - mid - 1] = key[i];
                right.value[i - mid - 1] = value[i];
                right.child[i - mid] = child[i + 1];
                value[i] = null;
                child[i + 1] = null;
            }
            this.keys = mid;
            parent.child[0] = this;
            parent.child[1] = right;
            return parent;
        }

        public void print() {
            System.out.print("(");
            for (int i = 0; i < keys; i++) {
                if (!isLeaf) child[i].print();
                System.out.print(key[i] + " ");
            }
            if (!isLeaf) child[keys].print();
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        TwoFourTree tree = new TwoFourTree();
        tree.put(1, "One");
        tree.put(2, "Two");
        tree.print();
        System.out.println(tree.get(1));
    }
}