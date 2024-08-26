import java.util.Arrays;

class Player {
    private final int[] sequence;
    private int numberOfWinMatches;
    private int lastSequenceIndex;
    private int numberOfWins;
    private final String name;
    private final int playersSequenceSize;

    public Player(String name, int playersSequenceSize, int bound) {
        RandomArrayHandler rah = new RandomArrayHandlerImpl(playersSequenceSize, bound);
        this.sequence = rah.generateArray();
        this.numberOfWinMatches = 0;
        this.lastSequenceIndex = 0;
        this.numberOfWins = 0;
        this.name = name;
        this.playersSequenceSize = playersSequenceSize;
    }

    public Player(String name, int[] sequence) {
        this.sequence = sequence;
        this.numberOfWinMatches = 0;
        this.lastSequenceIndex = 0;
        this.numberOfWins = 0;
        this.name = name;
        this.playersSequenceSize = sequence.length;

    }

    public void incNumberOfOccurrences() {
        numberOfWinMatches++;
    }

    public void incNumberOfWins() {
        this.numberOfWins++;
    }

    public int getPlayersSequenceSize() {
        return playersSequenceSize;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void resetPlayerCache() {
        numberOfWinMatches = 0;
        lastSequenceIndex = 0;
    }

    public int getNumberOfWinMatches() {
        return numberOfWinMatches;
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
                .append(numberOfWinMatches)
                .toString();
    }
}
