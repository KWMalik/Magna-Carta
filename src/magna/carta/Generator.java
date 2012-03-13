package magna.carta;

import java.util.*;

public abstract class Generator<E> implements Iterable<E>, Iterator<E> {
    private E next;
    private boolean hasNext;
    
    public Generator() {
        iterator();
    }
    
    /**
     * Resets this generator to its initial state.
     * 
     * Called by {@link #iterator()}, meaning that this method will get called
     * automatically each time you pass this generator into a for loop.
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
    
    public final <T> Generator<T> map(Function<E, T> f) {
        return new MapComprehension<E, T>(this, f);
    }
    
    public final Generator<E> filter(Predicate<E> p) {
        return new FilterComprehension<E>(this, p);
    }
    
    @Override
    public final Iterator<E> iterator() {
        hasNext = true;
        init();
        readAhead();
        return this;
    }
    
    @Override
    public final boolean hasNext() {
        return hasNext;
    }
    
    @Override
    public final E next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        E element = next;
        readAhead();
        return element;
    }
    
    private void readAhead() {
        try {
            next = yield();
        } catch (NoSuchElementException e) {
            hasNext = false;
        }
    }
    
    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
