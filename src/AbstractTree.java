import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** The course abstract implementation of the Tree interface. */
public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private class ElementIterator implements Iterator<E> {
        private final Iterator<Position<E>> positionIterator =
                positions().iterator();

        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        public E next() {
            return positionIterator.next().getElement();
        }

        public void remove() {
            positionIterator.remove();
        }
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    private void preorderSubtree(
            Position<E> p,
            List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> child : children(p)) {
            preorderSubtree(child, snapshot);
        }
    }

    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            postorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    private void postorderSubtree(
            Position<E> p,
            List<Position<E>> snapshot) {
        for (Position<E> child : children(p)) {
            postorderSubtree(child, snapshot);
        }
        snapshot.add(p);
    }

    public Iterable<Position<E>> positions() {
        return preorder();
    }
}
