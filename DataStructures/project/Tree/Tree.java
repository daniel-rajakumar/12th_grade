import java.util.ArrayList;

/**
 * A class that implements a Binary-Search-Tree.
 * @author Daniel Rajakumar
 * @date May 6, 2022
 * @param <E>
 */
public class Tree <E extends Comparable<E>> {

    private TreeNode<E> root;
    private int size;


    public int count(){
        return count(getRoot());
    }

    private int count(TreeNode<E> root){
        if (root == null) return 0;
        return 1 + count(root.getLeft()) + count(root.getRight());
    }

    /**
     * Creates an empty tree.
     */
    public Tree() {
        root = null;
        size = 0;
    }

    /**
     * Creates a tree with given value as it's root.
     * @param root - the value of the node
     */
    public Tree(E root){
        this.root = new TreeNode<>(root);
        size = 1;
    }

    /**
     * Visualize the tree.
     */
    public void print(){
        if (root == null)
            System.out.print("Empty Tree");
        print(root,0,"");
        System.out.println();
    }

    private void print(TreeNode<E> curr, int depth, String s) {
        if (curr == null) return;

        for (int i = 1; i <= depth - 1; i++)
            System.out.print("|\t");

        if (depth == 0)
            System.out.println("[" + curr.getValue() + "] <- root (L___ left, R___ right)");

        if (depth > 0)
            System.out.println(s + curr.getValue());

        print(curr.getLeft() ,depth + 1,"L___"); // indicates left or "less than" side
        print(curr.getRight(),depth + 1,"R___");  // indicate right or "greater than" side
    }

    /**
     * Returns the root of the tree
     * @return root
     */
    public TreeNode<E> getRoot() {
        return root;
    }

    /**
     * Returns the number of elements in the tree
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Append an element to the tree
     * @param value - value that is being added as a node.
     */
    public void add(E value){
        root = add(root, value);
        size++;
    }

    /**
     * Append multiple elements to the list
     * @param values - values that are being added as nodes.
     */
    public void addAll(E... values){ for (E value: values) add(value); }

    private TreeNode<E> add(TreeNode<E> root, E val){
        if (root == null)                    return new TreeNode<>(val);
        if (val.compareTo(root.value) <= 0)  return root.setLeft (add(root.getLeft() , val));
                                             return root.setRight(add(root.getRight(), val));
    }

    /**
     * Print all the tree's elements in pre-order.
     */
    public void traversePreOrder() { traversePreOrder(getRoot()); System.out.println(); }

