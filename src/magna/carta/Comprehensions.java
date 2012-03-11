package magna.carta;

import java.util.Iterator;

public final class Comprehensions {

	public <T> Generator<T> generator(final Iterable<T> iterable) {
		return new Generator<T>() {
			Iterator<T> iterator = iterable.iterator();

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public T next() {
				return iterator.next();
			}
		};
	}
	
	public <IN,OUT> Generator<OUT> generator(final Iterable<IN> iterable, final Mapping<IN,OUT> mapping) {
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
