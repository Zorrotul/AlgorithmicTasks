import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FirstAlgorithmHandlerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final int[] array = {0, 10, -8, -6, 1, 9, -2, 1, 9, -3};
    private FirstAlgorithmHandler firstAlgorithmHandler;

    @BeforeEach
    public void setUpStreams() {
        Counter counter = new Counter();
        firstAlgorithmHandler = new FirstAlgorithmHandler(array, 10, counter, 0, 10, new int[10]);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void firstAlgorithmNumberLines() {

        firstAlgorithmHandler.handleArrayByFirstAlgorithm();

        String[] lines = outContent.toString().trim().split("\n");
        assertEquals(2, lines.length);
    }


    @Test
    void firstAlgorithmFirstLine() {

        firstAlgorithmHandler.handleArrayByFirstAlgorithm();

        String[] lines = outContent.toString().trim().split("\n");
        assertTrue(lines[0].startsWith("Random array: [0, 10, -8, -6, 1, 9, -2, 1, 9, -3]"));
    }

    @Test
    void firstAlgorithmSecondLine() {

        firstAlgorithmHandler.handleArrayByFirstAlgorithm();

        String[] lines = outContent.toString().trim().split("\n");
        assertTrue(lines[1].startsWith("Sorted array: [-3, 1, 1, 9, 9, 0, 10, -2, -6, -8]"));
    }

    @Test
    void counterNumberLines() {

        firstAlgorithmHandler.handleArrayByFirstAlgorithm();
        firstAlgorithmHandler.printResults();

        String[] lines = outContent.toString().trim().split("\n");
        assertEquals(4, lines.length);
    }

    @Test
    void counterFirstLine() {

        firstAlgorithmHandler.handleArrayByFirstAlgorithm();
        firstAlgorithmHandler.printResults();

        String[] lines = outContent.toString().trim().split("\n");
        assertTrue(lines[2].startsWith("Most popular values: [1, 9]"));
    }

    @Test
    void counterSecondLine() {

        firstAlgorithmHandler.handleArrayByFirstAlgorithm();
        firstAlgorithmHandler.printResults();

        String[] lines = outContent.toString().trim().split("\n");
        assertTrue(lines[3].startsWith("Quantity of popular values: 2"));
    }

}