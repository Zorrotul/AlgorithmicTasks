import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class FirstAlgorithmHandler {
    private final int[] array;
    private final int arraySize;
    private int leftBorder;
    private int rightBorder;

    public FirstAlgorithmHandler(int[] array) {
        this.array = array;
        this.arraySize = array.length;
        this.leftBorder = 0;
        this.rightBorder = arraySize;
    }

    public int[] handleArrayByFirstAlgorithm() {
        int[] tempArray = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            if (array[i] == 0) {
                continue;
            }

            if (array[i] % 2 == 0) {
                embedEvenNumberIntoArray(tempArray, array[i]);
            }

            if (array[i] % 2 != 0) {
                embedOddNumberIntoArray(tempArray, array[i]);
            }
        }
        log.info(Arrays.toString(tempArray));
        return tempArray;
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
        log.info("index: {}, value: {}, rightBorder: {}", index, value, rightBorder);
        log.info("array: {}", Arrays.toString(array));
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
        log.info("index: {}, value: {}, rightBorder: {}", index, value, leftBorder);
        log.info("array: {}", Arrays.toString(array));
        leftBorder++;
    }

}
