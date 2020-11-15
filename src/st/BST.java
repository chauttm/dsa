package st;

import java.util.ArrayList;
import java.util.List;

public class BST<Key extends Comparable<Key>, Value> implements ST<Key, Value>
{
    private Node root;

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, 1);
        int cmp = node.key.compareTo(key);
        if (cmp > 0) node.left = put(node.left, key, value);
        else if (cmp < 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    private int size(Node node) {
        return node == null ? 0 : node.count;
    }

    @Override
    public Value get(Key key) {
        return find(root, key);
    }

    @Override
    public void delete(Key key) {
        root = delete(key, root);
    }

    private Node delete(Key key, Node node) {
        if (node == null) return null;
        int cmp = node.key.compareTo(key);
        if (cmp > 0) node.left = delete(key, node.left);
        else if (cmp < 0) node.right = delete(key, node.right);
        else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;

            Node minRight = min(node.right);
            node.right = deleteMin(node.right);
            node.key = minRight.key;
            node.value = minRight.value;
        }
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Value find(Node node, Key key) {
        if (node == null) return null;
        int cmp = node.key.compareTo(key);
        if (cmp > 0) return find(node.left, key);
        if (cmp < 0) return find(node.right, key);
        return node.value;
    }

    @Override
    public Iterable<Key> keys() {
        List<Key> keys = new ArrayList<>();
        getInOrderKeys(keys, root);
        return keys;
    }

    private void getInOrderKeys(List<Key> keys, Node node) {
        if (node == null) return;
        getInOrderKeys(keys, node.left);
        keys.add(node.key);
        getInOrderKeys(keys, node.right);
    }

    public Key min() {
        return root == null ? null : (min(root)).key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    public Key max() {
        return root == null ? null : (max(root)).key;
    }

    private Node max(Node node) {
        return node.right == null ? node : max(node.right);
    }

    public Key floor(Key key) {
        Node n = floor(key, root);
        return n == null ? null : n.key;
    }

    private Node floor(Key key, Node node) {
        if (node == null) return null;
        int cmp = node.key.compareTo(key);
        if (cmp == 0) return node;
        if (cmp < 0) return floor(key, node.right);
        Node left = floor(key, node.left);
        return left == null ? node : left;
    }

    public int size() {
        return size(root);
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node node) {
        if (node == null) return 0;
        int cmp = node.key.compareTo(key);
        if (cmp == 0) return size(node.left);
        if (cmp > 0) return rank(key, node.left);
        return size(node.left) + 1 + rank(key, node.right);
    }

    public void deleteMin() {
        if (root != null) root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private class Node
    {
        Key key;
        Value value;
        Node left;
        Node right;
        int count;

        public Node(Key key, Value value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }
    }
}
