package magna.carta;

public interface Filter<T> {
	boolean selects(T item);
}
