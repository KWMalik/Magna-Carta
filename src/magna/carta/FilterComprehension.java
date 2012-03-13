package magna.carta;

import java.util.*;

class FilterComprehension<T> extends Comprehension<T, T> {
    private final Predicate<T> predicate;
    
    public FilterComprehension(Iterable<T> iterable, Predicate<T> predicate) {
        super(iterable);
        this.predicate = predicate;
    }
    
    @Override
    protected final T yield() {
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (predicate.selects(next)) {
                return next;
            }
        }
        throw new NoSuchElementException();
    }
}
