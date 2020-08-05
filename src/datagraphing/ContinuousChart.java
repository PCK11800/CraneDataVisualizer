package datagraphing;

import font.Inconsolata;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.LineBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.xy.XYSeriesCollection;
import userinterface.AppColors;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ContinuousChart{

    ContinuousDataset dataset;
    JFreeChart continuousChart = null;

    public ContinuousChart(ContinuousDataset dataset)
    {
        this.dataset = dataset;
        createChart();
    }

    private void createChart()
    {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setDefaultLinesVisible(false);
        renderer.setDrawSeriesLineAsPath(true);
        chart.setBackgroundPaint(AppColors.BACKGROUND);

        setData(renderer);

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(AppColors.BACKGROUND);
        plot.setRangeGridlinesVisible(false);
        plot.setRangeGridlinePaint(AppColors.BORDER);
        plot.setRangeMinorGridlinesVisible(false);
        plot.setRangeMinorGridlinePaint(AppColors.BORDER);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(AppColors.BORDER);

        plot.getDomainAxis().setLowerBound(0);
        plot.getDomainAxis().setVisible(true);
        plot.getDomainAxis().setTickLabelPaint(AppColors.BORDER);
        plot.getDomainAxis().setTickLabelFont(new Inconsolata().getFont(14));

        plot.getRangeAxis().setVisible(true);
        plot.getRangeAxis().setTickLabelPaint(AppColors.BORDER);
        plot.getRangeAxis().setTickLabelFont(new Inconsolata().getFont(14));
        plot.getRangeAxis().setAutoRange(false);
        plot.getRangeAxis().setFixedDimension(50);

        double lowerBound = Math.abs(((XYSeriesCollection) dataset).getRangeLowerBound(false));
        double upperBound = Math.abs(((XYSeriesCollection) dataset).getRangeUpperBound(false));
        double bound = 0;

        if(lowerBound >= upperBound) { bound = lowerBound; }
        else{ bound = upperBound; }

        bound = bound * 1.2; //Add some padding
        plot.getRangeAxis().setRange(bound * -1, bound);

        //Legend
        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.RIGHT);
        legend.setFrame(BlockBorder.NONE);
        legend.setItemFont(new Inconsolata().getFont(14));
        legend.setBackgroundPaint(AppColors.BACKGROUND);
        legend.setItemPaint(AppColors.BORDER);

        continuousChart = chart;
    }

    private void setData(XYLineAndShapeRenderer renderer)
    {
        Shape dot;
        int seriesNumber;

        //Zero Line
        dot = new Ellipse2D.Double(0, 0, 0, 0);
        seriesNumber = getSeriesNumber("", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, true);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(2.0f));
        renderer.setSeriesPaint(seriesNumber, AppColors.BORDER);
        renderer.setSeriesVisibleInLegend(seriesNumber, false);

        //Hoist Position
        dot = new Ellipse2D.Double(0, 0, 0, 0);
        seriesNumber = getSeriesNumber("Hoist Position", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, true);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(4.0f));
        renderer.setSeriesPaint(seriesNumber, Color.RED);

        //Trolley Position
        dot = new Ellipse2D.Double(0, 0, 0, 0);
        seriesNumber = getSeriesNumber("Trolley Position", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, true);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(4.0f));
        renderer.setSeriesPaint(seriesNumber, Color.BLUE);
    }

    private int getSeriesNumber(String name, XYSeriesCollection dataset){
        for(int i = 0; i < dataset.getSeriesCount(); i++)
        {
            String seriesName = (String) dataset.getSeriesKey(i);
            if (name.equals(seriesName))
            {
                return i;
            }
        }
        return 0;
    }

    public JFreeChart getContinuousChart()
    {
        return continuousChart;
    }
}
