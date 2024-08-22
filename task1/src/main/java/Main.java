public class Main {

    public static void main(String[] args) {
//        int[] array = ArrayHandler.fillInArrayAndGet(100,10);
//        Counter counter = new Counter();
//        FirstAlgorithmHandler firstAlgHandler = new FirstAlgorithmHandler(array, counter);
//        firstAlgHandler.handleArrayByFirstAlgorithm();
//        firstAlgHandler.printResults();

        GameHandler gameHandler = new GameHandler();
        gameHandler.handle(50);
    }

}