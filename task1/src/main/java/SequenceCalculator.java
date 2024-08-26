import config.GameConfiguration;
import error.SqCalculatorException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
public class SequenceCalculator {

    private final int degreeOfAccuracy;
    private final int sequenceQuantity;
    private DecimalFormat format = new DecimalFormat("#0.00");
    private RandomArrayHandler rah;


    public SequenceCalculator(GameConfiguration gameConfiguration) {
        this.degreeOfAccuracy = gameConfiguration.getDegreeOfAccuracy();
        this.sequenceQuantity = gameConfiguration.getSequenceQuantity();
    }


    /**
     * Данный метод играет 1 игру с игроками и выводит победителя на экран
     */
    public void calculateAndPrintWinner(List<Player> players) {
        printResults(calculateWinner(players));
    }

    public void calculateTheChanceOfWinningByPlayers(List<Player> players) {
        calculateTheChanceOfWinning(players);
        printChancesResults(players);
    }


    private void calculateTheChanceOfWinning(List<Player> players) {

        if (players.size() != 2) {
            throw new SqCalculatorException("calculateTheChanceOfWinning<- Wrong quantity of players");
        }

        for (int i = 0; i < degreeOfAccuracy; i++) {
            List<Player> tempPlayers = calculateWinner(players);
            tempPlayers.forEach(Player::incNumberOfWins);

            players.forEach(Player::resetPlayerCache);
        }
        players.forEach(player -> log.debug(String.valueOf(player.getNumberOfWins())));

    }

    private List<Player> calculateWinner(List<Player> players) {
        rah = new RandomArrayHandlerImpl(sequenceQuantity, 6);
        int[] array = rah.generateArray();
        log.debug(Arrays.toString(array));
        for (int i = 0; i < array.length - players.get(0).getPlayersSequenceSize() + 1; i++) {
            int[] subsequence = Arrays.copyOfRange(array, i, i + players.get(0).getPlayersSequenceSize());
            log.debug("subsequence {}: {}", i, Arrays.toString(subsequence));

            for (Player player : players) {
                if (Arrays.equals(subsequence, player.getSequence()) && (i >= player.getLastSequenceIndex())) {
                    player.incNumberOfOccurrences();
                    player.setLastSequenceIndex(i + players.get(0).getPlayersSequenceSize());
                    log.debug("{}, last index: {}", player.getName(), player.getLastSequenceIndex());
                }
            }
        }

        players.forEach(p -> log.debug("Player: {}", p.toString()));

        int max = players.stream()
                .map(Player::getNumberOfWinMatches)
                .max(Integer::compareTo)
                .orElseThrow(() -> new SqCalculatorException("no max value"));

        return players.stream()
                .filter(player -> player.getNumberOfWinMatches() == max)
                .collect(Collectors.toList());
    }

    private void printChancesResults(List<Player> players) {
        if (players.size() != 2) {
            throw new SqCalculatorException("printChancesResults<- Wrong quantity of players");
        }
        int draws = players.get(0).getNumberOfWins() + players.get(1).getNumberOfWins() - degreeOfAccuracy;
        int winFirst = players.get(0).getNumberOfWins() - draws;
        int winSecond = players.get(1).getNumberOfWins() - draws;
        double chancesOfDraw = (double) draws / degreeOfAccuracy * 100;
        double chancesOfWinningFirst = (double) winFirst / degreeOfAccuracy * 100;
        double chancesOfWinningSecond = (double) winSecond / degreeOfAccuracy * 100;
        System.out.printf("%s chance to win: %s%%\n", players.get(0).getName(), format.format(chancesOfWinningFirst));
        System.out.printf("%s chance to win: %s%%\n", players.get(1).getName(), format.format(chancesOfWinningSecond));
        System.out.printf("chance to draw: %s%%\n", format.format(chancesOfDraw));
    }

    private void printResults(List<Player> players) {
        if (players.size() > 1) {
            System.out.print("Draw\n");
        } else {
            System.out.printf("Winner: %s\n", players.get(0).getName());
        }

    }
}
