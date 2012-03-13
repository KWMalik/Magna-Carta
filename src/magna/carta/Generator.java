package magna.carta;

import java.util.*;

public abstract class Generator<E> implements Iterable<E> {
    private E nextElem;
    private boolean hasNext;
    
    /**
     * Creates a new generator that returns the elements from the given
     * iterable.
     */
    public static <T> Generator<T> from(Iterable<T> iterable) {
        return new SelfComprehension<T>(iterable);
    }
    
    public final <T> Generator<T> map(Function<E, T> f) {
        return new MapComprehension<E, T>(this, f);
    }
    
    public final Generator<E> filter(Predicate<E> p) {
        return new FilterComprehension<E>(this, p);
    }
    
    /**
     * Set this generator's variables to their initial state.
     * 
     * Called by {@link #iterator()}.
     */
    protected abstract void init();
    
    /**
     * Generates the next element.
     * 
     * Called by {@link #next()}.
     * 
     * @throws NoSuchElementException
     *             if there is no next element.
     */
    protected abstract E yield();
    
    @Override
    public final Iterator<E> iterator() {
        hasNext = true;
        init();
        readAhead();
        return new Iter();
    }
    
    private void readAhead() {
        try {
            nextElem = yield();
        } catch (NoSuchElementException e) {
            hasNext = false;
        }
    }
    
    private final class Iter implements Iterator<E> {
        @Override
        public final boolean hasNext() {
            return hasNext;
        }
        
        @Override
        public final E next() {
            if (!hasNext) {
                throw new NoSuchElementException();
            }
            E element = nextElem;
            readAhead();
            return element;
        }
        
        @Override
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
