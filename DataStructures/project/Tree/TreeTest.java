import com.sun.source.tree.BinaryTree;

public class TreeTest {

    TreeTest(){


        Tree<Integer> tree = new Tree<>();

        tree.print();
        tree.add(30);
        tree.print();
        tree.add(20);
        tree.add(60);
        tree.print();
        tree.add(40);
        tree.add(45);
        tree.add(35);
        tree.add(90);
        tree.print();
        tree.rotateRight();
        System.out.println("In Order:");
        tree.traverseInOrder();
        System.out.println("Pre Order:");
        tree.traversePreOrder();
        System.out.println("Post Order:");
        tree.traversePostOrder();

    }


    public static void TreeRotateTester(){
        Tree<Character> tree = new Tree<>();
        String test = "philmorsy";
        for (int i = 0; i < test.length(); i++)
            tree.add(test.charAt(i));

        System.out.println("\nOriginal:  Pre-order, In-order Array, Root");
        tree.traversePreOrder(tree.getRoot());
        System.out.println();

        System.out.println(tree.toArrayList());
        System.out.println("Root Before => "+tree.getRoot());
        tree.rotateRight();

        System.out.println("\n\nAfter Rotation:  Pre-order, In-order Array, Root");
        tree.traversePreOrder(tree.getRoot());
        System.out.println();
        System.out.println(tree.toArrayList()); //should not change
        System.out.println("Root After => "+tree.getRoot());
    }


    void print(Tree<?> tree){
        System.out.println("root: " + tree.getRoot().getValue());
        System.out.println("left: " + tree.getRoot().getLeft().getValue());
        System.out.println("right: " + tree.getRoot().getRight().getValue());

        System.out.println();
    }

    public static void main(String[] args) {
        new TreeTest();
//        TreeRotateTester();
    }


}
