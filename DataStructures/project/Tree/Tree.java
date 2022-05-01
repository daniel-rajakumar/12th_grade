import org.w3c.dom.Node;

import java.util.ArrayList;
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
        size = 1;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    void addAll(E... vals){
        for (E val: vals)
            add(val);
    }

    void add(E val){
        root = add(root, val);
        size++;
    }

    TreeNode<E> add(TreeNode<E> root, E val){
        if (root == null)                    return new TreeNode<>(val);
        if (val.compareTo(root.value) <= 0)  root.setLeft(add(root.getLeft(), val));
        if (val.compareTo(root.value) > 0)   root.setRight(add(root.getRight(), val));
        return root;
    }


    public void traverseInOrder() { traverseInOrder(getRoot()); System.out.println(); }

    private void traverseInOrder(TreeNode<E> root) {
        if (root == null) return;
        traverseInOrder(root.getLeft());
        System.out.print(root.getValue() + " ");
        traverseInOrder(root.getRight());
    }


    public void traversePostOrder() { traversePostOrder(getRoot()); System.out.println(); }

    private void traversePostOrder(TreeNode<E> root) {
        if (root == null) return;
        traversePostOrder(root.getLeft());
        traversePostOrder(root.getRight());
        System.out.print(root.getValue() + " ");
    }

    public void traversePreOrder() { traversePreOrder(getRoot()); System.out.println(); }

    public void traversePreOrder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.getValue() + " ");
        traversePreOrder(root.getLeft());
        traversePreOrder(root.getRight());
    }

    public E minValue(){ return getRoot() == null ? null : minValue(getRoot()); }

    private E minValue(TreeNode<E> node){
        if (node.getLeft() == null)
            return node.getValue();
        return minValue(node.getLeft());
    }


    public E maxValue(){ return getRoot() == null ? null : maxValue(getRoot()); }

    private E maxValue(TreeNode<E> node){
        if (node.getRight() == null)
            return node.getValue();
        return maxValue(node.getRight());
    }


    public ArrayList<E> toArrayList(){ return toArrayList(getRoot(), new ArrayList<>()); }

    private ArrayList<E> toArrayList(TreeNode<E> root, ArrayList<E> list) {
        if (root == null) return list;

        toArrayList(root.getLeft(), list);
        list.add(root.getValue());
        toArrayList(root.getRight(), list);

        return list;
    }

    public boolean contains(E value){
        return contains(getRoot(), value);
    }

    private boolean contains(TreeNode<E> node, E value){
        if (node == null) return false;
        if (node.getValue().compareTo(value) > 0) return contains(node.getLeft(),  value);
        if (node.getValue().compareTo(value) < 0) return contains(node.getRight(), value);
        return true;
    }


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


    public void rotateRight(){ rotateRight(getRoot()); }

    private void rotateRight(TreeNode<E> node){
        TreeNode<E> temp = node.getLeft();
        root.setLeft(temp.getRight());
        temp.setRight(root);
        root = temp;
    }

    public void rotateLeft(){ rotateLeft(getRoot()); }

    private void rotateLeft(TreeNode<E> node){
        TreeNode<E> temp = node.getRight();
        root.setRight(temp.getLeft());
        temp.setLeft(root);
        root = temp;
    }


    public void print(){
        if (root == null)
            System.out.print("Empty Tree");
        print(root,0,"");
        System.out.println();
    }

    private void print(TreeNode<E> curr, int depth, String s) {
        if (curr == null)
            return;

        for (int i=1; i<=depth-1; i++) {
            System.out.print("|\t");
        }

        if (depth == 0)
            System.out.println("["+curr.value+"] <- root (L___ left, R___ right)");


        if (depth > 0){
            System.out.print(s);
            System.out.println(curr.value);
        }

        print(curr.left, depth + 1,"L___"); // indicates left or "less than" side
        print(curr.right,depth + 1,"R___");  // indicate right or "greater than" side

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
            this.right = right;
            return this;
        }

        TreeNode<E> setLeft(TreeNode<E> left){
            this.left = left;
            return this;
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
                    ", left="  + (left  == null ? "null" : left .getValue().toString()) +
                    ", right=" + (right == null ? "null" : right.getValue().toString()) +
                    '}';
        }





    }




}
