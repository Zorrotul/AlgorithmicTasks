import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class SequenceCalculator {

    private final int playersSequenceSize;

    public SequenceCalculator(int playersSequenceSize) {
        this.playersSequenceSize = playersSequenceSize;
    }

    public List<Player> calculate(int[] array, List<Player> players) {
        for (int i = 0; i < array.length - playersSequenceSize + 1; i++) {
            int[] subsequence = Arrays.copyOfRange(array, i, i + playersSequenceSize);
            log.info("subsequence {}: {}", i, Arrays.toString(subsequence));

            for (Player player : players) {
                if (Arrays.equals(subsequence, player.getSequence()) && (i >= player.getLastSequenceIndex())) {
                    player.incNumberOfOccurrences();
                    player.setLastSequenceIndex(i + playersSequenceSize);
                    log.info("{}, last index: {}", player.getName(), player.getLastSequenceIndex());
                }
            }
        }

        players.stream().peek(p -> log.info("Player: {}", p.toString()))
                .collect(Collectors.toList());


        int max = players.stream()
                .map(p -> p.getNumberOfOccurrences())
                .max(Integer::compareTo)
                .orElseThrow(RuntimeException::new);//TODO exceptions

        return players.stream()
                .filter(player -> player.getNumberOfOccurrences() == max)
                .collect(Collectors.toList());
    }

    public void calculateTheChanceOfWinning(List<Player> players) {
        Map<Player, Integer> playerWinsMap = new HashMap<>();
        players.forEach(p -> playerWinsMap.put(p, 0));
        log.info(String.valueOf(playerWinsMap));
        for (int i = 0; i < 1000; i++) {
            List<Player> tempPlayers = calculate(ArrayHandler.fillInArrayAndGet(1000, 6), players);
            tempPlayers.stream()
                    .peek(player -> playerWinsMap.computeIfPresent(player, (k, v) -> v + 1))
                    .collect(Collectors.toList());
            players.forEach(Player::resetPlayerCache);
        }
        log.info(String.valueOf(playerWinsMap.values()));
    }

}
