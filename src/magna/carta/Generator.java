package magna.carta;

import java.util.Iterator;

public abstract class Generator<T> implements Iterable<T>, Iterator<T> {

	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
