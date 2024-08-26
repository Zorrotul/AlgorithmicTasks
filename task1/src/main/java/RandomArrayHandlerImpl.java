import java.util.Random;

public class RandomArrayHandlerImpl implements RandomArrayHandler{

    private final int arraySize;
    private final int bound;

    public RandomArrayHandlerImpl(int arraySize, int bound) {
        this.arraySize = arraySize;
        this.bound = bound;
    }

    @Override
    public int[] generateArray() {

        Random random = new Random();
        int[] array = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(bound) + 1;
        }
        return array;
    }

    @Override
    public int[] generateRealNumbersArray() {

        Random random = new Random();

        int[] array = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(bound * 2) - bound + 1;
        }
        return array;
    }
}
