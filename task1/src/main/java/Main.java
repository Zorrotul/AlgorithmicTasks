public class Main {

    public static void main(String[] args) {
        ArrayHandler arrayHandler = new ArrayHandler(100);
        int[] array = arrayHandler.fillInArrayAndGet();
        Counter counter = new Counter();
        FirstAlgorithmHandler firstAlgHandler = new FirstAlgorithmHandler(array, counter);
        firstAlgHandler.handleArrayByFirstAlgorithm();
        firstAlgHandler.printResults();
    }

}