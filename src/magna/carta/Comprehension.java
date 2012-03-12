package magna.carta;

import java.util.*;

public class Comprehension<IN, OUT> extends Generator<OUT> {
    private final Iterable<IN> iterable;
    private final Mapping<IN, OUT> mapping;
    private final Filter<IN> filter;
    private Iterator<IN> iterator;
    
    public Comprehension(Iterable<IN> iterable, Mapping<IN, OUT> mapping,
            Filter<IN> filter) {
        this.iterable = iterable;
        this.mapping = mapping;
        this.filter = filter;
    }
    
    @Override
    public void init() {
        iterator = iterable.iterator();
    }
    
    @Override
    protected final OUT yield() {
        while (iterator.hasNext()) {
            IN next = iterator.next();
            if (filter.selects(next)) {
                return mapping.apply(next);
            }
        }
        throw new NoSuchElementException();
    }
}
