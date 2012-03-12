package magna.carta;

import java.util.Iterator;

public abstract class Generator<T> implements Iterable<T>, Iterator<T> {

    /**
     * Resets this generator to its initial state.
     * 
     * Called by {@link #iterator()}, meaning that this method will get called
     * automatically each time you pass this generator into a for loop.
     */
    public abstract void init();

    public final Iterator<T> iterator() {
        init();
        return this;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
