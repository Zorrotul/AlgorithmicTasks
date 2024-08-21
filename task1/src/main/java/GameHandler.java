import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class GameHandler {

    private final int[] array;
    private final List<Player> players;
    private final int bound = 2;
    private final int playersSequenceSize = 3;


    public GameHandler(int quantity) {
        this.array = ArrayHandler.fillInArrayAndGet(quantity, bound);
        players = new ArrayList<>();
        players.add(new Player("Alex"));
        players.add(new Player("Bob"));
        log.info("array: " + Arrays.toString(array));
        players.stream()
                .peek(p -> {
                    log.info("{} subsequence: {}", p.name, Arrays.toString(p.sequence));
                });

    }

    public void handle() {
        for (int i = 0; i < array.length - playersSequenceSize + 1; i++) {
            int[] subsequence = Arrays.copyOfRange(array, i, i + playersSequenceSize);
            log.info("subsequence {}: {}", i, Arrays.toString(subsequence));

            for (Player player : players) {
                if (Arrays.equals(subsequence, player.sequence) && (i >= player.lastSequenceIndex)) {
                    player.incNumberOfOccurrences();
                    player.setLastSequenceIndex(i + playersSequenceSize);
                    log.info("{}, last index: {}", player.name, player.lastSequenceIndex);
                }
            }
        }

        players.stream().peek(p -> log.info("Player: {}", p.toString()))
                .collect(Collectors.toList());
    }

    private class Player {
        private final int[] sequence;
        private int numberOfOccurrences;
        private int lastSequenceIndex;
        private final String name;

        public Player(String name) {
            this.sequence = ArrayHandler.fillInArrayAndGet(playersSequenceSize, bound);
            numberOfOccurrences = 0;
            this.name = name;
            this.lastSequenceIndex = 0;

        }

        public void incNumberOfOccurrences() {
            numberOfOccurrences++;
        }

        public int getNumberOfOccurrences() {
            return numberOfOccurrences;
        }

        public int[] getSequence() {
            return sequence;
        }

        public String getName() {
            return name;
        }

        public void setLastSequenceIndex(int lastSequenceIndex) {
            this.lastSequenceIndex = lastSequenceIndex;
        }

        public int getLastSequenceIndex() {
            return lastSequenceIndex;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            return sb.append("Name: ")
                    .append(name)
                    .append(", Sequence: ")
                    .append(Arrays.toString(sequence))
                    .append(", NumberOfOccurrences: ")
                    .append(numberOfOccurrences)
                    .toString();
        }
    }
}
