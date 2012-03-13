package magna.carta;

public interface Predicate<T> {
    boolean selects(T item);
}