    private void traversePreOrder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.getValue() + " ");
        traversePreOrder(root.getLeft());
        traversePreOrder(root.getRight());
    }


    /**
     * Print all the tree's elements in in-order.
     */
    public void traverseInOrder() { traverseInOrder(getRoot()); System.out.println(); }

    private void traverseInOrder(TreeNode<E> root) {
        if (root == null) return;
        traverseInOrder(root.getLeft());
        System.out.print(root.getValue() + " ");
        traverseInOrder(root.getRight());
    }

    /**
     * Returns an arraylist containing all the elements in-order.
     * @return arrayList of all element
     */
    public ArrayList<E> toArrayList(){ return toArrayList(getRoot(), new ArrayList<>()); }

    private ArrayList<E> toArrayList(TreeNode<E> root, ArrayList<E> list) {
        if (root == null) return list;
               toArrayList(root.getLeft() , list).add(root.getValue());
        return toArrayList(root.getRight(), list);
    }


    /**
     * Print all the tree's elements in post-order.
     */
    public void traversePostOrder() { traversePostOrder(getRoot()); System.out.println(); }

    private void traversePostOrder(TreeNode<E> root) {
        if (root == null) return;
        traversePostOrder(root.getLeft());
        traversePostOrder(root.getRight());
        System.out.print(root.getValue() + " ");
    }

    /**
     * Returns the minimum value in the list.
     * @return minimum value
     */
    public E minValue(){ return getRoot() == null ? null : minValue(getRoot()); }

    private E minValue(TreeNode<E> node){
        if (node.getLeft() == null)
            return node.getValue();
        return minValue(node.getLeft());
    }

    /**
     * Returns the maximum value in the list.
     * @return maximum value
     */
    public E maxValue(){ return getRoot() == null ? null : maxValue(getRoot()); }

    private E maxValue(TreeNode<E> node){
        if (node.getRight() == null)
            return node.getValue();
        return maxValue(node.getRight());
    }

    public void clear(){
        root = null;
    }

    /**
     * Returns whether the value is in the tree.
     * @param value - the value that is being tested.
     * @return boolean of contains.
     */
    public boolean contains(E value){ return contains(getRoot(), value); }

    private boolean contains(TreeNode<E> node, E value){
        if (node == null)                         return false;
        if (node.getValue().compareTo(value) > 0) return contains(node.getLeft(),  value);
        if (node.getValue().compareTo(value) < 0) return contains(node.getRight(), value);
        return true;
    }

    /**
     * Rotates the tree to the left.
     */
    public void rotateLeft() { rotateLeft(getRoot()); }

    private void rotateLeft(TreeNode<E> node){
        TreeNode<E> temp = node.getRight();  // store a copy of [original-root's right]
        root.setRight(temp.getLeft());      // set [original-root's right] as [original-root's right's left]
        temp.setLeft(root);                // set [original-root's right's left] as [original-root].
        root = temp;                      // set [original-root's right] as the new root.
    }

    /**
     * Rotates the tree to the right.
     */
    public void rotateRight() { rotateRight(getRoot()); }

    private void rotateRight(TreeNode<E> node){
        TreeNode<E> temp = node.getLeft(); // store a copy of [original-root's left]
        root.setLeft(temp.getRight());    // set [original-root's left] as [original-root's left's right]
        temp.setRight(root);             // set [original-root's left's right] as [original-root].
        root = temp;                    // set [original-root's left] as the new root.
    }

    /**
     * Deletes the element from the tree.
     * @param value - the first node of value that is being removed.
     */
    public void remove(E value){
        if (!contains(value)) return;
        size--;
        root = remove(getRoot(), value);
    }

    private TreeNode<E> remove(TreeNode<E> node, E value){
        if (node == null) return null;

        // funnel down till the removal node
        if (value.compareTo(node.getValue()) < 0) return node.setLeft (remove(node.getLeft() , value));
        if (value.compareTo(node.getValue()) > 0) return node.setRight(remove(node.getRight(), value));
        // in the node that is being removed

        // 0 || 1 child - set the current-node to its child node.
        if (node.getLeft()  == null) return node.getRight();
        if (node.getRight() == null) return node.getLeft();

        // 2 children - update the current-value as inorder-successor (smallest node)
        node.setValue(minValue(node.getRight()));
        // 2 children - update the right-node by removing the inorder-successor (smallest node)
        return node.setRight(remove(node.getRight(), node.getValue()));
    }

    /**
     * Tree uses this as its node.
     * @param <E> - The type of value the tree holds.
     */
     static class TreeNode<E> {

        private E value;
        private TreeNode<E> left, right;

        /**
         * creates a node with given value
         * @param value - the value of the node.
         */
        TreeNode(E value){
            this.value = value;
        }

        /**
         * Sets the right node of this node
         * @param right - the child node that is being added.
         * @return updated node
         */
        public TreeNode<E> setRight(TreeNode<E> right){
            this.right = right;
            return this;
        }

        /**
         * Sets the left node of this node
         * @param left - the child node that is being added.
         * @return updated node
         */
        public TreeNode<E> setLeft(TreeNode<E> left){
            this.left = left;
            return this;
        }

        /**
         * Sets this node's value.
         * @param value - the value that is being replaced.
         * @return removed node's value.
         */
         public E setValue(E value) {
            E temp = this.value;
            this.value = value;
            return temp;
        }

        /**
         * Returns the right node of this node.
         * @return right node
         */
        public TreeNode<E> getRight(){
            return this.right;
        }

        /**
         * Returns the left node of this node
         * @return left node
         */
         public TreeNode<E> getLeft(){
            return this.left;
        }

        /**
         * Returns this node's value
         * @return node's value
         */
        public E getValue(){
            return this.value;
        }
    }
}
