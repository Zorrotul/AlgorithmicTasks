package config;

import error.ConfigurationException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
@Data
public class FirstAlgConfiguration {
    private final int sequenceQuantity;
    private final int bound;


    public FirstAlgConfiguration() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("task1/src/main/resources/firstAlgConfig.properties")) {
            properties.load(input);
            this.sequenceQuantity = Integer.parseInt(properties.getProperty("sequenceQuantity"));
            this.bound = Integer.parseInt(properties.getProperty("bound"));
        } catch (IOException | NumberFormatException | NullPointerException e) {
            e.printStackTrace();
            throw new ConfigurationException("invalid config");
        }

    }

}
