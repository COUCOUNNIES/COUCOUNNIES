import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TST<T extends Comparable<T>> implements Iterable<T> {
    // root node of the tree
    TSTNode<T> root;

    // constructor
    public TST() {
        this.root = null;
    }

    public TSTNode<T> insert_1(TSTNode<T> root, T element){
        TSTNode added_node = new TSTNode(element);
        if (root == null) {
            root = added_node;
        }
        else {
            if (element.compareTo(root.element) >= 1) {

                root.right=insert_1(root.right,element);
            }
            else if (element.compareTo(root.element) == 0) {
                root.mid=insert_1(root.mid,element);

            }
            else {
                root.left=insert_1(root.left,element);
            }
        }
        return root;
    }
    public void insert(T element) {
        this.root=insert_1(this.root, element);
    }

    public TSTNode<T> help_remove(TSTNode<T> root, T element){
        if (!contains(element)){
            return root;
        }
        if (root == null) {
            return null;
        }
        else if (element.compareTo(root.element) <= -1){
            root.left =help_remove(root.left,element);
        }
        else if (element.compareTo(root.element) >=1){
            root.right =help_remove(root.right,element);
        }
        else {
            if (root.mid != null){
                root=root.mid;
            }
            else if (root.left == null){
                root =root.right;
            }
            else if(root.right == null){
                root=root.left;
            }
            else {
                TSTNode<T> temp_node= root.left.findMax();
                if (temp_node.mid == null) {
                    root.element = root.left.findMax().element;
                    root.left = help_remove(root.left, root.element);
                }
                else{
                    root.element = root.left.findMax().element;
                    while (temp_node.mid != null){
                        insert(root.element);
                        temp_node.mid = temp_node.mid.mid;
                    }
                    root.left=temp_node.left;

                }
            }
        }
        return root;
    }

    public void remove(T element) {
        this.root=help_remove(this.root,element);
    }

    public boolean contains_1(TSTNode<T> root, T element){
        if (root== null){
            return false;
        }
        if (element.compareTo(root.element)==0){
            return true;
        }
        else {
            if (element.compareTo(root.element)>=1){

                return contains_1(root.right,element);
            }

            else{
                return contains_1(root.left,element);
            }

        }

    }
    public boolean contains(T element) {
        return contains_1(this.root, element);
    }

    public void help_rebalance(List<T> temp_list){
        if (temp_list.size() == 0){
            return ;
        }
        int mid= temp_list.size()/2;
        this.insert(temp_list.get(mid));

        help_rebalance(temp_list.subList(0,mid));
        help_rebalance(temp_list.subList(mid+1,temp_list.size()));


    }
    public void rebalance() {
        if (this.root == null){
            return;
        }

        ArrayList<T> temp_list= new ArrayList<>();
        for (T item: this){
            temp_list.add(item);
        }

        this.root=null;
        help_rebalance(temp_list);
    }
    /**
     * Caculate the height of the tree.
     * You need to implement the height() method in the TSTNode class.
     *
     * @return -1 if the tree is empty otherwise the height of the root node
     */
    public int height() {
        if (this.root == null)
            return -1;
        return this.root.height();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return new TSTIterator(this.root);

    }

    // --------------------PROVIDED METHODS--------------------
    // The code below is provided to you as a simple way to visualize the tree
    // This string representation of the tree mimics the 'tree' command in unix
    // with the first child being the left child, the second being the middle child, and the last being the right child.
    // The left child is connect by ~~, the middle child by -- and the right child by __.
    // e.g. consider the following tree
    //               5
    //            /  |  \
    //         2     5    9
    //                   /
    //                  8
    // the tree will be printed as
    // 5
    // |~~ 2
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |-- 5
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |__ 9
    //     |~~ 8
    //     |   |~~ null
    //     |   |-- null
    //     |   |__ null
    //     |-- null
    //     |__ null
    @Override
    public String toString() {
        if (this.root == null)
            return "empty tree";
        // creates a buffer of 100 characters for the string representation
        StringBuilder buffer = new StringBuilder(100);
        // build the string
        stringfy(buffer, this.root, "", "");
        return buffer.toString();
    }

    /**
     * Build a string representation of the tertiary tree.
     *
     * @param buffer         String buffer
     * @param node           Root node
     * @param nodePrefix     The string prefix to add before the node's data (connection line from the parent)
     * @param childrenPrefix The string prefix for the children nodes (connection line to the children)
     */
    private void stringfy(StringBuilder buffer, TSTNode<T> node, String nodePrefix, String childrenPrefix) {
        buffer.append(nodePrefix);
        buffer.append(node.element);
        buffer.append('\n');
        if (node.left != null)
            stringfy(buffer, node.left, childrenPrefix + "|~~ ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|~~ null\n");
        if (node.mid != null)
            stringfy(buffer, node.mid, childrenPrefix + "|-- ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|-- null\n");
        if (node.right != null)
            stringfy(buffer, node.right, childrenPrefix + "|__ ", childrenPrefix + "    ");
        else
            buffer.append(childrenPrefix + "|__ null\n");
    }

    /**
     * Print out the tree as a list using an enhanced for loop.
     * Since the Iterator performs an inorder traversal, the printed list will also be inorder.
     */
    public void inorderPrintAsList() {
        String buffer = "[";
        for (T element : this) {
            buffer += element + ", ";
        }
        int len = buffer.length();
        if (len > 1)
            buffer = buffer.substring(0, len - 2);
        buffer += "]";
        System.out.println(buffer);
    }
    public static void main (String args[]){
        TST<Integer> tree = new TST<Integer>();

        tree.insert(3);
        System.out.println(tree);
        tree.insert(0);
        System.out.println(tree);
        tree.insert(0);
        System.out.println(tree);
        tree.insert(0);
        System.out.println(tree);

        tree.insert(7);
        tree.insert(6);
        System.out.println(tree);
        tree.insert(8);
        tree.insert(1);
        System.out.println(tree);
        tree.insert(-1);
        System.out.println(tree);
        tree.insert(-1);
        System.out.println(tree);
        tree.remove(3);
        System.out.println(tree);


    }
}

