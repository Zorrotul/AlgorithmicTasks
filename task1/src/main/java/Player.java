import java.util.Arrays;

class Player {
    private final int[] sequence;
    private int numberOfOccurrences;
    private int lastSequenceIndex;
    private final String name;
    private final int playersSequenceSize;

    public Player(String name, int playersSequenceSize, int bound) {
        this.sequence = ArrayHandler.fillInArrayAndGet(playersSequenceSize, bound);
        this.numberOfOccurrences = 0;
        this.name = name;
        this.lastSequenceIndex = 0;
        this.playersSequenceSize = playersSequenceSize;

    }

    public void incNumberOfOccurrences() {
        numberOfOccurrences++;
    }

    public void resetPlayerCache() {
        numberOfOccurrences = 0;
        lastSequenceIndex = 0;
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

    public int getPlayersSequenceSize() {
        return playersSequenceSize;
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
