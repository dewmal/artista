/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.gui.analys;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.dineth.dip.arttista.artista.gui.ChartAndData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author dewmal
 */
public class ChartBuilder {
    
    public void drawNormalDistributionChart(double[] values) {
        DescriptiveStatistics stats = new DescriptiveStatistics();

        // Add the data from the array
        for (int i = 0; i < values.length; i++) {
            stats.addValue(values[i]);
        }

// Compute some statistics
        double mean = stats.getMean();
        double std = stats.getStandardDeviation();
        double skewness = stats.getSkewness();
        double variance = stats.getVariance();
        double kurtosis = stats.getKurtosis();
        
        System.out.println(mean + "\t" + std + "\t" + skewness + "\t" + variance + "\t" + kurtosis);
        
        
        stats.clear();
    }
    
    public void getXYChart(int width, int height, Map<String, double[]> dataMap) {
        Set<String> keySet = dataMap.keySet();
        
        final DefaultXYDataset dataset = new DefaultXYDataset();
        int j = 0;
        
        for (String key : keySet) {
            double[] values = dataMap.get(key);
            
            
            double[][] dataArray = new double[2][255];
            
            
            for (int i = 0; i < values.length; i++) {
                ChartBuilder.Dot d = new ChartBuilder.Dot();
                d.x = values[i];
                d.y = 1 + i;
                
                
                
                
                dataArray[0][i] = i;
                dataArray[1][i] = values[i];
            }

            //  XYDataset data = (XYDataset) createData("component", dots);

            
            dataset.addSeries(key, dataArray);
            
            
            j++;
            
        }
        
        
        
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Serial Data", // chart title
                "Domain", // domain axis label
                "Range", // range axis label
                dataset, // initial series
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
                );

        // set chart background
        chart.setBackgroundPaint(Color.white);

