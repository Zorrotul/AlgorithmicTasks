import error.CounterException;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Counter {
    private final HashMap<Integer, Integer> valuesMap;

    public Counter() {
        this.valuesMap = new HashMap<>();
    }

    public void count(int i) {
        if (valuesMap.containsKey(i)) {
            valuesMap.put(i, valuesMap.get(i) + 1);
        } else {
            valuesMap.put(i, 1);
        }
        log.debug("valuesMap: " + valuesMap);
    }

    public void printFrequentlyEncountered() {
        Map<Integer, Integer> tempMap = new HashMap<>();
        int max = valuesMap.values().stream().max(Comparator.naturalOrder())
                .orElseThrow(() -> {
                    throw new RuntimeException();
                });

        for (Map.Entry<Integer, Integer> entry : valuesMap.entrySet()) {
            if (entry.getValue() == max) {
                tempMap.put(entry.getKey(), max);
            }
        }
        printResults(tempMap);
    }

    private void printResults(Map<Integer, Integer> results) {
        System.out.printf("Most popular values: %s\n", results.keySet());
        System.out.printf("Quantity of popular values: %d\n", results.values().stream().findFirst().orElseThrow(() -> {
            throw new CounterException("PrintResults<- cant find values");
        }));
    }

}
