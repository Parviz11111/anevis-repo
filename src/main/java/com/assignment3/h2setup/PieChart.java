package com.assignment3.h2setup;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.GridArrangement;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.general.DefaultPieDataset;

import javax.persistence.Entity;
import java.awt.*;

public class PieChart {
    public JFreeChart drawPieChart(DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("", dataset, true, false, false);
        styleChart(chart);
        return chart;
    }

    private void styleChart(JFreeChart chart) {

        PiePlot plot = (PiePlot) chart.getPlot();


        plot.setLabelLinksVisible(false);
        plot.setSectionOutlinesVisible(false);
        //plot.setSeparatorPaint(Color.white);
        //plot.setSeparatorStroke(BasicStroke(10));
        plot.setShadowPaint(Color.white);
        plot.setLabelGenerator(null);
        plot.setBackgroundPaint(Color.white);
        plot.setOutlineVisible(false);

        plot.setLegendItemShape(new Rectangle(8, 8));
        chart.removeLegend();

        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}   {1}%"));

        LegendTitle legend = new LegendTitle(plot, new GridArrangement(9, 1),
                new GridArrangement(1, 1));
        legend.setPosition(RectangleEdge.BOTTOM);

        chart.addLegend(legend);


        //plot.setSection

        //Sections färben
        plot.setSectionPaint("Deutschland", new Color(126, 204, 151));
        plot.setSectionPaint("Frankreich", new Color(50, 154, 134));
        plot.setSectionPaint("Spanien", new Color(34, 144, 206));
        plot.setSectionPaint("Niederlande", new Color(94, 84, 37));
        plot.setSectionPaint("Belgien", new Color(31, 89, 65));
        plot.setSectionPaint("Italien", new Color(132, 122, 43));
        plot.setSectionPaint("Großbritannien", new Color(219, 194, 55));
        plot.setSectionPaint("Kanada", new Color(236, 165, 51));
        plot.setSectionPaint("Liquidität/Terminkontrakte", new Color(242, 196, 72));

        //section seperator verdicken---------------------------------------???-----------------------

    }
}