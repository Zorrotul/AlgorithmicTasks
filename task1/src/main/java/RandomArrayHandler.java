import java.util.Random;

public class RandomArrayHandler {

    public static int[] generateArray(int arraySize, int bound) {

        Random random = new Random();
        int[] array = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(bound) + 1;
        }
        return array;
    }

    public static int[] generateRealNumbersArray(int arraySize, int bound) {

        Random random = new Random();

        int[] array = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(bound * 2) - bound + 1;
        }
        return array;
    }
}
