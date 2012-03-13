package magna.carta;

import java.util.*;

public final class Range extends Generator<Integer> {
    private int start;
    private int stop;
    private int step;
    private int next;
    
    Range() {}
    
    public Range from(int start) {
        this.start = start;
        iterator();
        return this;
    }
    
    public Range to(int stop) {
        this.stop = stop;
        iterator();
        return this;
    }
    
    public Range step(int step) {
        this.step = step;
        iterator();
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
}
