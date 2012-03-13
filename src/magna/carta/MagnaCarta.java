package magna.carta;

import java.util.*;

public final class MagnaCarta {
    private MagnaCarta() {}
    
    public static <T> Generator<T> from(Iterable<T> iterable) {
        return new SelfComprehension<T>(iterable);
    }
    
    public static <T, C extends Collection<T>> C to(C collection,
            Iterable<T> iterable) {
        for (T elem : iterable) {
            collection.add(elem);
        }
        return collection;
    }
    
    public static <K, V, M extends Map<K, V>> M to(M map,
            Iterable<Map.Entry<K, V>> iterable) {
        for (Map.Entry<K, V> entry : iterable) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
    
    public static Generator<Integer> range() {
        return new Range();
    }
}
