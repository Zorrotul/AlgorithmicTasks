import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        log.info("valuesMap: " + String.valueOf(valuesMap));
    }

    public Map<Integer, Integer> countFrequentlyEncountered() {
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
        return tempMap;
    }

    private class Results {
        private List<Integer> popularValues;
        private int count;

        public Results(int count) {
            popularValues = new ArrayList<>();
            this.count = count;
        }

        public void addValue(int value) {
            popularValues.add(value);
        }

        public void incCount() {
            count++;
        }

        public void reset() {
            popularValues = new ArrayList<>();
            count = 1;
        }

        public int getCount() {
            return count;
        }
    }
}
