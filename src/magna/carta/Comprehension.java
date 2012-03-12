package magna.carta;

import java.util.Iterator;

public class Comprehension<IN, OUT> extends Generator<OUT> {
    private final Iterator<IN> iterator;
    private final Mapping<IN, OUT> mapping;
    private final Filter<IN> filter;
    private IN next;

    Comprehension(Iterator<IN> iterator, Mapping<IN, OUT> mapping,
            Filter<IN> filter) {
        this.iterator = iterator;
        this.mapping = mapping;
        this.filter = filter;
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