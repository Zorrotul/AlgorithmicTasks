import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class ArrayHandler {
//    private final int arraySize;
//    private final int bound;

//    private Random random = new Random();

//    public ArrayHandler(int arraySize, int bound) {
//        this.arraySize = arraySize;
//        this.bound = bound;
//    }

    public static int[] fillInArrayAndGet(int arraySize, int bound) {

        Random random = new Random();

        int[] array = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(bound) + 1;
        }
        return array;
    }

    public static int[] fillInArrayAndGet(int arraySize) {

        Random random = new Random();

        int[] array = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt();
            log.info(String.valueOf(array[i]));
        }
        return array;
    }


}
