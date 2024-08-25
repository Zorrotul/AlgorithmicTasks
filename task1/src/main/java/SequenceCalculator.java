import config.Configuration;
import error.SqCalculatorException;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SequenceCalculator {

    private final int playersSequenceSize;
    private final int degreeOfAccuracy;
    DecimalFormat format = new DecimalFormat("#0.00");


    public SequenceCalculator(Configuration configuration) {
        this.playersSequenceSize = configuration.getPlayersSequenceSize();
        this.degreeOfAccuracy = configuration.getDegreeOfAccuracy();
    }

    public void calculateAndPrintWinner(List<Player> players) {
        printResults(calculateWinner(players));
    }

    public List<Player> calculateWinner(List<Player> players) {
        int[] array = RandomArrayHandler.generateArray(50, 6);
        for (int i = 0; i < array.length - playersSequenceSize + 1; i++) {
            int[] subsequence = Arrays.copyOfRange(array, i, i + playersSequenceSize);
            log.debug("subsequence {}: {}", i, Arrays.toString(subsequence));

            for (Player player : players) {
                if (Arrays.equals(subsequence, player.getSequence()) && (i >= player.getLastSequenceIndex())) {
                    player.incNumberOfOccurrences();
                    player.setLastSequenceIndex(i + playersSequenceSize);
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

    public void calculateTheChanceOfWinningBySequences(int[] s1, int[] s2) {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Alex", s1));
        players.add(new Player("Bob", s2));
        calculateTheChanceOfWinning(players);
        printChancesResults(players);
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
        log.info("{} chance to win: {}%", players.get(0).getName(), format.format(chancesOfWinningFirst));
        log.info("{} chance to win: {}%", players.get(1).getName(), format.format(chancesOfWinningSecond));
        log.info("chance to draw: {}%", format.format(chancesOfDraw));
    }

    private void printResults(List<Player> players) {
        if (players.size() > 1) {
            log.info("Draw");
        } else {
            log.info("Winner: {}", players.get(0).getName());
        }

    }
}
