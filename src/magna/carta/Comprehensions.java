package magna.carta;

public final class Comprehensions {
    private Comprehensions() {
    }

    public static <T> Generator<T> generate(Iterable<T> iterable) {
        return generate(iterable, new Identity<T>(), new AllPass<T>());
    }

    public static <IN, OUT> Generator<OUT> generate(Iterable<IN> iterable,
            Mapping<IN, OUT> mapping) {
        return generate(iterable, mapping, new AllPass<IN>());
    }

    public static <T> Generator<T> generate(Iterable<T> iterable,
            Filter<T> filter) {
        return generate(iterable, new Identity<T>(), filter);
    }

    public static <IN, OUT> Generator<OUT> generate(Iterable<IN> iterable,
            Mapping<IN, OUT> mapping, Filter<IN> filter) {
        return new Comprehension<IN, OUT>(iterable, mapping, filter);
    }

    public static final class Identity<T> implements Mapping<T, T> {
        public T apply(T input) {
            return input;
        }
    }

    public static final class AllPass<IN> implements Filter<IN> {
        public boolean selects(IN item) {
            return true;
        }
    }
}
