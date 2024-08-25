import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class FirstAlgorithmHandler {
    private final int[] array;
    private final int arraySize;
    private final Counter counter;
    private int leftBorder;
    private int rightBorder;
    private final int[] newArray;


    public FirstAlgorithmHandler(Counter counter) {
        this.array = RandomArrayHandler.generateRealNumbersArray(100, 10);
        this.arraySize = array.length;
        this.counter = counter;
        this.leftBorder = 0;
        this.rightBorder = arraySize;
        this.newArray = new int[arraySize];
    }

    public void handleArrayByFirstAlgorithm() {
        log.info("Random array: {}", Arrays.toString(array));

        for (int i = 0; i < arraySize; i++) {
            counter.count(array[i]);
            if (array[i] == 0) {
                continue;
            }

            if (array[i] % 2 == 0) {
                embedEvenNumberIntoArray(newArray, array[i]);
            }

            if (array[i] % 2 != 0) {
                embedOddNumberIntoArray(newArray, array[i]);
            }
        }
        log.info("Sorted array: {}", Arrays.toString(newArray));
    }

    public void printResults() {
        counter.printFrequentlyEncountered();
    }

    private void embedEvenNumberIntoArray(int[] array, int value) {
        int index = rightBorder - 1;
        for (int i = rightBorder; i < arraySize; i++) {
            if (value <= array[i]) {
                index = i;
            } else {
                break;
            }
        }

        for (int i = rightBorder; i < index + 1; i++) {
            array[i - 1] = array[i];
        }
        array[index] = value;
        rightBorder--;
    }

    private void embedOddNumberIntoArray(int[] array, int value) {
        int index = 0;
        for (int i = 0; i < leftBorder; i++) {
            if (value >= array[i]) {
                index = i + 1;
            } else {
                break;
            }
        }

        for (int i = leftBorder; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        leftBorder++;
    }

}
