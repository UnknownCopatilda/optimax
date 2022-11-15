package optimax.task.auction;

import optimax.task.auction.utils.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {

    @Test
    void calcRange() {
        Range range = Range.calcRange(25);
        assertEquals(23, range.lowerLimit());
        assertEquals(27, range.upperLimit());

        range = Range.calcRange(10);
        assertEquals(9, range.lowerLimit());
        assertEquals(11, range.upperLimit());

        range = Range.calcRange(50);
        assertEquals(45, range.lowerLimit());
        assertEquals(55, range.upperLimit());

    }
}