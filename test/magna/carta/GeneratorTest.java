package magna.carta;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class GeneratorTest {
    
    @Test
    public void testCounter() {
        Iterator<Integer> counter = new Generator<Integer>() {
            private int x;
            
            @Override
            public void init() {
                x = 2;
            }
            
            @Override
            protected Integer yield() throws NoSuchElementException {
                return x++;
            }
        }.iterator();
        assertThat(counter.next(), is(2));
        assertThat(counter.next(), is(3));
        assertThat(counter.next(), is(4));
    }
}
