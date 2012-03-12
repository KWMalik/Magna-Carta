package magna.carta;

import java.util.*;

public class Comprehension<IN, OUT> extends Generator<OUT> {
    private final Iterable<IN> iterable;
    private final Mapping<IN, OUT> mapping;
    private final Filter<IN> filter;
    private Iterator<IN> iterator;
    private IN nextInput;
    private boolean hasNext;
    
    public Comprehension(Iterable<IN> iterable, Mapping<IN, OUT> mapping,
            Filter<IN> filter) {
        this.iterable = iterable;
        this.mapping = mapping;
        this.filter = filter;
    }
    
    @Override
    public void init() {
        iterator = iterable.iterator();
        nextInput();
    }
    
    public boolean hasNext() {
        return hasNext;
    }
    
    public OUT next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        OUT output = mapping.apply(nextInput);
        nextInput();
        return output;
    }
    
    private void nextInput() {
        hasNext = false;
        while (iterator.hasNext()) {
            IN next = iterator.next();
            if (filter.selects(next)) {
                nextInput = next;
                hasNext = true;
                break;
            }
        }
    }
}
