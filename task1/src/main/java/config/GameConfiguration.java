package config;

import error.ConfigurationException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
@Data
public class GameConfiguration {
    private final int degreeOfAccuracy;
    private final int sequenceQuantity;
    private int[] firstPlayerSequence;
    private int[] secondPlayerSequence;


    public GameConfiguration() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("task1/src/main/resources/gameConfig.properties")) {
            properties.load(input);
            this.degreeOfAccuracy = Integer.parseInt(properties.getProperty("degreeOfAccuracy"));
            this.sequenceQuantity = Integer.parseInt(properties.getProperty("sequenceQuantity"));
        } catch (IOException | NumberFormatException | NullPointerException e) {
            e.printStackTrace();
            throw new ConfigurationException("invalid config");
        }

        try (FileInputStream input = new FileInputStream("task1/src/main/resources/gameConfig.properties")) {
            properties.load(input);
            String[] firstArray = properties.getProperty("firstPlayerSequence").split(",");
            String[] secondArray = properties.getProperty("secondPlayerSequence").split(",");

            this.firstPlayerSequence = new int[firstArray.length];
            for (int i = 0; i < firstArray.length; i++) {
                firstPlayerSequence[i] = Integer.parseInt(firstArray[i].trim());
            }

            this.secondPlayerSequence = new int[secondArray.length];
            for (int i = 0; i < secondArray.length; i++) {
                secondPlayerSequence[i] = Integer.parseInt(secondArray[i].trim());
            }

        } catch (IOException | NumberFormatException | ConfigurationException | NullPointerException e) {
            e.printStackTrace();
            firstPlayerSequence = null;
            secondPlayerSequence = null;
            log.error("cant find player sequences", e);
        }

    }

}
