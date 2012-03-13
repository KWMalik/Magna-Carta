package magna.carta;

class MapComprehension<IN, OUT> extends Comprehension<IN, OUT> {
    private final Function<IN, OUT> function;
    
    public MapComprehension(Iterable<IN> iterable, Function<IN, OUT> function) {
        super(iterable);
        this.function = function;
    }
    
    @Override
    protected final OUT yield() {
        return function.apply(iterator.next());
    }
}
