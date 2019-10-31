import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TwoFourTreeTest {
    @Test
    public void testNodeInsert() {
        TwoFourTree.Node node = new TwoFourTree.Node();
        node.insert(1, "one");
        assertEquals(1, node.keys);
        assertEquals(1, node.key[0]);
        assertEquals("one", node.value[0]);

        node.insert(2, "two");
        assertEquals(2, node.keys);

        node.insert(3, "three");
        assertEquals(3, node.keys);
        assertEquals(3, node.key[2]);
        assertEquals("three", node.value[2]);
    }

    @Test
    public void testNodeOverflowTest() {
        TwoFourTree.Node node = new TwoFourTree.Node();
        node.insert(1, "A");
        node.insert(2, "B");
        node.insert(3, "C");
        assertFalse(node.overflown());
        node.insert(4, "D");
        assertTrue(node.overflown());
    }

    @Test
    public void testNodeSplit() {
        TwoFourTree.Node node = new TwoFourTree.Node();
        node.insert(1, "A");
        node.insert(2, "B");
        node.insert(3, "C");
        node.insert(4, "D");
        node.print();
        System.out.print("\n");

        TwoFourTree.Node result = node.split();
        result.print();

        assertEquals(1, result.keys);
        assertEquals(3, result.key[0]);

        TwoFourTree.Node left = result.child[0];
        TwoFourTree.Node right = result.child[1];
        assertEquals(2, left.keys);
        assertEquals(2, left.key[1]);

        assertEquals(1, right.keys);
        assertEquals(4, right.key[0]);
    }

    @Test
    public void testNodeSplit_parent() {
        TwoFourTree.Node node = new TwoFourTree.Node();
        node.insert(10, "A");
        node.insert(20, "B");
        node.insert(30, "C");
        node.insert(40, "D");
        node.isLeaf = false;

        node.child[0] = new TwoFourTree.Node(5, "5");
        node.child[1] = new TwoFourTree.Node(15, "5");
        node.child[2] = new TwoFourTree.Node(25, "5");
        node.child[3] = new TwoFourTree.Node(35, "5");
        node.child[4] = new TwoFourTree.Node(45, "5");

        node.print();
        System.out.print("\n");

        TwoFourTree.Node result = node.split();
        result.print();


        TwoFourTree.Node left = result.child[0];
        TwoFourTree.Node right = result.child[1];

        assertEquals(2, left.keys);
        assertEquals(1, right.keys);
    }

    @Test
    public void testTreeInsert() {
        TwoFourTree tree = new TwoFourTree();
        tree.put(10, "A"); tree.print();
        tree.put(20, "B"); tree.print();
        tree.put(30, "C"); tree.print();
        tree.put(40, "D"); tree.print();
        assertEquals(2, tree.root.child[0].keys);
        assertEquals(1, tree.root.keys);
        tree.put(50, "E");
        assertEquals(2, tree.root.child[0].keys);
        assertEquals(2, tree.root.child[1].keys);

        tree.print();
        tree.put(60, "D");

        tree.print();
        tree.put(70, "D"); tree.print();
        tree.put(45, "D"); tree.print();
        tree.put(80, "D"); tree.print();
        tree.put(90, "D"); tree.print();
        tree.put(100, "D"); tree.print();
        tree.put(41, "D"); tree.print();
    }

    @Test
    public void testTreeDelete() {
        TwoFourTree tree = new TwoFourTree();
        tree.put(10, "A"); tree.print();
        tree.put(20, "B"); tree.print();
        tree.put(30, "C"); tree.print();
        tree.delete(10); tree.print();

        assertEquals(2, tree.root.keys);
        assertEquals(20, tree.root.key[0]);

        tree.delete(30); tree.print();

        assertEquals(1, tree.root.keys);
        assertEquals(20, tree.root.key[0]);

        tree.delete(20); tree.print();

        assertEquals(0, tree.root.keys);
    }
}
