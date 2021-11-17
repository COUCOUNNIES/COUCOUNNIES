import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
// add your imports here

class TSTIterator<T extends Comparable<T>> implements Iterator<T> {
    List<T> ordered_list= new ArrayList<T>();
    Iterator<T> it;

    public void constructor_helper(TSTNode<T> node){
        if (node == null){
            return;
        }
        if(node.left != null){
            constructor_helper(node.left);
        }
        ordered_list.add(node.element);
        if (node.mid != null){
            constructor_helper(node.mid);
        }
        if (node.right!= null){
            constructor_helper(node.right);
        }
        return;
    }
    public TSTIterator(TSTNode<T> node){
        constructor_helper(node);
        it = ordered_list.iterator();

    }
    /**
     * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true} if {@link #next}
     * would return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    /*
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     *
     * @throws NoSuchElementException
     *         if the iteration has no more elements
     */
    @Override
    public T next() {
        return it.next();
    }
}