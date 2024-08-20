public class Main {

    public static void main(String[] args) {
        ArrayHandler arrayHandler = new ArrayHandler(10);
        int[] array = arrayHandler.fillInArrayAndGet();
        FirstAlgorithmHandler handler = new FirstAlgorithmHandler(array);
        handler.handleArrayByFirstAlgorithm();
    }

}