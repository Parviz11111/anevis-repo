package com.assignment3.h2setup;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Set;

public class ChartBuilder {

    public static void buildRingChart(List<String[]> data, String filepath) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        RingChart ringChart = new RingChart();

        data.forEach((row) -> dataset.setValue(row[1], Double.valueOf(row[2])));

        JFreeChart chart = ringChart.drawRingChart(dataset);

        int width = 700;
        int height = 700;

        exportAsPNG(chart, width, height, filepath, "ring_chart");

    }

    public static void buildPieChart(List<String[]> data, String filepath) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        PieChart pieChart = new PieChart();

        data.forEach((row) -> dataset.setValue(row[0], Double.valueOf(row[1])));

        JFreeChart chart = pieChart.drawPieChart(dataset);

        int width = 700;
        int height = 700;

        exportAsPNG(chart, width, height, filepath, "pie_chart");

    }

    private static void exportAsPNG(JFreeChart chart, int width, int height, String chartFullpath, String chartName) {
        try {
            Path chartPath = Paths.get(chartFullpath);
            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrw-");
            FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
            if (Files.notExists(chartPath)) {
                Files.createDirectories(chartPath, attr);
            }
            chartFullpath = chartFullpath + chartName + ".png";

            perms = PosixFilePermissions.fromString("rw-rw-rw-");
            attr = PosixFilePermissions.asFileAttribute(perms);
            Path pathToChart = Paths.get(chartFullpath);
            Files.deleteIfExists(pathToChart);
            Files.createFile(pathToChart, attr);

            ChartUtils.saveChartAsPNG(pathToChart.toFile(), chart, width, height);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
