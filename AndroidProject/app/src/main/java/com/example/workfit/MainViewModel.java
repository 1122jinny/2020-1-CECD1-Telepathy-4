package com.example.workfit;

import androidx.lifecycle.ViewModel;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class MainViewModel extends ViewModel {
    private Map<Date, String> mDailyStepCountMap = new TreeMap<>();

    // Method to add new key-value into the fitness data map.
    public void addDailyStepCount(Date date, String steps) {
        if (date == null) {
            return;
        }
        mDailyStepCountMap.put(date, steps.isEmpty() ? "0" : steps);
    }

    // Method to clear the data in the map.
    public void clearData() {
        mDailyStepCountMap.clear();
    }

    // Method to get the fitness data.
    public Map<Date, String> getFitnessData() {
        return mDailyStepCountMap;
    }

}