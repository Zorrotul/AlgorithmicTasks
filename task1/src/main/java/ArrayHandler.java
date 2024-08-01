import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class ArrayHandler {
    private final int arraySize;

    private Random random = new Random();

    public ArrayHandler(int arraySize) {
        this.arraySize = arraySize;
    }

    public int[] initArray() {

        int[] array = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt();
            log.info(String.valueOf(array[i]));
        }

        return array;
    }

    public int[] handleArray(int[] array) {
        int[] newArraySize = new int[arraySize];
        int leftBorder = 0;
        int rightBorder = arraySize;

        for (int i = 0; i < arraySize; i++) {
            if (array[i] == 0) {
                continue;
            }

            if (array[i] % 2 == 0) {
                newArraySize[i]
            }
        }
    }


    private void embeddingIntoArray(int[] array, int value, int rightBorder, int leftBorder) {

    }
}
