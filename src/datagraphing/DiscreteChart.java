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
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.util.SortOrder;
import org.jfree.data.xy.XYSeriesCollection;
import userinterface.AppColors;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class DiscreteChart {

    DiscreteDataset dataset;
    JFreeChart discreteChart = null;

    public DiscreteChart(DiscreteDataset dataset)
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

        plot.getRangeAxis().setLowerBound(0);
        plot.getRangeAxis().setUpperBound(10);
        plot.getRangeAxis().setVisible(true);
        plot.getRangeAxis().setTickLabelFont(new Inconsolata().getFont(0));
        plot.getRangeAxis().setAutoRange(false);
        plot.getRangeAxis().setFixedDimension(50);

        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.RIGHT);
        legend.setFrame(BlockBorder.NONE);
        legend.setItemFont(new Inconsolata().getFont(14));
        legend.setBackgroundPaint(AppColors.BACKGROUND);
        legend.setItemPaint(AppColors.BORDER);
        legend.setSortOrder(SortOrder.DESCENDING);

        legend.setItemLabelPadding(new RectangleInsets(0, 0, 23.5, 0));
        legend.setLegendItemGraphicPadding(new RectangleInsets(0, 0, 23.5, 0));
        legend.setPadding(0, 0, 23.5, 0);

        discreteChart = chart;
    }

    private void setData(XYLineAndShapeRenderer renderer)
    {
        Shape dot;
        int seriesNumber;
        float defaultDot = 3;
        float defaultThickness = 3.0f;

        //RCS Control
        dot = new Ellipse2D.Double(defaultDot, defaultDot, defaultDot, defaultDot);
        seriesNumber = getSeriesNumber("RCS Active", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, false);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(defaultThickness));

        //Intervention to RCOS
        dot = new Ellipse2D.Double(defaultDot, defaultDot, defaultDot, defaultDot);
        seriesNumber = getSeriesNumber("RCOS Intervention", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, false);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(defaultThickness));

        //MI Active
        dot = new Ellipse2D.Double(defaultDot, defaultDot, defaultDot, defaultDot);
        seriesNumber = getSeriesNumber("MI Active", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, false);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(defaultThickness));

        //Spreader Lock
        dot = new Ellipse2D.Double(defaultDot, defaultDot, defaultDot, defaultDot);
        seriesNumber = getSeriesNumber("Spreader Lock", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, false);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(defaultThickness));

        //FLS Scan
        dot = new Ellipse2D.Double(defaultDot, defaultDot, defaultDot, defaultDot);
        seriesNumber = getSeriesNumber("FLS Scan", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, false);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(defaultThickness));

        //TPS Positioning
        dot = new Ellipse2D.Double(defaultDot, defaultDot, defaultDot, defaultDot);
        seriesNumber = getSeriesNumber("TPS Positioning", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, false);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(defaultThickness));

        //TPS Scan
        dot = new Ellipse2D.Double(defaultDot, defaultDot, defaultDot, defaultDot);
        seriesNumber = getSeriesNumber("TPS Scan", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, false);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(defaultThickness));

        //ATIDS Active
        dot = new Ellipse2D.Double(defaultDot, defaultDot, defaultDot, defaultDot);
        seriesNumber = getSeriesNumber("ATIDS Active", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, false);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(defaultThickness));

        //GA Motion
        dot = new Ellipse2D.Double(defaultDot, defaultDot, defaultDot, defaultDot);
        seriesNumber = getSeriesNumber("GA Motion", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, false);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(defaultThickness));

        //Hoist Motion
        dot = new Ellipse2D.Double(defaultDot, defaultDot, defaultDot, defaultDot);
        seriesNumber = getSeriesNumber("Hoist Motion", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, false);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(defaultThickness));

        //Trolley Motion
        dot = new Ellipse2D.Double(defaultDot, defaultDot, defaultDot, defaultDot);
        seriesNumber = getSeriesNumber("Trolley Motion", (XYSeriesCollection) dataset);
        renderer.setSeriesShape(seriesNumber, dot);
        renderer.setSeriesLinesVisible(seriesNumber, false);
        renderer.setSeriesStroke(seriesNumber, new BasicStroke(defaultThickness));
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

    public JFreeChart getDiscreteChart() {
        return discreteChart;
    }
}
