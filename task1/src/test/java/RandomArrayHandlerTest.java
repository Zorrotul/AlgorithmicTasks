import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomArrayHandlerTest {
    int[] naturalArray;
    int naturalArraySize = 10;
    int naturalBound = 6;
    int[] realArray;
    int realArraySize = 10;
    int realBound = 6;

    @BeforeEach
    public void setUpStreams() {
        RandomArrayHandler naturalRah = new RandomArrayHandlerImpl(naturalArraySize, naturalBound);
        RandomArrayHandler realRah = new RandomArrayHandlerImpl(realArraySize, realBound);
        naturalArray = naturalRah.generateArray();
        realArray = realRah.generateRealNumbersArray();
    }

    @Test
    void generateArrayLength() {
        assertEquals(naturalArraySize, naturalArray.length);
    }

    @Test
    void generateArrayBoundUnder() {
        for (int j : naturalArray) {
            assertTrue(j >= 1);
        }
    }

    @Test
    void generateArrayBoundUp() {
        for (int j : naturalArray) {
            assertTrue(j <= naturalBound);
        }
    }

    @Test
    void generateRealNumbersArray() {
    }

    @Test
    void generateRealNumbersArrayLength() {
        assertEquals(realArraySize, realArray.length);
    }

    @Test
    void generateRealArrayBoundUnder() {
        for (int j : naturalArray) {
            assertTrue(j > -realBound);
        }
    }

    @Test
    void generateRealArrayBoundUp() {
        for (int j : naturalArray) {
            assertTrue(j <= realBound);
        }
    }
}