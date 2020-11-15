package st;

import org.junit.Test;

public class BSTTest {
    @Test
    public void testDelete() {
        BST<String, Integer> bst = new BST<>();
        bst.put("S", 1);
        bst.put("E", 2);
        bst.put("A", 3);
        bst.put("C", 4);
        bst.put("R", 5);
        bst.put("H", 6);
        bst.put("M", 7);
        bst.put("X", 8);

        for (String key: bst.keys()) {
            System.out.print(key + " ");
        }
        bst.delete("E");
        for (String key: bst.keys()) {
            System.out.print(key + " ");
        }
    }
}
