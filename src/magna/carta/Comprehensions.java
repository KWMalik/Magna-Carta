package magna.carta;

public final class Comprehensions {
    public static <T> Generator<T> generate(Iterable<T> iterable) {
        return generate(iterable, new IdentityMapping<T>());
    }

    public static <IN, OUT> Generator<OUT> generate(Iterable<IN> iterable,
            Mapping<IN, OUT> mapping) {
        return generate(iterable, mapping, new AllPassFilter<IN>());
    }

    public static <IN, OUT> Generator<OUT> generate(Iterable<IN> iterable,
            Mapping<IN, OUT> mapping, Filter<IN> filter) {
        return new Comprehension<IN, OUT>(iterable.iterator(), mapping, filter);
    }

    private static final class IdentityMapping<T> implements Mapping<T, T> {
        public T apply(T input) {
            return input;
        }
    }

    private static final class AllPassFilter<IN> implements Filter<IN> {
        public boolean selects(IN item) {
            return true;
        }
    }
}
