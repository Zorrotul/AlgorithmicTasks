import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class ArrayHandler {
    private final int arraySize;

    private Random random = new Random();

    public ArrayHandler(int arraySize) {
        this.arraySize = arraySize;
    }

    public int[] fillInArrayAndGet() {

        int[] array = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt();
            log.info(String.valueOf(array[i]));
        }
        return array;
    }


}
