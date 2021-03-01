package com.assignment3.h2setup;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.GridArrangement;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;

public class RingChart {
    public JFreeChart drawRingChart(DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createRingChart("", dataset, true, false, false);
        styleChart(chart);
        return chart;
    }

    private void styleChart(JFreeChart chart) {
        RingPlot plot = (RingPlot) chart.getPlot();

        //was fehlt? -> Legende rechts neben Chart, neben Namen Prozente

        plot.setLabelLinksVisible(false);
        plot.setSectionOutlinesVisible(false);
        plot.setSeparatorPaint(Color.white);
        plot.setShadowPaint(Color.white);
        plot.setLabelGenerator(null);
        plot.setBackgroundPaint(Color.white);
        plot.setOutlineVisible(false);
        plot.setSectionDepth(0.6);


        plot.setLegendItemShape(new Rectangle(8, 8));
        chart.removeLegend();


        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}   {1}%"));
        LegendTitle legend = new LegendTitle(plot, new GridArrangement(9, 1), new GridArrangement(1, 1));
        legend.setPosition(RectangleEdge.BOTTOM);
        chart.addLegend(legend);

        //plot.setSection

        //Sections färben
        plot.setSectionPaint("Apple", new Color(124, 199, 147));
        plot.setSectionPaint("L'Oreal Skincare and Health Ltd.", new Color(49, 152, 133));
        plot.setSectionPaint("Volkswagen AG", new Color(34, 144, 204));
        plot.setSectionPaint("Microsoft Computers and ASCII Art Inc.", new Color(60, 91, 129));
        plot.setSectionPaint("American Airlines Inc.", new Color(94, 84, 37));
        plot.setSectionPaint("München Mag Dich T-Shirts GmbH", new Color(31, 89, 65));


        //section seperator verdicken---------------------------------------???-----------------------

    }
}
