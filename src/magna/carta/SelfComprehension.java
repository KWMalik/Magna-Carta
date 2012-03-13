package magna.carta;

class SelfComprehension<T> extends Comprehension<T, T> {
    public SelfComprehension(Iterable<T> iterable) {
        super(iterable);
    }
    
    @Override
    protected final T yield() {
        return iterator.next();
    }
}