        /*// set a few custom plot features
         XYPlot plot = (XYPlot) chart.getPlot();
         plot.setBackgroundPaint(new Color(0xffffe0));
         plot.setDomainGridlinesVisible(true);
         plot.setDomainGridlinePaint(Color.lightGray);
         plot.setRangeGridlinePaint(Color.lightGray);

         // set the plot's axes to display integers
         TickUnitSource ticks = NumberAxis.createIntegerTickUnits();
         NumberAxis domain = (NumberAxis) plot.getDomainAxis();
         domain.setStandardTickUnits(ticks);
         NumberAxis range = (NumberAxis) plot.getRangeAxis();
         range.setStandardTickUnits(ticks);

         // render shapes and lines
         XYLineAndShapeRenderer renderer =
         new XYLineAndShapeRenderer(true, true);
         plot.setRenderer(renderer);
         renderer.setBaseShapesVisible(true);
         renderer.setBaseShapesFilled(true);

         // set the renderer's stroke
      
         Stroke stroke = new BasicStroke(
         3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
         renderer.setBaseOutlineStroke(stroke);
         * 

         // label the points
         NumberFormat format = NumberFormat.getNumberInstance();
         format.setMaximumFractionDigits(2);
         XYItemLabelGenerator generator =
         new StandardXYItemLabelGenerator(
         StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT,
         format, format);
         renderer.setBaseItemLabelGenerator(generator);
         renderer.setBaseItemLabelsVisible(true);

         */
        ChartDrawer drawer = new ChartDrawer("Plot", chart, new Dimension(width, height));
        drawer.showChart();
        
        
    }
    
    public void getHistogramChart(int width, int height, Map<String, double[]> dataMap) {
        Set<String> keySet = dataMap.keySet();
        
        HistogramDataset dataset = new HistogramDataset();
        
        
        for (String key : keySet) {
            double[] values = dataMap.get(key);
            dataset.addSeries(key, values, values.length, 0, 1);
            
        }
        
        
        
        JFreeChart chart = ChartFactory.createHistogram(
                "Histogram Demo",
                null,
                null,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        
        chart.setBackgroundPaint(new Color(230, 230, 230));
        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.setForegroundAlpha(0.7F);
        xyplot.setBackgroundPaint(Color.WHITE);
        xyplot.setDomainGridlinePaint(new Color(150, 150, 150));
        xyplot.setRangeGridlinePaint(new Color(150, 150, 150));
        XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
        xybarrenderer.setShadowVisible(false);
        xybarrenderer.setBarPainter(new StandardXYBarPainter());
        
        
        ChartDrawer drawer = new ChartDrawer("Plot", chart, new Dimension(width, height));
        drawer.showChart();
        
    }

    /**
     *
     * Build XY chart For Given Data Range
     *
     * @param width
     * @param height
     * @param datasets
     * @param bin
     * @return
     */
    public void getXYCumChart(int width, int height, Map<String, Map<String, Integer>> datasets, int bin) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        Set<String> keySet = datasets.keySet();
        for (String keyFilter : keySet) {
            Map<String, Integer> filterData = datasets.get(keyFilter);
            
            Set<String> keyAngle = filterData.keySet();
            
            
            XYSeries series = new XYSeries(keyFilter);
            
            int value = 0;
            
            for (String keyAng : keyAngle) {
                //   System.out.println(keyAng);

                double x = Double.valueOf(keyAng);
                value = filterData.get(keyAng);
                
                
                series.add(x, value);
                
                
                
            }
            
            series.setMaximumItemCount(100);
            
            dataset.addSeries(series);
            
        }
        
        
        
        dataset.setAutoWidth(true);
        dataset.setIntervalWidth(1000);
        
        
        
        
        JFreeChart chart = ChartFactory.createXYLineChart(
                "XY Chart",
                // Title
                "Angles ",
                // x-axis Label
                "Count",
                // y-axis Label
                dataset,
                // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true,
                // Show Legend
                true,
                // Use tooltips
                false // Configure chart to generate URLs?
                );
        
        
        ChartDrawer drawer = new ChartDrawer("Plot", chart, new Dimension(width, height));
        drawer.showChart();
        
        
    }

    /**
     *
     * Build XY chart For Given Data Range
     *
     * @param width
     * @param height
     * @param datasets
     * @param bin
     * @return
     */
    public void getXYChart(int width, int height, Map<String, Map<String, Integer>> datasets, int bin) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        Set<String> keySet = datasets.keySet();
        for (String keyFilter : keySet) {
            Map<String, Integer> filterData = datasets.get(keyFilter);
            
            Set<String> keyAngle = filterData.keySet();
            
            
            XYSeries series = new XYSeries(keyFilter);
            
            for (String keyAng : keyAngle) {
                
                double x = Double.valueOf(keyAng);
                int value = filterData.get(keyAng);
                
                
                series.add(x, value);
                
                
                
            }
            
            dataset.addSeries(series);
            
        }
        
        
        
        
        
        
        
        
        JFreeChart chart = ChartFactory.createXYLineChart(
                "XY Chart",
                // Title
                "Angles ",
                // x-axis Label
                "Count",
                // y-axis Label
                dataset,
                // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true,
                // Show Legend
                true,
                // Use tooltips
                false // Configure chart to generate URLs?
                );
        
        
        
        
        
        
        ChartDrawer drawer = new ChartDrawer("Plot", chart, new Dimension(width, height));
        drawer.showChart();
    }
    
    private XYSeriesCollection createData(String componentId, List<ChartBuilder.Dot> dots) {
        XYSeriesCollection data = new XYSeriesCollection();
        
        XYSeries series1 = new XYSeries(componentId);
        
        for (int i = 0; i < dots.size(); i++) {
            series1.add(dots.get(i).y, dots.get(i).x);
        }
        
        data.addSeries(series1);
        
        return data;
    }
    
    private class Dot {
        
        double x;
        double y;
    }
    
    private class ChartDrawer extends ApplicationFrame {
        
        private ChartAndData cad;
        
        public ChartDrawer(String title, JFreeChart chart, Dimension dimension) {
            super(title);
            
            cad = new ChartAndData(title, chart, dimension);
        }
        
        private void showChart() {
            cad.setVisible(true);
        }
    }
}
