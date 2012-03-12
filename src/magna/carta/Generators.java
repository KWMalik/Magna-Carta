package magna.carta;

import java.util.*;

public final class Generators {
    private Generators() {}
    
    public Generator<Integer> range(int start, int stop, int step) {
        return new Range(start, stop, step);
    }
    
    public class Range extends Generator<Integer> {
        private final int start;
        private final int stop;
        private final int step;
        private int next;
        
        public Range(int start, int stop, int step) {
            this.start = start;
            this.stop = stop;
            this.step = step;
        }
        
        @Override
        public void init() {
            next = start - step;
        }
        
        @Override
        public Integer yield() {
            next += step;
            if (next < stop) {
                return next;
            }
            throw new NoSuchElementException();
        }
    }
}
