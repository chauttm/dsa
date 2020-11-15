package st;

public class Demo {
    public static void main(String[] args) {
        ST<String, Integer> st = new BST<>();
        st.put("One", 1);
        st.put("Two", 2);
        st.put("Three", 3);

        for (String key: st.keys()) {
            System.out.println(key + ": " + st.get(key));
        }

        BST<String, Integer> bst = new BST<>();
        bst.put("One", 1);
        bst.put("Two", 2);
        bst.put("Three", 3);
        System.out.println(bst.min());
        System.out.println(bst.max());
        System.out.println(bst.size());
        System.out.println(bst.floor("On"));

        System.out.println(bst.rank("One"));

        bst.deleteMin();
        for (String key: bst.keys()) {
            System.out.println(key + ": " + st.get(key));
        }
    }
}
