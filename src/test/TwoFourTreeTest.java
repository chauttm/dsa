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
    }
}
