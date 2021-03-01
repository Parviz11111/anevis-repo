package com.assignment3.h2setup;

import com.assignment3.h2setup.model.PieData;
import com.assignment3.h2setup.model.RingData;
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


        //test ob Daten korrekt eingelesen
        System.out.println("erste Zeile = " + dataForPieChart.get(0)[1]);
        System.out.println("Tabellenausgabe");
        dataForPieChart.forEach((data) -> System.out.println(data[1]));

        //spring for database
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(H2SetupApplication.class, args);

        //init pie and ring repositories
        PieDataRepository pieDataRepository = configurableApplicationContext.getBean(PieDataRepository.class);
        RingDataRepository ringDataRepository = configurableApplicationContext.getBean(RingDataRepository.class);

        //creates objects from pie chart data and puts it in database
        dataForPieChart.forEach((row) -> {
            pieDataRepository.save(new PieData(row[0], Double.parseDouble(row[1])));
        });

        //creates objects from ring chart data and puts it in database
        dataForRingChart.forEach((row) -> {
            ringDataRepository.save(new RingData(row[0], row[1], Double.parseDouble(row[2])));
        });


    }

}
