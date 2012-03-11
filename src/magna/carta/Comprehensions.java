package magna.carta;

import java.util.Iterator;

public final class Comprehensions {

	public static <T> Generator<T> generator(final Iterable<T> iterable) {
		return generator(iterable, new Mapping<T,T>() {
			@Override
			public T apply(T input) {
				return input;
			}
		});
	}
	
	public static <IN,OUT> Generator<OUT> generator(final Iterable<IN> iterable, final Mapping<IN,OUT> mapping) {
		return new Generator<OUT>() {
			Iterator<IN> iterator = iterable.iterator();

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public OUT next() {
				return mapping.apply(iterator.next());
			}
		};
	}
}
