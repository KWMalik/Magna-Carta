package magna.carta;

import java.util.*;

public final class Range extends Generator<Integer> {
    private int start = 1;
    private int stop;
    private int step = 1;
    private int next;
    
    Range() {}
    
    public static Builder from(int start) {
        return new Builder().from(start);
    }
    
    public static Range to(int stop) {
        return new Builder().to(stop);
    }
    
    public Range step(int step) {
        this.step = step;
        reset();
        return this;
    }
    
    @Override
    protected void init() {
        next = start - step;
    }
    
    @Override
    protected Integer yield() {
        next += step;
        if (next < stop) {
            return next;
        }
        throw new NoSuchElementException();
    }
    
    public static final class Builder {
        private final Range range = new Range();
        
        private Builder from(int start) {
            range.start = start;
            return this;
        }
        
        public Range to(int stop) {
            range.stop = stop;
            range.reset();
            return range;
        }
    }
}
