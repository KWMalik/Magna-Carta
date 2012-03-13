package magna.carta;

import static magna.carta.Comprehensions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class GeneratorTest {
    
    @Test
    public void counter() {
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
    
    @Test
    public void fibonacci() {
        Generator<Integer> fib = new Generator<Integer>() {
            private int b;
            private int c;
            private int max;
            
            @Override
            protected void init() {
                b = 0;
                c = 1;
                max = 1000;
            }
            
            @Override
            protected Integer yield() {
                if (b < max) {
                    int a = b;
                    b = c;
                    c = a + b;
                    return a;
                }
                throw new NoSuchElementException();
            }
        };
        StringBuilder builder = new StringBuilder();
        for (int i : fib) {
            builder.append(i).append(' ');
        }
        
        assertEquals(builder.toString(),
                "0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 ");
        
        ArrayList<Integer> list = to(new ArrayList<Integer>(), fib);
        
        assertArrayEquals(list.toArray(), new Integer[] { 0, 1, 1, 2, 3, 5, 8,
                13, 21, 34, 55, 89, 144, 233, 377, 610, 987 });
    }
}
