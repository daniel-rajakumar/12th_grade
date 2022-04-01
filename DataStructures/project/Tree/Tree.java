import java.util.TreeMap;

public class Tree <E extends Comparable<E>> {

    private TreeNode<E> root;
    private int size;


    public Tree() {
        root = null;
        size = 0;
    }

    public Tree(E root){
        this.root = new TreeNode<>(root);
        size = 0;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    void add(E val){
        root = addRecur(root, val);
        size++;
    }

    TreeNode<E> addRecur(TreeNode<E> root, E val){
        if (root == null)                    return new TreeNode<>(val);
        if (val.compareTo(root.value) <= 0)  root.setLeft(addRecur(root.getLeft(), val));
        if (val.compareTo(root.value) > 0)   root.setRight(addRecur(root.getRight(), val));
        return root;
    }


    //used generally to walk through the values stored in the tree in order
    public void traverseInOrder(TreeNode<E> root)
    {
        if (root != null)
        {
            traverseInOrder(root.getLeft());
            System.out.println(root.getValue());
            traverseInOrder(root.getRight());
        }
    }


    public void traversePostOrder(TreeNode<E> root)
    {
        if (root !=null)
        {	traversePostOrder(root.getLeft());
            traversePostOrder(root.getRight());
            System.out.println(root.getValue());
        }
    }

    //used generally if you need to make a duplicate of your tree
    public void traversePreOrder(TreeNode<E> root)
    {
        if (root !=null)
        {
            System.out.println(root.getValue());
            traversePreOrder(root.getLeft());
            traversePreOrder(root.getRight());
        }
    }

    // write add method that calls addRecursive
    static class TreeNode<E> {

        private E value;
        private TreeNode<E> right;
        private TreeNode<E> left;

        TreeNode(E value){
            this.value = value;
        }

          //////////////////////
         ////// SETTERS ///////
        //////////////////////
        TreeNode<E> setRight(TreeNode<E> right){
            TreeNode<E> temp = this.right;
            this.right = right;
            return temp;
        }

        TreeNode<E> setLeft(TreeNode<E> left){
            TreeNode<E> temp = this.left;
            this.left = left;
            return temp;
        }

         E setValue(E value) {
            E temp = this.value;
            this.value = value;
            return temp;
        }

          ///////////////////////
         ////// GETTERS ////////
        ///////////////////////
        TreeNode<E> getRight(){
            return right;
        }

        TreeNode<E> getLeft(){
            return left;
        }

        E getValue(){
            return value;
        }


          ///////////////////////
         /////// OTHERS ////////
        ///////////////////////

        @Override
        public String toString() {
            return "TreeNode{" +
                    "value=" + value +
                    ", right=" + right +
                    ", left=" + left +
                    '}';
        }
    }




}
