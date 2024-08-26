import config.GameConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class GameHandler {

    private final int bound = 6;
    private final SequenceCalculator calculator;
    private final GameConfiguration gameConfiguration;


    public GameHandler(GameConfiguration gameConfiguration) {
        this.calculator = new SequenceCalculator(gameConfiguration);
        this.gameConfiguration = gameConfiguration;
    }

    public void handle() {

        List<Player> players = new ArrayList<>();

        if (gameConfiguration.getFirstPlayerSequence() != null) {
            players.add(new Player("Alex", gameConfiguration.getFirstPlayerSequence()));
            players.add(new Player("Bob", gameConfiguration.getSecondPlayerSequence()));
        } else {
            players.add(new Player("Alex", 3, bound));
            players.add(new Player("Bob", 3, bound));
        }
        calculator.calculateTheChanceOfWinningByPlayers(players);

        players.forEach(p -> log.debug("{} sequence: {}", p.getName(), Arrays.toString(p.getSequence())));
    }
}
