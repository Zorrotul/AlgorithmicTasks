import config.Configuration;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class GameHandler {

    private int[] array;
    private List<Player> players;
    private final int bound = 6;
    private final int playersSequenceSize;
    private final SequenceCalculator calculator;
    private final int quantity;
    private final Configuration configuration;


    public GameHandler(Configuration configuration) {
        this.playersSequenceSize = configuration.getPlayersSequenceSize();
        this.quantity = configuration.getSequenceQuantity();
        this.calculator = new SequenceCalculator(configuration);
        this.configuration = configuration;
    }

    public void handle() {

        array = RandomArrayHandler.generateArray(quantity, bound);
        players = new ArrayList<>();

        if (configuration.getFirstPlayerSequence() != null) {
            players.add(new Player("Alex", configuration.getFirstPlayerSequence()));
            players.add(new Player("Bob", configuration.getSecondPlayerSequence()));
        } else {
            players.add(new Player("Alex", playersSequenceSize, bound));
            players.add(new Player("Bob", playersSequenceSize, bound));
        }
        calculator.calculateTheChanceOfWinningByPlayers(players);
        calculator.calculateAndPrintWinner(players);

        players.forEach(p -> log.debug("{} sequence: {}", p.getName(), Arrays.toString(p.getSequence())));
    }
}
