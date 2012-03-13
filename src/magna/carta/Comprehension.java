package magna.carta;

import java.util.*;

abstract class Comprehension<IN, OUT> extends Generator<OUT> {
    private final Iterable<IN> iterable;
    protected Iterator<IN> iterator;
    
    public Comprehension(Iterable<IN> iterable) {
        this.iterable = iterable;
    }
    
    @Override
    protected final void init() {
        iterator = iterable.iterator();
    }
}
