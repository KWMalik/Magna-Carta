package magna.carta;

import java.util.*;

public final class MagnaCarta {
    private MagnaCarta() {}
    
    public static <E, T extends Collection<E>> T to(T collection,
            Iterable<E> iterable) {
        for (E elem : iterable) {
            collection.add(elem);
        }
        return collection;
    }
    
    public static <K, V, T extends Map<K, V>> T to(T map,
            Iterable<Map.Entry<K, V>> iterable) {
        for (Map.Entry<K, V> entry : iterable) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}
