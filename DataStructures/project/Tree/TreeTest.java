public class TreeTest {

    TreeTest(){
        Tree<String> tree = new Tree<>("M");
        tree.add("F");
        tree.add("H");
        tree.add("Y");
        tree.add("H");
        tree.add("Q");
        tree.traverseInOrder(tree.getRoot());
    }

    public static void main(String[] args) {
        new TreeTest();
    }

}
