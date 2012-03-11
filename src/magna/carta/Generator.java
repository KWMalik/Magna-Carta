package magna.carta;

import java.util.Iterator;

public abstract class Generator<T> implements Iterable<T>, Iterator<T> {

	public Iterator<T> iterator() {
		return this;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
