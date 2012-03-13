package magna.carta;

import java.util.*;

class Range extends Generator<Integer> {
    private int start;
    private int stop;
    private int step;
    private int next;
    
    public Range() {}
    
    public Range from(int start) {
        this.start = start;
        return this;
    }
    
    public Range to(int stop) {
        this.stop = stop;
        return this;
    }
    
    public Range step(int step) {
        this.step = step;
        return this;
    }
    
    @Override
    protected final void init() {
        next = start - step;
    }
    
    @Override
    protected final Integer yield() {
        next += step;
        if (next < stop) {
            return next;
        }
        throw new NoSuchElementException();
    }
}
