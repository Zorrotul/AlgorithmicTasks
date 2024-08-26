import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SequenceCalculatorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    SequenceCalculator calculator;
    List<Player> players = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        RandomArrayHandler rahMock = mock(RandomArrayHandler.class);
        calculator = new SequenceCalculator(1, 50, new DecimalFormat("#0.00"), rahMock);
        when(rahMock.generateArray()).thenReturn(new int[]{1, 3, 1, 1, 5, 3, 3, 2, 4, 6, 5, 1, 5, 6, 4, 2, 6, 4, 6, 4, 6, 5, 5, 4, 1, 1, 6, 3, 4, 2, 3, 5, 2, 1, 5, 2, 5, 3, 4, 5, 1, 5, 6, 2, 6, 6, 1, 2, 4, 1});

    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void calculateTheChanceNumberOfLines() {
        players.add(new Player("Alex", new int[]{1, 2, 1}));
        players.add(new Player("Bob", new int[]{1, 2, 1}));

        calculator.calculateTheChanceOfWinningByPlayers(players);

        String[] lines = outContent.toString().trim().split("\n");
        assertEquals(3, lines.length);
    }

    @Test
    void calculateTheChanceStartOfLines() {
        players.add(new Player("Alex", new int[]{1, 2, 1}));
        players.add(new Player("Bob", new int[]{1, 2, 1}));

        calculator.calculateTheChanceOfWinningByPlayers(players);

        String[] lines = outContent.toString().trim().split("\n");
        assertTrue(lines[0].startsWith("Alex chance to win: "));
        assertTrue(lines[1].startsWith("Bob chance to win: "));
        assertTrue(lines[2].startsWith("chance to draw: "));
    }

    @Test
    void calculateTheChanceSameSequences() {
        players.add(new Player("Alex", new int[]{1, 2, 1}));
        players.add(new Player("Bob", new int[]{1, 2, 1}));

        calculator.calculateTheChanceOfWinningByPlayers(players);

        String[] lines = outContent.toString().trim().split("\n");
        assertTrue(lines[0].startsWith("Alex chance to win: 0,00%"));
        assertTrue(lines[1].startsWith("Bob chance to win: 0,00%"));
        assertTrue(lines[2].startsWith("chance to draw: 100,00%"));
    }

    @Test
    void calculateTheChanceWrongSequence() {
        players.add(new Player("Alex", new int[]{1, -1, 1}));
        players.add(new Player("Bob", new int[]{1, 3, 1}));

        calculator.calculateTheChanceOfWinningByPlayers(players);

        String[] lines = outContent.toString().trim().split("\n");
        assertTrue(lines[0].startsWith("Alex chance to win: 0,00%"));
    }

    @Test
    void calculateTheChanceTwoWrongSequence() {
        players.add(new Player("Alex", new int[]{1, -1, 1}));
        players.add(new Player("Bob", new int[]{1, -3, 1}));

        calculator.calculateTheChanceOfWinningByPlayers(players);

        String[] lines = outContent.toString().trim().split("\n");
        assertTrue(lines[0].startsWith("Alex chance to win: 0,00%"));
        assertTrue(lines[1].startsWith("Bob chance to win: 0,00%"));
        assertTrue(lines[2].startsWith("chance to draw: 100,00%"));
    }

    @Test
    void calculateWinnerAndPrintNumberOfLines() {
        players.add(new Player("Alex", new int[]{1, -1, 1}));
        players.add(new Player("Bob", new int[]{1, -3, 1}));

        calculator.calculateAndPrintWinner(players);

        String[] lines = outContent.toString().trim().split("\n");
        assertEquals(1, lines.length);
    }

    @Test
    void calculateWinnerAndPrintDraw() {
        players.add(new Player("Alex", new int[]{1, -1, 1}));
        players.add(new Player("Bob", new int[]{1, -3, 1}));

        calculator.calculateAndPrintWinner(players);

        String[] lines = outContent.toString().trim().split("\n");
        assertEquals(1, lines.length);
        assertTrue(lines[0].startsWith("Draw"));
    }


}