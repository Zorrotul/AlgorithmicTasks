import config.Configuration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {


    public static void main(String[] args) {
        Configuration configuration = new Configuration();

        //Логика первой и второй задачи
        Counter counter = new Counter();
        FirstAlgorithmHandler firstAlgHandler = new FirstAlgorithmHandler(counter);
        firstAlgHandler.handleArrayByFirstAlgorithm();
        firstAlgHandler.printResults();

        //Логика 3 задачи
        GameHandler gameHandler = new GameHandler(configuration);
        gameHandler.handle();
    }

}