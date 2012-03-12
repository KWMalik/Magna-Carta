package magna.carta;

import java.util.Iterator;

public class Comprehension<IN, OUT> extends Generator<OUT> {
    private final Filter<IN> filter;
    private final Mapping<IN, OUT> mapping;
    private final Iterator<IN> iterator;
    private IN next;

    Comprehension(Iterator<IN> iterator, Mapping<IN, OUT> mapping,
            Filter<IN> filter) {
        this.filter = filter;
        this.mapping = mapping;
        this.iterator = iterator;
    }

    public boolean hasNext() {
        while (iterator.hasNext()) {
            next = iterator.next();
            if (filter.selects(next)) {
                return true;
            }
        }
        return false;
    }

    public OUT next() {
        return mapping.apply(next);
    }
}