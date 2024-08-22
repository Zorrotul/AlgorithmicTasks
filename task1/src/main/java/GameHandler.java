import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class GameHandler {

    private int[] array;
    private List<Player> players;
    private final int bound = 2;
    private final int playersSequenceSize = 3;
    private final SequenceCalculator calculator;


    public GameHandler() {
        this.calculator = new SequenceCalculator(playersSequenceSize);
    }

    public void handle(int quantity) {

        array = ArrayHandler.fillInArrayAndGet(quantity, bound);
        players = new ArrayList<>();
        players.add(new Player("Alex", playersSequenceSize, bound));
        players.add(new Player("Bob", playersSequenceSize, bound));
        log.info("array: " + Arrays.toString(array));
        players.stream()
                .peek(p -> {
                    log.info("{} subsequence: {}", p.getName(), Arrays.toString(p.getSequence()));
                });

        calculator.calculateTheChanceOfWinning(players);
        calculator.calculate(array, players);

    }




}
