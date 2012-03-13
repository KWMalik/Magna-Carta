package magna.carta;

import static org.junit.Assert.*;

import org.junit.*;

public class RangeTest {
    
    @Test
    public void to() {
        int[] numbers = new int[7];
        for (int i : Range.to(7)) {
            numbers[i] = i;
        }
        assertArrayEquals(new int[] { 0, 1, 2, 3, 4, 5, 6 }, numbers);
    }
}
