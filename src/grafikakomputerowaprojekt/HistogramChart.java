package grafikakomputerowaprojekt;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class HistogramChart extends JPanel {
    private int[] data;  

    public HistogramChart(int[] data) {
        this.data = data;
        
        createChart();
    }

    public HistogramChart() {
    }

    public void setData(int[] data) {
        this.data = data;
        createChart();
    }

    private void createChart() {
        IntervalXYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 700));

        removeAll();
        add(chartPanel);
        revalidate();
    }

    private IntervalXYDataset createDataset() {
        XYSeries series = new XYSeries("Histogram");
        for (int i = 0; i < data.length; i++) {
            series.add(i * (255.0 / data.length), data[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    private JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart(
                "Histogram",
                "Pixel value",
                false,
                "Count",
                dataset
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setTickUnit(new NumberTickUnit(25));

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        XYBarRenderer renderer = new XYBarRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setBasePaint(Color.BLUE);

        plot.setRenderer(renderer);

        return chart;
    }
}
