package controller;

import model.Region;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.List;

public class Line extends View{
    DefaultCategoryDataset dataset;


    public Line(){
        setTitle("Line Chart");
        chartType="Line";
        createChart();

    }


    @Override
    public void addDataset( ) {

        dataset = new DefaultCategoryDataset();
        List<Region> regions = data.getRegions();
        for(Region r : regions){
            dataset.setValue(r.getNHPI(),r.getRegionName(),r.getPeriod());
        }

    }



    @Override
    public JFreeChart plotView() {

        JFreeChart chart = ChartFactory.createLineChart("Line: New housing price index (NHPI)",
                "Year-Month",
                "NHPI",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        //XYPlot plot = chart.getXYPlot();
        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        //XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        //not sure how many series we have
        renderer.setSeriesPaint(0, Color.YELLOW);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }



}
