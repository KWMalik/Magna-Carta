package magna.carta;

import java.util.*;

public abstract class Generator<T> implements Iterable<T>, Iterator<T> {
    private T next;
    private boolean hasNext;
    
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
    protected abstract T yield();
    
    @Override
    public final Iterator<T> iterator() {
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
    public final T next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        T element = next;
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
