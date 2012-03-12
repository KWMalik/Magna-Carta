package magna.carta;

import java.util.Iterator;

public final class Comprehensions {

    public static <T> Generator<T> generate(final Iterable<T> iterable) {
        return generate(iterable, new Mapping<T, T>() {
            public T apply(T input) {
                return input;
            }
        });
    }

    public static <IN, OUT> Generator<OUT> generate(
            final Iterable<IN> iterable, final Mapping<IN, OUT> mapping) {
        return generate(iterable, mapping, new Filter<IN>() {
            public boolean selects(IN item) {
                return true;
            }
        });
    }

    public static <IN, OUT> Generator<OUT> generate(
            final Iterable<IN> iterable, final Mapping<IN, OUT> mapping,
            final Filter<IN> filter) {
        return new Generator<OUT>() {
            Iterator<IN> iterator = iterable.iterator();
            private IN next;

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
        };
    }
}
