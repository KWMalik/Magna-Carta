package magna.carta;

public final class Generators {
    private Generators() {}
    
    public Generator<Integer> range(int start, int stop, int step) {
        return new Range(start, stop, step);
    }
    
    public class Range extends Generator<Integer> {
        private final int start;
        private final int stop;
        private final int step;
        private int current;
        
        public Range(int start, int stop, int step) {
            this.start = start;
            this.stop = stop;
            this.step = step;
        }
        
        @Override
        public void init() {
            current = start;
        }
        
        public boolean hasNext() {
            return current + step < stop;
        }
        
        public Integer next() {
            Integer next = Integer.valueOf(current);
            current += step;
            return next;
        }
    }
}
