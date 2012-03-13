package magna.carta;

import static magna.carta.Generator.*;
import static magna.carta.MagnaCarta.*;
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
        
        assertEquals("0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 ",
                builder.toString());
        
        ArrayList<Integer> list = to(new ArrayList<Integer>(), fib);
        
        assertArrayEquals(new Integer[] { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55,
                89, 144, 233, 377, 610, 987 }, list.toArray());
    }
    
    @Test
    public void map() {
        ArrayList<Integer> arrayList = to(new ArrayList<Integer>(), from(Range.to(10)).map(
                new Function<Integer, Integer>() {
                    public Integer apply(Integer x) {
                        return (int) Math.pow(x, 2);
                    }
                }));
        Object[] array = arrayList.toArray();
        
        assertEquals(10, array.length);
        assertArrayEquals(new Integer[] { 0, 1, 4, 9, 16, 25, 36, 49, 64, 81 },
                array);
    }
}
