package com.assignment3.h2setup.output;

import com.assignment3.h2setup.model.PieData;
import com.assignment3.h2setup.model.RingData;
import com.assignment3.h2setup.repository.PieDataRepository;
import com.assignment3.h2setup.repository.RingDataRepository;

import java.util.List;

public class OutputDatabase {

    //creates objects from pie chart data and puts it in database
    public static void savePieChartDataInDatabase(List<String[]> dataForPieChart,
                                                  PieDataRepository pieDataRepository) {

        dataForPieChart.forEach((row) -> {
            pieDataRepository.save(new PieData(row[0], Double.parseDouble(row[1])));
        });
    }

    //creates objects from ring chart data and puts it in database
    public static void saveRingChartDataInDatabase(List<String[]> dataForRingChart,
                                                   RingDataRepository ringDataRepository) {

        dataForRingChart.forEach((row) -> {
            ringDataRepository.save(new RingData(row[0], row[1], Double.parseDouble(row[2])));
        });

    }
}
