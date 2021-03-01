package com.assignment3.h2setup;

import com.assignment3.h2setup.output.OutputDatabase;
import com.assignment3.h2setup.output.OutputPDF;
import com.assignment3.h2setup.repository.PieDataRepository;
import com.assignment3.h2setup.repository.RingDataRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.List;

@SpringBootApplication
public class H2SetupApplication {

    public static final String PATH = "/home/paza/Desktop/assignment3/";
    public static final String OUTPUT_FOLDER_EXTENSION = "output/";

    public static void main(String[] args) throws NoSuchFileException, FileNotFoundException {

        //reads Data into list
        List<String[]> dataForRingChart = InputReader
                .readDataFromCsv(PATH, "Ring Chart Data.csv");
        List<String[]> dataForPieChart = InputReader
                .readDataFromXls(PATH, "piechart-data.xls");

        //builds ring chart
        ChartBuilder.buildRingChart(dataForRingChart, PATH + OUTPUT_FOLDER_EXTENSION);

        //builds pie chart
        ChartBuilder.buildPieChart(dataForPieChart, PATH + OUTPUT_FOLDER_EXTENSION);

        //pdf output pie chart
        OutputPDF.renderPDF(PATH + OUTPUT_FOLDER_EXTENSION,
                "pie_chart.png", "pie_chart.pdf");

        //pdf output ring chart
        OutputPDF.renderPDF(PATH + OUTPUT_FOLDER_EXTENSION,
                "ring_chart.png", "ring_chart.pdf");

        //spring for database
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(H2SetupApplication.class, args);

        //init pie and ring repositories
        PieDataRepository pieDataRepository = configurableApplicationContext.getBean(PieDataRepository.class);
        RingDataRepository ringDataRepository = configurableApplicationContext.getBean(RingDataRepository.class);

        //creates objects from pie chart data and puts it in database
        OutputDatabase.savePieChartDataInDatabase(dataForPieChart, pieDataRepository);

        //creates objects from ring chart data and puts it in database
        OutputDatabase.saveRingChartDataInDatabase(dataForRingChart, ringDataRepository);


    }

}
