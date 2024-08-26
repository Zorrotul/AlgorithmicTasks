import config.FirstAlgConfiguration;
import config.GameConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {


    public static void main(String[] args) {
        GameConfiguration gameConfiguration = new GameConfiguration();
        FirstAlgConfiguration firstAlgConfiguration = new FirstAlgConfiguration();

        //Логика первой и второй задачи
        Counter counter = new Counter();
        FirstAlgorithmHandler firstAlgHandler = new FirstAlgorithmHandler(counter, firstAlgConfiguration);
        firstAlgHandler.handleArrayByFirstAlgorithm();
        firstAlgHandler.printResults();

        //Логика 3 задачи
        GameHandler gameHandler = new GameHandler(gameConfiguration);
        gameHandler.handle();
    }

}